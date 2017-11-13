package app.utils;


import org.hibernate.Session;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class DatabaseUtils {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tr;
    private boolean connOpened;


    public DatabaseUtils() {
        connOpened = false;
    }

    /*private static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("entityManager");
        }
        return emf;
    }*/

    public void openConnection() {
        if (!connOpened) {
            emf = Persistence.createEntityManagerFactory("entityManager");
            em = emf.createEntityManager();
            tr = em.getTransaction();
            tr.begin();
            connOpened = true;
        }
    }

    public void closeConnection() {
        if (connOpened) {
            tr.commit();
            em.close();
            emf.close();
            tr = null;
            em = null;
            emf = null;
            connOpened = false;
        }
    }
    public void clearStatistics(){
        getStatistics().clear();
    }
    public void clearCache(){
        em.clear();
    }

    public Statistics getStatistics(){
        if(em != null && em.isOpen()) {
            return em.unwrap(Session.class).getSessionFactory().getStatistics();
        } else return null;
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

        if(!connWasOpened) closeConnection();
    }
}
