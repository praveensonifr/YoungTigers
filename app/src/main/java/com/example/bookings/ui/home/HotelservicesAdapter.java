package com.example.bookings.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookings.R;

public class HotelservicesAdapter extends RecyclerView.Adapter<HotelservicesAdapter.ServicesHolder> {

    Context context1;
    String services1[];
    String servicesdetails1[];
    int images1[];

    public  HotelservicesAdapter (Context context, String services[], String servicesdetails[], int images[]){

        context1 = context;
        services1 = services;
        servicesdetails1 = servicesdetails;
        images1 = images;

    }

    @NonNull
    @Override
    public HotelservicesAdapter.ServicesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context1);
        View view = layoutInflater.inflate(R.layout.hotelservices_layout, parent,false);
        return new ServicesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelservicesAdapter.ServicesHolder holder, int position) {
        holder.textView.setText(services1[position]);
        holder.textView2.setText(servicesdetails1[position]);
        holder.imageView.setImageResource(images1[position]);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ServicesHolder extends RecyclerView.ViewHolder{

        TextView textView;
        TextView textView2;
        ImageView imageView;

        public ServicesHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.servicesTitle);
            textView2 = itemView.findViewById(R.id.servicesDetails);
            imageView = itemView.findViewById(R.id.servicesImage);
        }
    }
}
