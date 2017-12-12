package app.stats;

import java.util.*;

public class StatisticsKeeper {
    private static Map<StatisticType, List<Statistics>> statisticsMap = new HashMap<>();

    static {
        EnumSet.allOf(StatisticType.class).forEach(type -> statisticsMap.put(type, new LinkedList<Statistics>()));
    }

    private StatisticsKeeper(){}

    public static void saveStatistics(Statistics statistics, StatisticType type){
        statisticsMap.get(type).add(statistics);
    }


    public static Map<StatisticType, List<Statistics>> getStatisticsMap() {
        return Collections.unmodifiableMap(statisticsMap);
    }

    public static void clearStatistics(){
        EnumSet.allOf(StatisticType.class).forEach(type -> statisticsMap.get(type).clear());
    }
}
