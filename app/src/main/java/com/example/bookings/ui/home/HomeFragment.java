package com.example.bookings.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookings.LoginActivity;
import com.example.bookings.R;
import com.example.bookings.RegisterActivity;
import com.example.bookings.ui.dashboard.DashboardFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button BooknowButton;
    RecyclerView hotelservices;
    private String services[];
    private String servicesDetail[];
    int images [] = {R.drawable.hotelbreakfast,R.drawable.hotelpool,R.drawable.hotelrestaurant};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        services = getResources().getStringArray(R.array.serviceshotel);
        servicesDetail = getResources().getStringArray(R.array.serviceshoteldetails);

        HotelservicesAdapter hotelservicesAdapter = new HotelservicesAdapter(this.getContext(),services,servicesDetail,images);
        RecyclerView hotelservices = root.findViewById(R.id.hotelservices);
        hotelservices.setAdapter(hotelservicesAdapter);
        hotelservices.setLayoutManager(new LinearLayoutManager(this.getContext()));


        Button BooknowButton = root.findViewById(R.id.BooknowButton);
        BooknowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
//                DashboardFragment dashboardFragment = new DashboardFragment();
//                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment,dashboardFragment);
//                fragmentTransaction.commit();

                Intent intent = new Intent(getContext(), DashboardFragment.class);
                startActivity(intent);
            }
        });


        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }*/

        return root;

       // });

    }

}