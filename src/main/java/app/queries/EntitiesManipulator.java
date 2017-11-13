package app.queries;

import app.utils.DatabaseUtils;
import org.hibernate.stat.Statistics;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("WeakerAccess")
public abstract class EntitiesManipulator {
    protected long JPQLqueryTime;
    protected long nativeSqlQueryTime;
    protected DatabaseUtils dbutils;
    protected EntityManager em;
    boolean initialized;

    public EntitiesManipulator(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
        this.initialized = false;
    }

    public long getJPQLqueryTime() {
        return JPQLqueryTime;
    }


    public long getNativeSqlQueryTime() {
        return nativeSqlQueryTime;
    }

    protected long executeQuery(Runnable query) {
        query.run();
        return measureQueryExecutionTime();
    }


    protected <T> long executeQuery(Callable<T> query, EntityKeeper<T> ek) {
        try {
            ek.storeEntity(query.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return measureQueryExecutionTime();
    }


    protected <T> long executeQuery(Callable<List<T>> query, List<T> result) {
        try {
            result.addAll(query.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return measureQueryExecutionTime();
    }

    protected void prepareConnectionToDB() {
        cleanStateOfManipulator();
        dbutils.openConnection();
        em = dbutils.getEntityManager();
    }

    protected void closeConnectionToDB() {
        dbutils.closeConnection();
        em = null;
        initialized = true;
    }

    private void cleanStateOfManipulator(){
        if(initialized){
            JPQLqueryTime = 0L;
            nativeSqlQueryTime = 0L;
            initialized = false;
        }
    }

    private long measureQueryExecutionTime(){
        Statistics stats = dbutils.getStatistics();
        long queryExecutionTime = stats.getQueryExecutionMaxTime();
        dbutils.clearStatistics();
        dbutils.clearCache();
        return queryExecutionTime;
    }


    protected class EntityKeeper <T> {
        T entity;

        public void storeEntity(T it){
            entity = it;
        }

        public T getEntity(){
            return entity;
        }
    }
}
