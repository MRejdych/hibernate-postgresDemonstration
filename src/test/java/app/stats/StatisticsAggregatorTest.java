package app.stats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatisticsAggregatorTest {
    private final String TEST_QUERY_1 = "TEST_QUERY_1";
    private final String TEST_QUERY_2 = "TEST_QUERY_2";
    private final long QUERY_1_AVG_EXEC_TIME = 2L;
    private final long QUERY_2_AVG_EXEC_TIME = 4L;
    private final long TOTAL_EXEC_READ_QUERIES = 8L;
    private final long READ_QUERIES_AVG_TIME = 3L;


    private Map<StatisticType, List<Statistics>> testStatisticsMap;
    private List<Statistics> createStatsList;
    private List<Statistics> readStatsList;
    private List<Statistics> updateStatsList;
    private List<Statistics> deleteStatsList;

    private List<Statistics> nativeCreateStatsList;
    private List<Statistics> nativeReadStatsList;
    private List<Statistics> nativeUpdateStatsList;
    private List<Statistics> nativeDeleteStatsList;

    @Before
    public void before(){
        createStatsList = new LinkedList<>();
        readStatsList = new LinkedList<>();
        updateStatsList = new LinkedList<>();
        deleteStatsList = new LinkedList<>();

        nativeCreateStatsList = new LinkedList<>();
        nativeReadStatsList = new LinkedList<>();
        nativeUpdateStatsList = new LinkedList<>();
        nativeDeleteStatsList = new LinkedList<>();
        testStatisticsMap = new HashMap<>();
        prepareStatisticsMap();
    }

    @Test
    public void aggregatingTest(){
        String[] queries = new String[2];
        queries[0] = TEST_QUERY_1;
        queries[1] = TEST_QUERY_2;
        Map<String, Long> queryToTimeMap = new HashMap<>();
        queryToTimeMap.put(TEST_QUERY_1, QUERY_1_AVG_EXEC_TIME);
        queryToTimeMap.put(TEST_QUERY_2, QUERY_2_AVG_EXEC_TIME);

        Statistics stats1 = mock(Statistics.class);
        Statistics stats2 = mock(Statistics.class);
        Statistics stats3 = mock(Statistics.class);
        Statistics stats4 = mock(Statistics.class);
        when(stats1.getNumberOfExecutedQueries()).thenReturn(2L);
        when(stats1.getQueryToItsExecutionTimeMap()).thenReturn(queryToTimeMap);
        when(stats2.getNumberOfExecutedQueries()).thenReturn(2L);
        when(stats2.getQueryToItsExecutionTimeMap()).thenReturn(queryToTimeMap);
        when(stats3.getNumberOfExecutedQueries()).thenReturn(2L);
        when(stats3.getQueryToItsExecutionTimeMap()).thenReturn(queryToTimeMap);
        when(stats4.getNumberOfExecutedQueries()).thenReturn(2L);
        when(stats4.getQueryToItsExecutionTimeMap()).thenReturn(queryToTimeMap);

        readStatsList.add(stats1);
        readStatsList.add(stats2);
        readStatsList.add(stats3);
        readStatsList.add(stats4);



        StatisticsAggregator statisticsAggregator = new StatisticsAggregator(testStatisticsMap);



        Assert.assertEquals(TOTAL_EXEC_READ_QUERIES, statisticsAggregator.totalReadQueriesExecuted);
        Assert.assertEquals(READ_QUERIES_AVG_TIME, statisticsAggregator.avgReadQueriesTime);
    }



    private void prepareStatisticsMap(){
        testStatisticsMap.put(StatisticType.CREATE, createStatsList);
        testStatisticsMap.put(StatisticType.READ, readStatsList);
        testStatisticsMap.put(StatisticType.UPDATE, updateStatsList);
        testStatisticsMap.put(StatisticType.DELETE, deleteStatsList);

        testStatisticsMap.put(StatisticType.NATIVE_CREATE, nativeCreateStatsList);
        testStatisticsMap.put(StatisticType.NATIVE_READ, nativeReadStatsList);
        testStatisticsMap.put(StatisticType.NATIVE_UPDATE, nativeUpdateStatsList);
        testStatisticsMap.put(StatisticType.NATIVE_DELETE, nativeDeleteStatsList);
    }
}
