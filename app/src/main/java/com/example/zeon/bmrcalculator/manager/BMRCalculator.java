package com.example.zeon.bmrcalculator.manager;

import android.content.Intent;
import android.renderscript.Double2;

import com.example.zeon.bmrcalculator.bmr.BMRProfile;

public class BMRCalculator {

    private BMRProfile profile;

    public BMRCalculator() { }

    public BMRCalculator(Builder builder){
        this.profile = builder.profile;
    }

    public BMRProfile getProfile() {
        return profile;
    }

    public void setProfile(BMRProfile profile) {
        this.profile = profile;
    }

    public double compute(){
        if(profile.getSex() == BMRProfile.BMR_PROFILE_MALE)
            return (10 * profile.getWeight()) +
                    (6.25 * profile.getHeight()) +
                    (5 * profile.getAge()) + 5;
        else
            return (10 * profile.getWeight()) +
                    (6.25 * profile.getHeight()) +
                    (5 * profile.getAge()) - 161;
    }

    public static class Builder {

        private BMRProfile profile;

        public Builder(){

        }

        public Builder profile(BMRProfile profile){
            this.profile = profile;
            return this;
        }

        public BMRCalculator build(){
            return new BMRCalculator(this);
        }

        public BMRCalculator create(){
            return new BMRCalculator();
        }

    }
}
