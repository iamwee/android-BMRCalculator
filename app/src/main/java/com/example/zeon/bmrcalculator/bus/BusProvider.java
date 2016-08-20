package com.example.zeon.bmrcalculator.bus;

import com.squareup.otto.Bus;

public class BusProvider extends Bus {

    private static BusProvider instance;
    public static BusProvider getInstance(){
        if(instance == null) instance = new BusProvider();
        return instance;
    }
}
