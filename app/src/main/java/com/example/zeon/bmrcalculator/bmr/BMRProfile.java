package com.example.zeon.bmrcalculator.bmr;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BMRProfile implements Parcelable{

    public static final int BMR_PROFILE_MALE = 1;
    public static final int BMR_PROFILE_FEMALE = 2;

    @SerializedName("name")     private String name;
    @SerializedName("age")      private int age;
    @SerializedName("sex")      private int sex;
    @SerializedName("weight")   private float weight;
    @SerializedName("height")   private float height;

    public BMRProfile() {

    }

    public BMRProfile(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.weight = builder.weight;
        this.height = builder.height;
    }


    protected BMRProfile(Parcel in) {
        name = in.readString();
        age = in.readInt();
        sex = in.readInt();
        weight = in.readFloat();
        height = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt(sex);
        dest.writeFloat(weight);
        dest.writeFloat(height);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BMRProfile> CREATOR = new Creator<BMRProfile>() {
        @Override
        public BMRProfile createFromParcel(Parcel in) {
            return new BMRProfile(in);
        }

        @Override
        public BMRProfile[] newArray(int size) {
            return new BMRProfile[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "BMRProfile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

    public static class Builder {

        private String name;
        private int age;
        private int sex;
        private float weight;
        private float height;


        public Builder(){
            this.age = 15;
            this.weight = 30;
            this.height = 140;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder sex(int sex){
            this.sex = sex;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Builder weight(float weight){
            this.weight = weight;
            return this;
        }

        public Builder height(float height){
            this.height = height;
            return this;
        }

        public BMRProfile build(){
            return new BMRProfile(this);
        }

        public BMRProfile create(){
            return new BMRProfile();
        }
    }
}
