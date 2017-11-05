package com.compound.request.json.alpha.vantage;

import com.google.api.client.util.Key;

import java.util.Map;

public class AlphaVantageJsonWeekly extends AlphaVantageJson {
  @Key("Weekly Time Series")
  public Map<String,AlphaVantageStock> weeklyData;

  @Override
  public Map<String, AlphaVantageStock> getStockData() {
    return weeklyData;
  }
}
