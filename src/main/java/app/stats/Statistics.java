package app.stats;


public class Statistics {
    private final long timestamp;
    private final long numberOfExecutedQueries;
    private final long executionTime;

    public Statistics(org.hibernate.stat.Statistics statistics, long duration){
        timestamp = statistics.getStartTime();
        numberOfExecutedQueries = statistics.getPrepareStatementCount();
        executionTime = duration;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public long getNumberOfExecutedQueries() {
        return numberOfExecutedQueries;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "timestamp=" + timestamp +
                ", numberOfExecutedQueries=" + numberOfExecutedQueries +
                ", executionTime=" + executionTime +
                '}';
    }
}

