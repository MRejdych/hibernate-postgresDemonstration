package app.utils;


import org.hibernate.Session;
import org.hibernate.stat.Statistics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DatabaseUtils {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private boolean connOpened;


    public DatabaseUtils() {
        connOpened = false;
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("entityManager");
        }
    }


    public void openConnection() {
        if (!connOpened) {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            connOpened = true;
        }
    }

    public void closeConnection() {
        if (connOpened) {
            em.flush();
            em.getTransaction().commit();
            clearStatistics();
            em.close();
            clearCache();
            em = null;
            connOpened = false;
        }
    }
    public void clearStatistics(){
        getStatistics().clear();
    }
    public void clearCache(){
        emf.getCache().evictAll();
    }

    public Statistics getStatistics(){
        return em.unwrap(Session.class).getSessionFactory().getStatistics();
    }

    public EntityManager getEntityManager() {
        if (connOpened && em.isOpen()) return em;
        else return null;
    }

    public void resetDatabaseToInitialState() throws IOException {
        boolean connWasOpened = true;
        if(!connOpened) {
            connWasOpened = false;
            openConnection();
        }

        Scanner scan = new Scanner(Paths.get("./postgres/northwind.postgre.sql"));
        scan.useDelimiter(Pattern.compile(";"));
        while(scan.hasNext()){
            String query = scan.next().trim();
            em.createNativeQuery(query)
                    .executeUpdate();
        }

        clearStatistics();
        if(!connWasOpened) closeConnection();
    }
}
