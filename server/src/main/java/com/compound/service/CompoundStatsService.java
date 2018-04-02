package com.compound.service;

import com.compound.request.json.compound.Statistics;
import com.compound.request.json.compound.StockSnapshot;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.inference.OneWayAnova;

import java.io.*;
import java.util.*;

public class CompoundStatsService {
    public Statistics buildRequest(String type, String symbol, String function) throws IOException {
        YearOverYearService yearOverYearService = new YearOverYearService();
        Collection<StockSnapshot>  stock = (Collection<StockSnapshot>) yearOverYearService.buildRequest(type, symbol, function);

        Collection<SummaryStatistics> stockYearStats = collectStats(stock);


        Statistics compoundStats = new Statistics();
        compoundStats.setOneWayAnova( new OneWayAnova().anovaPValue(stockYearStats,false));
        return compoundStats;
    }

    private List<SummaryStatistics> collectStats(Collection<StockSnapshot>  StockSnapshots) {
        SummaryStatistics[] weekStatistics = new SummaryStatistics[53];
        StockSnapshots.forEach( stockSnapshot -> {
           int week = stockSnapshot.date.week - 1;
           if (weekStatistics[week] == null) weekStatistics[week] = new SummaryStatistics();
           weekStatistics[week].addValue(stockSnapshot.close);
        });

        return Arrays.asList(weekStatistics);
    }


}
