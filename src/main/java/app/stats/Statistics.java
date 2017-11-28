package app.stats;

import java.util.*;

public class Statistics {
    public final long timestamp;
    public final String queryWithMaxExecutionTime;
    public final long numberOfExecutedQueries;
    public final long numberOfDeletedEntities;
    public final long numberOfInsertedEntities;
    public final long numberOfUpdatedEntities;
    public final long numberOfLoadedEntities;
    public final long numberOfFetchedEntities;
    public final long transactionCount;
    public final long successfulTransactionCount;
    public final long prepareStatementCount;
    public final long closeStatementCount;
    private final Map<String, Long> queryToItsExecutionTimeMap;

    public Statistics(org.hibernate.stat.Statistics statistics){
        timestamp = statistics.getStartTime();
        queryWithMaxExecutionTime = statistics.getQueryExecutionMaxTimeQueryString();
        numberOfExecutedQueries = statistics.getQueryExecutionCount();
        numberOfDeletedEntities = statistics.getEntityDeleteCount();
        numberOfInsertedEntities = statistics.getEntityInsertCount();
        numberOfUpdatedEntities = statistics.getEntityUpdateCount();
        numberOfLoadedEntities = statistics.getEntityLoadCount();
        numberOfFetchedEntities = statistics.getEntityFetchCount();

        queryToItsExecutionTimeMap = new HashMap<>();
        List<String> queries = Arrays.asList(statistics.getQueries());
        setQueryStatistics(statistics, queries);

        transactionCount = statistics.getTransactionCount();
        successfulTransactionCount = statistics.getSuccessfulTransactionCount();

        prepareStatementCount = statistics.getPrepareStatementCount();
        closeStatementCount = statistics.getCloseStatementCount();
    }

    private void setQueryStatistics(org.hibernate.stat.Statistics statistics, List<String> queries){
        queries.forEach(it -> queryToItsExecutionTimeMap.put(it, statistics.getQueryStatistics(it).getExecutionAvgTime()));
    }

    public Map<String, Long> getQueryToItsExecutionTimeMap() {
        return Collections.unmodifiableMap(queryToItsExecutionTimeMap);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "\ntimestamp=" + timestamp +
                ",\n queryWithMaxExecutionTime='" + queryWithMaxExecutionTime + '\'' +
                ",\n numberOfExecutedQueries=" + numberOfExecutedQueries +
                ",\n numberOfDeletedEntities=" + numberOfDeletedEntities +
                ",\n numberOfInsertedEntities=" + numberOfInsertedEntities +
                ",\n numberOfUpdatedEntities=" + numberOfUpdatedEntities +
                ",\n numberOfLoadedEntities=" + numberOfLoadedEntities +
                ",\n numberOfFetchedEntities=" + numberOfFetchedEntities +
                ",\n transactionCount=" + transactionCount +
                ",\n successfulTransactionCount=" + successfulTransactionCount +
                ",\n prepareStatementCount=" + prepareStatementCount +
                ",\n closeStatementCount=" + closeStatementCount +
                ",\n queryToItsExecutionTimeMap=" + queryToItsExecutionTimeMap +
                "ms }\n";
    }
}

