package com.compound.request.json.alpha.vantage;

import com.google.api.client.util.Key;

import java.util.Map;

public class AlphaVantageJson {
  @Key("Weekly Time Series")
  public Map<String,AlphaVantageStock> list;
  @Key("Meta Data")
  public Map<String,String> metaData;
}
