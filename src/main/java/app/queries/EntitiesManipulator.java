package app.queries;

import app.utils.DatabaseUtils;
import org.hibernate.stat.Statistics;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

@SuppressWarnings("WeakerAccess")
public abstract class EntitiesManipulator <T> {
    protected long JPQLqueryTime;
    protected long nativeSqlQueryTime;
    protected DatabaseUtils dbutils;
    protected EntityManager em;
    boolean initialized;

    public EntitiesManipulator(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
        this.initialized = false;
    }


    public abstract List<T> selectAll();

    public abstract T selectById(short id);

    public abstract void add(Model model);

    public abstract void update(short id, Model model);

    public abstract void remove(short id);

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


    protected long executeQuery(Callable<T> query, EntityKeeper ek) {
        try {
            ek.storeEntity(query.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return measureQueryExecutionTime();
    }


    protected long executeQuery(Callable<List<T>> query, List<T> result) {
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
        System.out.println(stats + "\n\n");
        dbutils.clearStatistics();
        dbutils.clearCache();
        System.out.println(stats + "\n\n");
        return queryExecutionTime;
    }


    protected class EntityKeeper {
        private T entity;

        public void storeEntity(T it){
            entity = it;
        }

        public T getEntity(){
            return entity;
        }
    }


    protected class AttributesSettingHelper {
        private Object value;
        private Map<String, Object> attributesMap;

        protected AttributesSettingHelper(Map<String, Object> map){
            this.attributesMap = map;
        }

        protected AttributesSettingHelper ifMapContainsKey(String key){
            value = null;
            value = attributesMap.get(key);
            return this;
        }

        @SuppressWarnings("unchecked")
        protected <V> AttributesSettingHelper thenSetValueOf(Consumer<V> consumer){
            if(value != null){
                consumer.accept((V) value);
            }
            value = null;
            return this;
        }

        protected void end(){
            attributesMap = null;
            value = null;
        }
    }
}
