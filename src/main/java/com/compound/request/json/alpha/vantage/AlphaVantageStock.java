package com.compound.request.json.alpha.vantage;

import com.google.api.client.util.Key;

public class AlphaVantageStock {

  @Key("1. open")
  public double open;

  @Key("2. high")
  public double high;

  @Key("3. low")
  public double low;

  @Key("4. close")
  public double close;

  @Key("5. volume")
  public double volume;

}
