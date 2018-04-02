package com.compound.request.json.compound;

import com.compound.request.json.alpha.vantage.AlphaVantageStock;
import com.google.api.client.util.Key;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class StockSnapshot {
  private WeekFields weekFields = WeekFields.of(Locale.getDefault());
  @Key
  public Date date;
  @Key
  public double close;

  public void setDate(LocalDate date) {
    this.date = new Date();
    this.date.year = date.getYear();
    this.date.week = date.get(weekFields.weekOfWeekBasedYear());
  }

  public void setStockPrice(AlphaVantageStock stockPrice) {
    this.close = stockPrice.close;
  }
}
