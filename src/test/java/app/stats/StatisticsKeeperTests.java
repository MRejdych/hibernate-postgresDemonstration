package app.stats;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class StatisticsKeeperTests {
    private List<Statistics> createStatsList;
    private List<Statistics> readStatsList;
    private List<Statistics> updateStatsList;
    private List<Statistics> deleteStatsList;

    private List<Statistics> nativeCreateStatsList;
    private List<Statistics> nativeReadStatsList;
    private List<Statistics> nativeUpdateStatsList;
    private List<Statistics> nativeDeleteStatsList;
    private Map<StatisticType, List<Statistics>> testStatisticsMap;

    @Before
    public void before(){
        StatisticsKeeper.clearStatistics();
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

    @AfterClass
    public static void afterClass(){
        StatisticsKeeper.clearStatistics();
    }

    @Test
    public void testSaveStatistics(){
        Statistics stats1 = mock(Statistics.class);
        Statistics stats2 = mock(Statistics.class);
        Statistics stats3 = mock(Statistics.class);
        Statistics stats4 = mock(Statistics.class);

        readStatsList.add(stats1);
        readStatsList.add(stats2);
        updateStatsList.add(stats3);
        deleteStatsList.add(stats4);

        StatisticsKeeper.saveStatistics(stats1, StatisticType.READ);
        StatisticsKeeper.saveStatistics(stats2, StatisticType.READ);
        StatisticsKeeper.saveStatistics(stats3, StatisticType.UPDATE);
        StatisticsKeeper.saveStatistics(stats4, StatisticType.DELETE);

        Assert.assertEquals(testStatisticsMap, StatisticsKeeper.getStatisticsMap());
    }

    @Test
    public void testClearStatistics(){
        Statistics stats1 = mock(Statistics.class);
        Statistics stats2 = mock(Statistics.class);
        Statistics stats3 = mock(Statistics.class);
        Statistics stats4 = mock(Statistics.class);


        StatisticsKeeper.saveStatistics(stats1, StatisticType.READ);
        StatisticsKeeper.saveStatistics(stats2, StatisticType.READ);
        StatisticsKeeper.saveStatistics(stats3, StatisticType.UPDATE);
        StatisticsKeeper.saveStatistics(stats4, StatisticType.DELETE);

        Assert.assertNotEquals(testStatisticsMap, StatisticsKeeper.getStatisticsMap());

        StatisticsKeeper.clearStatistics();
        Assert.assertEquals(testStatisticsMap, StatisticsKeeper.getStatisticsMap());
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
