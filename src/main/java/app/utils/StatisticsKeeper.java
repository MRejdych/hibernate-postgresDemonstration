package app.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StatisticsKeeper {
    private static List<Statistics> statisticsList = new LinkedList<>();

    private StatisticsKeeper(){}

    public static void saveStatistics(Statistics statistics){
        statisticsList.add(statistics);
    }

    public static List<Statistics> getStatisticsList() {
        return Collections.unmodifiableList(statisticsList);
    }
}
