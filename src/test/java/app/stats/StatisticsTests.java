package app.stats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StatisticsTests {
    private final long TEST_START_TIME = 10L;
    private final long TEST_TOTAL_EXECUTED_QUERIES = 2L;
    private org.hibernate.stat.Statistics hibernateStatistics;


    @Before
    public void before(){
        hibernateStatistics = mock(org.hibernate.stat.Statistics.class);
    }

    @Test
    public void testStatisticsConstruction(){
        when(hibernateStatistics.getStartTime()).thenReturn(TEST_START_TIME);
        when(hibernateStatistics.getPrepareStatementCount()).thenReturn(TEST_TOTAL_EXECUTED_QUERIES);


        Statistics testStatistics = new Statistics(hibernateStatistics, 2000L);

        Assert.assertEquals(TEST_START_TIME, testStatistics.getTimestamp());
        Assert.assertEquals(TEST_TOTAL_EXECUTED_QUERIES, testStatistics.getNumberOfExecutedQueries());
        Assert.assertEquals(2000L, testStatistics.getExecutionTime());
    }
}
