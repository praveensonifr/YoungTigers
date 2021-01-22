package com.example.bookings.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

/*        final TextView tv,tv2,tv3,tv4,tv5,tv6,tv7;
        tv=root.findViewById(R.id.textView);
        tv2=root.findViewById(R.id.textView2);
        tv3=root.findViewById(R.id.textView3);
        tv4=root.findViewById(R.id.textView4);
        tv5=root.findViewById(R.id.textView5);
        tv6=root.findViewById(R.id.textView6);
        tv7=root.findViewById(R.id.textView7);*/

        final EditText editTextDatearrival,editTextDatedeparture,editTextNumberguests;

        editTextDatearrival=root.findViewById(R.id.editTextDatearrival);
        editTextDatedeparture=root.findViewById(R.id.editTextDatedeparture);
        editTextNumberguests=root.findViewById(R.id.editTextNumberguests);

        final Spinner hotelSpinner;
        hotelSpinner=root.findViewById(R.id.hotelSpinner);

       // String[] hotelBranches = {"Hotel Paris","Hotel Miami","Hotel Maldives","Hotel London"};
      //  ArrayAdapter arrayAdapterH = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,hotelBranches);
      //  hotelSpinner.setAdapter(arrayAdapterH);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //tv.setText(s);
            }
        });
        return root;
    }
}