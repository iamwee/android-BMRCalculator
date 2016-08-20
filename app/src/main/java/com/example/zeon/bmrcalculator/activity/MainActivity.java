package com.example.zeon.bmrcalculator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.zeon.bmrcalculator.R;
import com.example.zeon.bmrcalculator.bus.BusProvider;
import com.example.zeon.bmrcalculator.bus.event.SimpleEvent;
import com.example.zeon.bmrcalculator.fragment.MainFragment;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MainFragment.newInstance(), "main_fragment")
                    .commit();
        }

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
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
    public void onMainFragmentMenuCliked(SimpleEvent event) {
        switch (event.getEventCode()) {
            case 1:
                openActivity(FoodActivity.class);
                break;
            case 2:
                openActivity(HistoryActivity.class);
                break;
            case 3:
                openActivity(ProfileActivity.class);
                break;
            case 4:
                openActivity(HelpActivity.class);
                break;
        }
    }

    private void openActivity(Class<?> cs) {
        startActivity(new Intent(this, cs));
    }
}
