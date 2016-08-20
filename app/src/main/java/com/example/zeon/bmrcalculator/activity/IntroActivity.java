package com.example.zeon.bmrcalculator.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zeon.bmrcalculator.R;
import com.example.zeon.bmrcalculator.bus.BusProvider;
import com.example.zeon.bmrcalculator.bus.event.SimpleEvent;
import com.example.zeon.bmrcalculator.fragment.IntroFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        if (!isProfileSetup()) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, IntroFragment.newInstance(), "intro_fragment")
                        .commit();
            }
        } else {
            openActivity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onProfileSaved(SimpleEvent event) {
        if(event.getEventCode() == 1){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void openActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean isProfileSetup() {
        return getSharedPreferences("bmr", Context.MODE_PRIVATE).getString("json", null) != null;
    }
}
