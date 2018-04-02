package com.compound.request.json.compound;

import com.google.api.client.util.Key;

public class Statistics {
    @Key
    public double oneWayAnova;

    public void setOneWayAnova(double oneWayAnova) {
        this.oneWayAnova = oneWayAnova;
    }
}
