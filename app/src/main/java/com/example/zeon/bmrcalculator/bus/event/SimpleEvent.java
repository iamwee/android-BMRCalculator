package com.example.zeon.bmrcalculator.bus.event;

/**
 * Created by Zeon on 15/8/2559.
 */
public class SimpleEvent {
    private int eventCode;

    public SimpleEvent(int eventCode) {
        this.eventCode = eventCode;
    }

    public SimpleEvent() {
    }

    public int getEventCode() {
        return eventCode;
    }
}
