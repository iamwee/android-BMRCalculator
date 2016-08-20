package com.example.zeon.bmrcalculator.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zeon.bmrcalculator.bmr.BMRFood;

import java.util.List;

/**
 * Created by Zeon on 19/8/2559.
 */
public class FoodAdapter extends BaseAdapter {

    private List<BMRFood> foods;

    public FoodAdapter(List<BMRFood> foods) {
        this.foods = foods;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public BMRFood getItem(int i) {
        return foods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setFoods(List<BMRFood> foods){
        this.foods = foods;
        notifyDataSetChanged();
    }

    public List<BMRFood> getFoods() {
        return foods;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if(view == null){
            textView = new TextView(viewGroup.getContext());
            textView.setPadding(32, 32, 32, 32);
        } else {
            textView = (TextView) view;
        }

        textView.setText(getItem(i).toString());
        return textView;
    }
}
