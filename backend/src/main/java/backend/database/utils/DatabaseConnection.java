package backend.database.utils;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class DatabaseConnection {

    private static EntityManagerFactory entityManagerFactory = null;

    private DatabaseConnection(){}

    public static EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
        }
        return entityManagerFactory;
    }
}
