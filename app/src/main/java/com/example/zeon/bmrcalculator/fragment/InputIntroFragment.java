package com.example.zeon.bmrcalculator.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeon.bmrcalculator.R;
import com.example.zeon.bmrcalculator.bus.BusProvider;
import com.example.zeon.bmrcalculator.bus.event.SimpleEvent;
import com.example.zeon.bmrcalculator.bmr.BMR;
import com.example.zeon.bmrcalculator.bmr.BMRProfile;
import com.google.gson.Gson;


public class InputIntroFragment extends Fragment {
    TextInputEditText edtName, edtAge, edtWeight, edtHeight;
    Button btnSaveProfile;
    RadioButton rbMale, rbFemale;

    public static InputIntroFragment newInstance() {
        InputIntroFragment fragment = new InputIntroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_intro_input, container, false);
        initInstances(rootView);
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
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

    private void initInstances(View rootView) {
        btnSaveProfile = (Button) rootView.findViewById(R.id.btn_save_profile);
        edtName = (TextInputEditText) rootView.findViewById(R.id.edt_name);
        edtAge = (TextInputEditText) rootView.findViewById(R.id.edt_age);
        edtHeight = (TextInputEditText) rootView.findViewById(R.id.edt_height);
        edtWeight = (TextInputEditText) rootView.findViewById(R.id.edt_weight);
        rbMale = (RadioButton) rootView.findViewById(R.id.rb_male);
        rbFemale = (RadioButton) rootView.findViewById(R.id.rb_female);


        edtWeight.setOnEditorActionListener(editorActionListener);
        btnSaveProfile.setOnClickListener(clickListener);
    }

    private TextView.OnEditorActionListener editorActionListener =
            new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == EditorInfo.IME_ACTION_DONE) saveProfile();
            return false;
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnSaveProfile) {
                saveProfile();
            }
        }
    };

    private void saveProfile() {
        if (edtName.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Input your name.", Toast.LENGTH_SHORT).show();
            return;
        } else if (edtAge.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Input your age.", Toast.LENGTH_SHORT).show();
            return;
        } else if (edtWeight.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Input your weight.", Toast.LENGTH_SHORT).show();
            return;
        } else if (edtHeight.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Input your height.", Toast.LENGTH_SHORT).show();
        }

        String name = edtName.getText().toString();
        int age = Integer.parseInt(edtAge.getText().toString());
        float weight = Float.parseFloat(edtWeight.getText().toString());
        float height = Float.parseFloat(edtHeight.getText().toString());

        BMRProfile profile =  new BMRProfile.Builder()
                .name(name)
                .age(age)
                .sex(rbMale.isChecked() ? 1 : 2)
                .weight(weight)
                .height(height)
                .build();


        BusProvider.getInstance().post(profile);
    }
}
