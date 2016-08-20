package com.example.zeon.bmrcalculator.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zeon.bmrcalculator.R;
import com.example.zeon.bmrcalculator.bus.BusProvider;
import com.example.zeon.bmrcalculator.bus.event.SimpleEvent;
import com.example.zeon.bmrcalculator.bmr.BMRProfile;
import com.example.zeon.bmrcalculator.manager.BMRCalculator;
import com.example.zeon.bmrcalculator.manager.BMRStorage;


public class MainFragment extends Fragment {

    TextView tvName;
    TextView tvBMR;
    View btnAddFood, btnHistory, btnInformation, btnHelp;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setBMRText();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @SuppressLint("SetTextI18n")
    private void initInstances(View rootView) {
        tvName = (TextView) rootView.findViewById(R.id.tv_name);
        tvBMR = (TextView) rootView.findViewById(R.id.tv_bmr_score);

        BMRProfile profile = BMRStorage.getInstance(getActivity()).readProfile();

        btnAddFood = rootView.findViewById(R.id.btn_add_food);
        btnHistory = rootView.findViewById(R.id.btn_history);
        btnInformation = rootView.findViewById(R.id.btn_information);
        btnHelp = rootView.findViewById(R.id.btn_help);

        btnAddFood.setOnClickListener(clickListener);
        btnHistory.setOnClickListener(clickListener);
        btnInformation.setOnClickListener(clickListener);
        btnHelp.setOnClickListener(clickListener);

        tvName.setText("Hello " +
                (profile != null ? profile.getName() : "Anonymous") +
                "\nYour BMR today is");
        setBMRText();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnAddFood){
                BusProvider.getInstance().post(new SimpleEvent(1));
            } else if (view == btnHistory) {
                BusProvider.getInstance().post(new SimpleEvent(2));
            } else if (view == btnInformation) {
                BusProvider.getInstance().post(new SimpleEvent(3));
            } else if (view == btnHelp) {
                BusProvider.getInstance().post(new SimpleEvent(4));
            }
        }
    };

    private void setBMRText(){
        double result = new BMRCalculator.Builder()
                .profile(BMRStorage
                        .getInstance(getActivity())
                        .readProfile())
                .build()
                .compute();
        tvBMR.setText(String.valueOf(result));
    }

}
