package com.example.zeon.bmrcalculator.bmr;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BMR {

    @SerializedName("profile")
    private BMRProfile profile;
    @SerializedName("food")
    private List<BMRFood> food = new ArrayList<>();

    public BMR(){

    }

    public BMR(Builder builder) {
        this.profile = builder.profile;
    }


    public BMRProfile getProfile() {
        return profile;
    }

    public void setProfile(BMRProfile profile) {
        this.profile = profile;
    }

    public List<BMRFood> getFood() {
        return food;
    }

    public void setFood(List<BMRFood> food) {
        this.food = food;
    }

    public static class Builder {

        private BMRProfile profile;
        private List<BMRFood> foodList;
        public Builder(){

        }

        public Builder profile(BMRProfile profile){
            this.profile = profile;
            return this;
        }

        public Builder foodList(List<BMRFood> foodList){
            this.foodList = foodList;
            return this;
        }

        public BMR build(){
            return new BMR(this);
        }

        public BMR create(){
            return new BMR();
        }

    }
}
