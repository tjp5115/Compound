package com.compound.request.json.alpha.vantage;

import com.google.api.client.util.Key;

import java.util.Map;

public class AlphaVantageJson {
  @Key("Weekly Time Series")
  public Map<String,AlphaVantageStock> weeklyData ;
  @Key("Meta Data")
  public Map<String,String> metaData;


  public String toString(){
    StringBuilder string = new StringBuilder();
    return string.toString();
  }
}
