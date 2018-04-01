package com.compound.request.json.compound;

import com.google.api.client.util.Key;

public class CompoundStats {
  @Key
  public double oneWayAnova;

  public void setOneWayAnova(double oneWayAnova) {
    this.oneWayAnova = oneWayAnova;
  }
}
