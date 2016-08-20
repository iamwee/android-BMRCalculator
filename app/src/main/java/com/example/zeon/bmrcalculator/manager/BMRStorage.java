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
    private Gson gson;
    private SharedPreferences pref;

    public static BMRStorage getInstance(Context context) {
        if(instance == null) instance = new BMRStorage(context);
        return instance;
    }

    public BMRStorage(Context context){
        gson = new Gson();
        pref = context.getSharedPreferences("bmr", Context.MODE_PRIVATE);
    }

    public void writeBMR(BMR bmr){
        pref.edit().putString("json", gson.toJson(bmr)).apply();
    }

    public BMR readBMR(){
        return gson.fromJson(pref.getString("json", null), BMR.class);
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

}
