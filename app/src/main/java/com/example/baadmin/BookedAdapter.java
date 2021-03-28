package com.example.baadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookedAdapter extends RecyclerView.Adapter<BookedAdapter.MyViewHolder> {

    ArrayList<BookedModel> bookedList;
    Context context;

    public BookedAdapter(ArrayList<BookedModel> bookedList, Context mContext) {

        this.bookedList = bookedList;
        context = mContext;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.time.setText("Time: "+bookedList.get(position).getTime());
        holder.date.setText("Date: "+bookedList.get(position).getDate());
        holder.location.setText("Location: "+bookedList.get(position).getLocation());
        holder.name.setText(bookedList.get(position).getName());

        if(bookedList.get(position).getType().contains("Hospital")) {

            holder.special.setText("Speciality: "+bookedList.get(position).getSpecial());
            holder.special.setVisibility(View.VISIBLE);

        } else if (bookedList.get(position).getType().contains("Movie")){

            holder.special.setText("Show: "+bookedList.get(position).getSpecial());
            holder.special.setVisibility(View.VISIBLE);

        }

        if(bookedList.get(position).getuName() != null) {

            holder.uname.setText("User: "+bookedList.get(position).getuName());
            holder.uname.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return bookedList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date,location,name,special,time,uname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.booked_date_list);
            location = itemView.findViewById(R.id.booked_location_list);
            name = itemView.findViewById(R.id.booked_hos_name_list);
            uname = itemView.findViewById(R.id.booked_uName_list);
            special = itemView.findViewById(R.id.booked_special_list);
            time = itemView.findViewById(R.id.booked_time_list);

        }
    }

}
