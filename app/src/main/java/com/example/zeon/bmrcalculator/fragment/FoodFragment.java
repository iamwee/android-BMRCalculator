package com.example.zeon.bmrcalculator.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zeon.bmrcalculator.R;
import com.example.zeon.bmrcalculator.adapter.FoodAdapter;
import com.example.zeon.bmrcalculator.bmr.BMRFood;
import com.example.zeon.bmrcalculator.bus.BusProvider;
import com.example.zeon.bmrcalculator.manager.BMRStorage;
import com.squareup.otto.Subscribe;

import java.util.List;


public class FoodFragment extends Fragment {

    private FloatingActionButton btnAddFood;
    private FoodAdapter adapter;
    private ListView listView;
    private LinearLayout foodEmptyList, foodList;

    public static FoodFragment newInstance() {
        FoodFragment fragment = new FoodFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);
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
        btnAddFood = (FloatingActionButton) rootView.findViewById(R.id.btn_add_food);
        listView = (ListView) rootView.findViewById(R.id.list_view);
        foodEmptyList = (LinearLayout) rootView.findViewById(R.id.food_list_empty);
        foodList = (LinearLayout) rootView.findViewById(R.id.food_list);

        btnAddFood.setOnClickListener(clickListener);
        adapter = new FoodAdapter(BMRStorage.getInstance(getActivity()).readFoodList());
        if (BMRStorage.getInstance(getActivity()).readFoodList().size() <= 0) {
            foodEmptyList.setVisibility(View.VISIBLE);
        } else {
            foodEmptyList.setVisibility(View.GONE);
            foodList.setVisibility(View.VISIBLE);
        }
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(itemLongClickListener);

        listView.setOnItemClickListener(itemClickListener);
    }

    @Subscribe
    public void onFoodAdded(BMRFood food) {
        BMRStorage.getInstance(getActivity()).writeNewFood(food);
        List<BMRFood> foods = BMRStorage.getInstance(getActivity()).readFoodList();
        if (foods.size() > 0) {
            foodList.setVisibility(View.VISIBLE);
            foodEmptyList.setVisibility(View.GONE);
        }
        adapter.setFoods(foods);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AddFoodDialogFragment.newInstance().show(getChildFragmentManager(), "dialog_fragment");
        }
    };

    private AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            BMRStorage.getInstance(getActivity()).removeFoodAtPosition(i);
            List<BMRFood> foods = BMRStorage.getInstance(getActivity()).readFoodList();
            adapter.setFoods(foods);
            if (foods.size() <= 0) {
                foodList.setVisibility(View.GONE);
                foodEmptyList.setVisibility(View.VISIBLE);
            }

            return true;
        }
    };

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getActivity(), adapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
