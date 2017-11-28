package app.utils;

import java.util.*;

public class StatisticsKeeper {
    private static List<Statistics> statisticsList = new LinkedList<>();
    private static Map<StatisticType, List<Statistics>> statisticsMap = new HashMap<>();

    static {
        EnumSet.allOf(StatisticType.class)
    }

    private StatisticsKeeper(){}

    public static void saveStatistics(Statistics statistics){
        statisticsList.add(statistics);
    }

    public static List<Statistics> getStatisticsList() {
        return Collections.unmodifiableList(statisticsList);
    }
}
