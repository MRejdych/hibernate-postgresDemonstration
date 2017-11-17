package app.dataAccessObjects;

import app.utils.DatabaseUtils;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class EntitiesHelper <T> {
    protected DatabaseUtils dbutils;
    protected EntityManager em;
    boolean initialized;


    public EntitiesHelper(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
        this.initialized = false;
    }

    public abstract List<T> selectAll();


    protected void prepareConnectionToDB() {
        cleanStateOfHelper();
        dbutils.openConnection();
        em = dbutils.getEntityManager();
    }

    protected void closeConnectionToDB() {
        dbutils.closeConnection();
        em = null;
        initialized = true;
    }

    private void cleanStateOfHelper(){
        if(initialized){
            initialized = false;
        }
    }
}
