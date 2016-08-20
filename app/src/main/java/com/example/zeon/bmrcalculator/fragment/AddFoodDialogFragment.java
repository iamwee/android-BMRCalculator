package com.example.zeon.bmrcalculator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeon.bmrcalculator.R;
import com.example.zeon.bmrcalculator.bmr.BMRFood;
import com.example.zeon.bmrcalculator.bus.BusProvider;

/**
 * Created by Zeon on 17/8/2559.
 */
public class AddFoodDialogFragment extends DialogFragment {


    private Button btnAddFood;
    private TextInputEditText edtFoodName, edtcal;

    public static AddFoodDialogFragment newInstance(){
        AddFoodDialogFragment fragment = new AddFoodDialogFragment();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_add_food, container, false);
        initInstance(rootView);
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

    private void initInstance(View rootView){
        btnAddFood = (Button) rootView.findViewById(R.id.btn_add_food);
        edtFoodName = (TextInputEditText) rootView.findViewById(R.id.edt_food_name);
        edtcal = (TextInputEditText) rootView.findViewById(R.id.edt_food_calories);


        edtcal.setOnEditorActionListener(editorActionListener);
        btnAddFood.setOnClickListener(clickListener);
    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == EditorInfo.IME_ACTION_DONE) {
                addFood();
                return true;
            }
            return false;
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addFood();
        }
    };

    private void addFood(){
        if(edtFoodName.getText().toString().isEmpty() || edtcal.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Enter food name or calorie that you want", Toast.LENGTH_SHORT).show();
            return;
        }

        BMRFood food = new BMRFood.Builder()
                .name(edtFoodName.getText().toString())
                .cal(Integer.parseInt(edtcal.getText().toString()))
                .build();

        BusProvider.getInstance().post(food);
        Toast.makeText(getActivity(), "Add Completed!", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}
