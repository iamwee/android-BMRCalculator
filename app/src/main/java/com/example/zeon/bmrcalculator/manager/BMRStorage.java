package com.example.zeon.bmrcalculator.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zeon.bmrcalculator.bmr.BMR;
import com.example.zeon.bmrcalculator.bmr.BMRFood;
import com.example.zeon.bmrcalculator.bmr.BMRProfile;
import com.google.gson.Gson;

import java.util.List;

public class BMRStorage {

    private static BMRStorage instance;
    private Context context;

    public static BMRStorage getInstance(Context context) {
        if(instance == null) instance = new BMRStorage(context);
        return instance;
    }

    public BMRStorage(Context context){
        this.context = context;
    }

    public void writeBMR(BMR bmr){
        getBMRPreferences().edit().putString("json", getGson().toJson(bmr)).apply();
    }

    public BMR readBMR(){
        return getGson().fromJson(getBMRPreferences().getString("json", null), BMR.class);
    }

    public void writeNewFood(BMRFood food){
        BMR bmr = readBMR();
        bmr.getFood().add(food);
        writeBMR(bmr);
    }

    public List<BMRFood> readFoodList(){
        return readBMR().getFood();
    }

    public void removeFoodAtPosition(int position){
        BMR bmr = readBMR();
        bmr.getFood().remove(position);
        writeBMR(bmr);
    }

    public void removeAllFood(){
        BMR bmr = readBMR();
        bmr.getFood().clear();
        writeBMR(bmr);
    }

    public void writeProfile(BMRProfile profile){
        BMR bmr = readBMR();
        bmr.setProfile(profile);
        writeBMR(bmr);
    }

    public BMRProfile readProfile(){
        return readBMR().getProfile();
    }


    private Gson getGson(){
        return new Gson();
    }

    private SharedPreferences getBMRPreferences(){
        return context.getSharedPreferences("bmr", Context.MODE_PRIVATE);
    }
}
