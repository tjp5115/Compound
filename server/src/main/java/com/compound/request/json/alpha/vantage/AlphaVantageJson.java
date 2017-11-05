package com.compound.request.json.alpha.vantage;

import com.google.api.client.util.Key;

import java.util.Map;

public abstract class AlphaVantageJson {
  @Key("Meta Data")
  public Map<String,String> metaData;

  public abstract Map<String,AlphaVantageStock> getStockData();
}
