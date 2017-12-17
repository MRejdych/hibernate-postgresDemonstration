package app.dao;

import app.stats.StatisticType;
import app.stats.Statistics;
import app.stats.StatisticsKeeper;
import app.utils.DatabaseUtils;
import app.utils.Timer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public abstract class EntitiesDAO<T> {
    protected DatabaseUtils dbutils;
    protected EntityManager em;
    private Timer timer;
    private Boolean statisticsGenerationMode;

    public EntitiesDAO(DatabaseUtils dbutils) {
        this(dbutils, false);
    }

    public EntitiesDAO(DatabaseUtils dbutils, Boolean statisticsGenerationMode) {
        this.dbutils = dbutils;
        this.statisticsGenerationMode = statisticsGenerationMode;
        if(statisticsGenerationMode){
            timer = new Timer();
        }
    }

    //C R U D
    public abstract void create(T entity);

    public abstract void createUsingNativeSql(T entity);

    public abstract List<T> readAll();

    public abstract List<T> readAllUsingNativeSql();

    public abstract T readById(short id);

    public abstract T readByIdUsingNativeSql(short id);

    public abstract void update(T updatedEntity, short id);

    public abstract void updateUsingNativeSql(T updatedEntity, short id);

    public abstract void delete(short id);

    public abstract void deleteUsingNativeSql(short id);


    protected void executeQueryAndSaveStatistics(Runnable query, StatisticType statisticType) {
        try {
            prepareConnectionToDB();
            startTimer();
            query.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            long duration = stopTimer();
            appendStatistics(statisticType, duration);
            closeConnectionToDB();
        }
    }

    protected <V> V executeQueryAndSaveStatistics(Supplier<V> query, StatisticType statisticType) {
        try {
            prepareConnectionToDB();
            startTimer();
            return query.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            long duration = stopTimer();
            appendStatistics(statisticType, duration);
            closeConnectionToDB();
        }
    }


    private void prepareConnectionToDB() {
        dbutils.openConnection();
        em = dbutils.getEntityManager();
    }

    private void closeConnectionToDB() {
        dbutils.closeConnection();
        em = null;
    }

    private void startTimer() {
        if(statisticsGenerationMode) {
            timer.startTimer();
        }
    }

    private long stopTimer() {
        if(statisticsGenerationMode) {
            return timer.stopTimerAndGetQueryTimeInMiliseconds();
        } else {
            return 0L;
        }
    }

    private void appendStatistics(StatisticType statisticType, long duration) {
        if(statisticsGenerationMode) {
            org.hibernate.stat.Statistics stats = dbutils.getStatistics();
            StatisticsKeeper.saveStatistics(new Statistics(stats, duration), statisticType);
        }
    }
}
