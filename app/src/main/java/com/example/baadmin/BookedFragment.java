package com.example.baadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.baadmin.databinding.FragmentBookedBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class BookedFragment extends Fragment {

    FragmentBookedBinding binding;
    FirebaseFirestore db;
    ArrayList<BookedModel> bookedList;
    BookedAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookedBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        db=FirebaseFirestore.getInstance();
        binding.rBooked.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bookedList = new ArrayList<>();
        adapter = new BookedAdapter(bookedList,getContext());
        binding.rBooked.setAdapter(adapter);

        db.collection("check").get().addOnSuccessListener(queryDocumentSnapshots -> {

            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
            for(DocumentSnapshot d:list) {

                BookedModel obj=d.toObject(BookedModel.class);
                db.collection("users").whereEqualTo("type",obj.getCname()).get().addOnSuccessListener(queryDocumentSnapshots2 -> {

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
