package com.example.bookings.ui.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookings.MainNavigation;
import com.example.bookings.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private Spinner hotelSpinner;
    private EditText editTextDatearrival;
    private EditText editTextDatedeparture;
    private EditText editTextNumberguests;
    private Button bookingroombutton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final Button bookingroombutton = root.findViewById(R.id.bookingroombutton);
        final EditText editTextDatearrival,editTextDatedeparture,editTextNumberguests;

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getContext()).setTitle("Notification")
                .setMessage("Room Successfully Booked").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog.Builder alertDialogEmpty = new AlertDialog.Builder(this.getContext()).setTitle("Notification")
                .setMessage("All fields need to filled for booking!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        alertDialog.create();
        alertDialogEmpty.create();

        editTextDatearrival=root.findViewById(R.id.editTextDatearrival);
        editTextDatedeparture=root.findViewById(R.id.editTextDatedeparture);
        editTextNumberguests=root.findViewById(R.id.editTextNumberguests);

        final Spinner hotelSpinner;
        hotelSpinner=root.findViewById(R.id.hotelSpinner);

       String[] hotelBranches = {"Hotel Paris","Hotel Miami","Hotel Maldives","Hotel London"};
       ArrayAdapter arrayAdapterH = new ArrayAdapter<String>(this.getContext(),R.layout.support_simple_spinner_dropdown_item,hotelBranches);
       hotelSpinner.setAdapter(arrayAdapterH);

        bookingroombutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if edit text fields are not empty
                if (editTextDatearrival.getText().toString().isEmpty()) {
                    alertDialogEmpty.show();
                }
                else if (editTextDatedeparture.getText().toString().isEmpty()) {
                    alertDialogEmpty.show();
                }
                else if (editTextNumberguests.getText().toString().isEmpty()) {
                    alertDialogEmpty.show();
                }
                else if (hotelSpinner.getSelectedItem().toString().isEmpty()) {
                    alertDialogEmpty.show();
                }
                else{
                    alertDialog.show();
                }

            }
        });

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //tv.setText(s);
            }
        });
        return root;
    }

}