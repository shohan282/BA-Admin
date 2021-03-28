package com.example.baadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.baadmin.databinding.FragmentAvailableBinding;
import com.example.baadmin.databinding.FragmentBookedBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AvailableFragment extends Fragment {

    FragmentAvailableBinding binding;
    FirebaseFirestore db;
    ArrayList<BookedModel> bookedList;
    BookedAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAvailableBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        db=FirebaseFirestore.getInstance();
        binding.rAvail.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bookedList = new ArrayList<>();
        adapter = new BookedAdapter(bookedList,getContext());
        binding.rAvail.setAdapter(adapter);

        db.collection("check").get().addOnSuccessListener(queryDocumentSnapshots -> {

            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
            for(DocumentSnapshot d:list) {

                BookedModel obj=d.toObject(BookedModel.class);
                db.collection(obj.getCname()).get().addOnSuccessListener(queryDocumentSnapshots2 -> {

                    List<DocumentSnapshot> list2 = queryDocumentSnapshots2.getDocuments();
                    for(DocumentSnapshot d2:list2) {

                        BookedModel obj2=d2.toObject(BookedModel.class);
                        bookedList.add(obj2);
                    }
                    adapter.notifyDataSetChanged();
                });

            }

        });



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
