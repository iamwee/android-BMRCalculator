package com.example.zeon.bmrcalculator.bmr;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zeon on 16/8/2559.
 */
public class BMRFood {

    @SerializedName("name")     private String name;
    @SerializedName("cal")      private int calories;

    public BMRFood() {
    }

    public BMRFood(Builder builder) {
        this.name = builder.name;
        this.calories = builder.cal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return getName() + " ให้พลังงาน " + getCalories() + " แคลอรี่";
    }

    public static class Builder {
        private String name;
        private int cal;
        public Builder(){

        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder cal(int cal){
            this.cal = cal;
            return this;
        }

        public BMRFood build(){
            return new BMRFood(this);
        }

        public BMRFood create(){
            return new BMRFood();
        }
    }
}
