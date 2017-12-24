package com.compound.stock;

public class StockSummary {

  public String symbol,name,sector,industry;
  public StockSummary(String symbol, String name, String sector, String industry){
    this.sector = sector;
    this.symbol = symbol;
    this.name = name;
    this.industry = industry;
  }
}
