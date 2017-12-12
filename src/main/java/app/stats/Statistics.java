package app.stats;

import java.util.*;

public class Statistics {
    private final long timestamp;
    private final long numberOfExecutedQueries;
    private final Map<String, Long> queryToItsExecutionTimeMap;

    public Statistics(org.hibernate.stat.Statistics statistics){
        timestamp = statistics.getStartTime();
        numberOfExecutedQueries = statistics.getQueryExecutionCount();

        queryToItsExecutionTimeMap = new HashMap<>();
        List<String> queries = Arrays.asList(statistics.getQueries());
        setQueryStatistics(statistics, queries);
    }

    private void setQueryStatistics(org.hibernate.stat.Statistics statistics, List<String> queries){
        queries.forEach(it -> queryToItsExecutionTimeMap.put(it, statistics.getQueryStatistics(it).getExecutionAvgTime()));
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getNumberOfExecutedQueries() {
        return numberOfExecutedQueries;
    }

    public Map<String, Long> getQueryToItsExecutionTimeMap() {
        return Collections.unmodifiableMap(queryToItsExecutionTimeMap);
    }
}

