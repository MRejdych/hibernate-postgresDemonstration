package app.dataAccessObjects;

import app.stats.StatisticType;
import app.stats.Statistics;
import app.stats.StatisticsKeeper;
import app.utils.DatabaseUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public abstract class EntitiesDAO<T> {
    protected DatabaseUtils dbutils;
    protected EntityManager em;

    public EntitiesDAO(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
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
            query.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            appendStatistics(statisticType);
            closeConnectionToDB();
        }
    }

    protected <V> V executeQueryAndSaveStatistics(Supplier<V> query, StatisticType statisticType) {
        try {
            prepareConnectionToDB();
            return query.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            appendStatistics(statisticType);
            closeConnectionToDB();
        }
    }


    protected void prepareConnectionToDB() {
        dbutils.openConnection();
        em = dbutils.getEntityManager();
    }

    protected void closeConnectionToDB() {
        dbutils.closeConnection();
        em = null;
    }


    private void appendStatistics(StatisticType statisticType){
        org.hibernate.stat.Statistics stats = dbutils.getStatistics();
        StatisticsKeeper.saveStatistics(new Statistics(stats), statisticType);
    }
}
