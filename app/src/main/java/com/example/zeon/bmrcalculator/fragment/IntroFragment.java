package com.example.zeon.bmrcalculator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zeon.bmrcalculator.R;
import com.example.zeon.bmrcalculator.bmr.BMR;
import com.example.zeon.bmrcalculator.bus.BusProvider;
import com.example.zeon.bmrcalculator.bus.event.SimpleEvent;
import com.example.zeon.bmrcalculator.customview.NonSwipeViewPager;
import com.example.zeon.bmrcalculator.bmr.BMRProfile;
import com.example.zeon.bmrcalculator.manager.BMRStorage;
import com.squareup.otto.Subscribe;


public class IntroFragment extends Fragment {

    NonSwipeViewPager viewPager;

    public static IntroFragment newInstance(){
        IntroFragment fragment = new IntroFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);
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

    public void onRestoreInstanceState(Bundle savedInstanceState){

    }

    private void initInstances(View rootView){
        viewPager = (NonSwipeViewPager) rootView.findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0 : return MoreIntroFragment.newInstance();
                    case 1 : return InputIntroFragment.newInstance();
                    default: return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

    @Subscribe
    public void onInputInformationClicked(SimpleEvent event){
        viewPager.setCurrentItem(1, true);
    }

    @Subscribe
    public void onInformationAdded(BMRProfile profile) {

        BMR bmr = new BMR.Builder()
                .profile(profile)
                .build();

        BMRStorage.getInstance(getActivity()).writeBMR(bmr);
        BusProvider.getInstance().post(new SimpleEvent(1));
    }
}
