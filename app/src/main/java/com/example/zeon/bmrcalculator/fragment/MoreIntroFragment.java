package com.example.zeon.bmrcalculator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.zeon.bmrcalculator.R;
import com.example.zeon.bmrcalculator.bus.BusProvider;
import com.example.zeon.bmrcalculator.bus.event.SimpleEvent;


public class MoreIntroFragment extends Fragment {

    TextView tvTitle;
    Button btnInput;

    public static MoreIntroFragment newInstance(){
        MoreIntroFragment fragment = new MoreIntroFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_intro_more, container, false);
        initInstances(rootView);
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        fadeIn();
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

    public void onRestoreInstanceState(Bundle savedInstanceState){

    }

    private void initInstances(View rootView){
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        btnInput = (Button) rootView.findViewById(R.id.btn_input);


        btnInput.setOnClickListener(clickListener);
    }

    private void fadeIn(){
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_show_view);
        tvTitle.startAnimation(anim);
        btnInput.startAnimation(anim);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnInput){
                BusProvider.getInstance().post(new SimpleEvent());
            }
        }
    };
}
