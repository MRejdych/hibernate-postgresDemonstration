package app.stats;

import java.util.List;
import java.util.Map;

import static app.stats.StatisticType.*;

public class StatisticsAggregator {
    public final long totalCreateQueriesExecuted;
    public final long totalReadQueriesExecuted;
    public final long totalUpdateQueriesExecuted;
    public final long totalDeleteQueriesExecuted;
    public final long avgCreateQueriesTime;
    public final long avgReadQueriesTime;
    public final long avgUpdateQueriesTime;
    public final long avgDeleteQueriesTime;


    public final long totalNativeCreateQueriesExecuted;
    public final long totalNativeReadQueriesExecuted;
    public final long totalNativeUpdateQueriesExecuted;
    public final long totalNativeDeleteQueriesExecuted;
    public final long avgNativeCreateQueriesTime;
    public final long avgNativeReadQueriesTime;
    public final long avgNativeUpdateQueriesTime;
    public final long avgNativeDeleteQueriesTime;



    public StatisticsAggregator(){
        Map<StatisticType, List<Statistics>> statisticsMap = StatisticsKeeper.getStatisticsMap();
        totalCreateQueriesExecuted = totalQueriesExecuted(statisticsMap.get(CREATE));
        totalReadQueriesExecuted = totalQueriesExecuted(statisticsMap.get(READ));
        totalUpdateQueriesExecuted = totalQueriesExecuted(statisticsMap.get(UPDATE));
        totalDeleteQueriesExecuted = totalQueriesExecuted(statisticsMap.get(DELETE));

        avgCreateQueriesTime = avgTime(statisticsMap.get(CREATE), totalCreateQueriesExecuted);
        avgReadQueriesTime = avgTime(statisticsMap.get(READ), totalReadQueriesExecuted);
        avgUpdateQueriesTime = avgTime(statisticsMap.get(UPDATE), totalUpdateQueriesExecuted);
        avgDeleteQueriesTime = avgTime(statisticsMap.get(DELETE), totalDeleteQueriesExecuted);

        totalNativeCreateQueriesExecuted = totalQueriesExecuted(statisticsMap.get(NATIVE_CREATE));
        totalNativeReadQueriesExecuted = totalQueriesExecuted(statisticsMap.get(NATIVE_READ));
        totalNativeUpdateQueriesExecuted = totalQueriesExecuted(statisticsMap.get(NATIVE_UPDATE));
        totalNativeDeleteQueriesExecuted = totalQueriesExecuted(statisticsMap.get(NATIVE_DELETE));

        avgNativeCreateQueriesTime = avgTime(statisticsMap.get(NATIVE_CREATE), totalNativeCreateQueriesExecuted);
        avgNativeReadQueriesTime = avgTime(statisticsMap.get(NATIVE_READ), totalNativeReadQueriesExecuted);
        avgNativeUpdateQueriesTime = avgTime(statisticsMap.get(NATIVE_UPDATE), totalNativeUpdateQueriesExecuted);
        avgNativeDeleteQueriesTime = avgTime(statisticsMap.get(NATIVE_DELETE), totalNativeDeleteQueriesExecuted);

    }

    private long totalQueriesExecuted(List<Statistics> statistics){
        return statistics.stream().map(it -> it.numberOfExecutedQueries).count();
    }

    private long avgTime(List<Statistics> statistics, long totalQueriesExecuted){
        long totalTime = statistics.stream().map(Statistics::getQueryToItsExecutionTimeMap)
                .map(Map::values).count();
        return totalTime / totalQueriesExecuted;
    }
}
