package com.compound.service;

import com.compound.request.json.compound.CompoundJsonDate;
import com.compound.request.json.compound.CompoundJsonRoot;
import com.compound.request.json.compound.CompoundJsonStock;
import com.compound.request.json.compound.CompoundStats;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.inference.OneWayAnova;

import java.io.*;
import java.util.*;

public class CompoundStatsService {
    public CompoundStats buildRequest(String type, String symbol, String function) throws IOException {
        YearOverYearService yearOverYearService = new YearOverYearService();
        CompoundJsonRoot compoundJsonRoot = (CompoundJsonRoot) yearOverYearService.buildRequest(type, symbol, function);

        Collection<SummaryStatistics> stockYearStats = collectStats(compoundJsonRoot);


        CompoundStats compoundStats = new CompoundStats();
        compoundStats.setOneWayAnova( new OneWayAnova().anovaPValue(stockYearStats,false));
        return compoundStats;
    }

    Collection<SummaryStatistics> collectStats(CompoundJsonRoot compoundJsonRoot) {
       SummaryStatistics[] weekStatistics = new SummaryStatistics[53];
        compoundJsonRoot.Year.forEach((Integer year, CompoundJsonDate date) -> {
            date.WeekOfYearToStock.forEach((Integer week, CompoundJsonStock stock) -> {
                week = week - 1;
                if (weekStatistics[week] == null) weekStatistics[week] = new SummaryStatistics();
                weekStatistics[week].addValue(stock.value);
            });
        });

        return Arrays.asList(weekStatistics);
    }


}
