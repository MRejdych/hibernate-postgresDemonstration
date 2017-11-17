package app.dataAccessObjects;

import app.utils.DatabaseUtils;
import org.hibernate.stat.Statistics;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public abstract class EntitiesDAO<T> {
    protected DatabaseUtils dbutils;
    protected EntityManager em;
    boolean initialized;

    public EntitiesDAO(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
        this.initialized = false;
    }


    public abstract List<T> selectAll();

    public abstract List<T> selectAllUsingNativeSql();

    public abstract T selectById(short id);

    public abstract T selectByIdUsingNativeSql(short id);

    public abstract void add(Model model);

    public abstract void addUsingNativeSql(Model model);

    public abstract void update(short id, Model model);

    public abstract void updateUsingNativeSql(short id, Model model);

    public abstract void remove(short id);

    public abstract void removeUsingNativeSql(short id);


    protected void executeQuery(Runnable query) {
        try {
            prepareConnectionToDB();
            query.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            closeConnectionToDB();
        }
    }

    protected <V> V executeQuery(Supplier<V> query) {
        try {
            prepareConnectionToDB();
            return query.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
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
        initialized = true;
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
        protected <V> AttributesSettingHelper then(Consumer<V> consumer){
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
