package com.compound.request.json.compound;

import com.compound.request.json.alpha.vantage.AlphaVantageStock;
import com.google.api.client.util.Key;

public class CompoundJsonStock {
  @Key
  public double value;

  public CompoundJsonStock(double value) {
   this.value = value;
  }
}
