package app.stats;

import org.hibernate.stat.QueryStatistics;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StatisticsTests {
    private final long TEST_START_TIME = 10L;
    private final long TEST_TOTAL_EXECUTED_QUERIES = 2L;
    private final String TEST_QUERY_1 = "TEST_QUERY_1";
    private final String TEST_QUERY_2 = "TEST_QUERY_2";
    private final long QUERY_1_AVG_EXEC_TIME = 1L;
    private final long QUERY_2_AVG_EXEC_TIME = 2L;
    private org.hibernate.stat.Statistics hibernateStatistics;
    private QueryStatistics queryStats1;
    private QueryStatistics queryStats2;

    @Before
    public void before(){
        hibernateStatistics = mock(org.hibernate.stat.Statistics.class);
        queryStats1 = mock(QueryStatistics.class);
        queryStats2 = mock(QueryStatistics.class);
    }

    @Test
    public void statisticsTest(){
        String[] queries = new String[2];
        queries[0] = TEST_QUERY_1;
        queries[1] = TEST_QUERY_2;
        Map<String, Long> expectedQueryToTimeMap = new HashMap<>();
        expectedQueryToTimeMap.put(TEST_QUERY_1, QUERY_1_AVG_EXEC_TIME);
        expectedQueryToTimeMap.put(TEST_QUERY_2, QUERY_2_AVG_EXEC_TIME);

        when(queryStats1.getExecutionAvgTime()).thenReturn(QUERY_1_AVG_EXEC_TIME);
        when(queryStats2.getExecutionAvgTime()).thenReturn(QUERY_2_AVG_EXEC_TIME);

        when(hibernateStatistics.getStartTime()).thenReturn(TEST_START_TIME);
        when(hibernateStatistics.getQueryExecutionCount()).thenReturn(TEST_TOTAL_EXECUTED_QUERIES);
        when(hibernateStatistics.getQueries()).thenReturn(queries);
        when(hibernateStatistics.getQueryStatistics(TEST_QUERY_1)).thenReturn(queryStats1);
        when(hibernateStatistics.getQueryStatistics(TEST_QUERY_2)).thenReturn(queryStats2);

        Statistics testStatistics = new Statistics(hibernateStatistics);

        Assert.assertEquals(TEST_START_TIME, testStatistics.getTimestamp());
        Assert.assertEquals(TEST_TOTAL_EXECUTED_QUERIES, testStatistics.getNumberOfExecutedQueries());
        Assert.assertEquals(expectedQueryToTimeMap, testStatistics.getQueryToItsExecutionTimeMap());
    }
}
