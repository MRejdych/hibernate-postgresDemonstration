package app.dataAccessObjects;

import app.utils.DatabaseUtils;
import app.utils.Statistics;
import app.utils.StatisticsKeeper;
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

    public EntitiesDAO(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
    }

    //C R U D
    public abstract void create(T entity);

    public abstract void createUsingNativeSql(Model model);

    public abstract List<T> readAll();

    public abstract List<T> readAllUsingNativeSql();

    public abstract T readById(short id);

    public abstract T readByIdUsingNativeSql(short id);

    public abstract void update(T updatedEntity, short id);

    public abstract void updateUsingNativeSql(short id, Model model);

    public abstract void delete(short id);

    public abstract void deleteUsingNativeSql(short id);


    protected void executeQueryAndSaveStatistics(Runnable query) {
        try {
            prepareConnectionToDB();
            query.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            appendStatistics();
            closeConnectionToDB();
        }
    }

    protected <V> V executeQueryAndSaveStatistics(Supplier<V> query) {
        try {
            prepareConnectionToDB();
            return query.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            appendStatistics();
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


    private void appendStatistics(){
        org.hibernate.stat.Statistics stats = dbutils.getStatistics();
        StatisticsKeeper.saveStatistics(new Statistics(stats));
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
