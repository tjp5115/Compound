package com.compound.stock;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

//todo Temporary class -- will be replaced with DB calls.
public class StocksCSV {
  public List<StockSummary> getStocks() throws IOException {
    Reader in = new FileReader("C:\\Users\\tydro\\IdeaProjects\\Compound\\server\\resources\\stocks\\nyse_samples.csv");
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
    List<StockSummary> stockSummarys = new ArrayList<>();
    for(CSVRecord record : records){
      stockSummarys.add(new StockSummary(record.get("Symbol"), record.get("Name"), record.get("Sector"), record.get("industry")));
    }
    return stockSummarys;
  }
}
