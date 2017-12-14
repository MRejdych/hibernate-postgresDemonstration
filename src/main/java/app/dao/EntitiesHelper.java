package app.dao;

import app.utils.DatabaseUtils;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class EntitiesHelper <T> {
    protected DatabaseUtils dbutils;
    protected EntityManager em;


    public EntitiesHelper(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
    }

    public abstract List<T> selectAll();

    public abstract T selectById(Short id);

    protected void prepareConnectionToDB() {
        dbutils.openConnection();
        em = dbutils.getEntityManager();
    }

    protected void closeConnectionToDB() {
        dbutils.closeConnection();
        em = null;
    }
}
