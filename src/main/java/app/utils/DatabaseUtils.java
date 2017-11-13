package app.utils;


import org.hibernate.Session;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class DatabaseConnection {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tr;
    private boolean connOpened;
    private QueryStatistics statistics;


    public DatabaseConnection() {
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
            wipeStatistics();
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
            saveStatistics();
            em.close();
            emf.close();
            tr = null;
            em = null;
            emf = null;
            connOpened = false;
        }
    }

    private void saveStatistics(){
        if(em != null) {
            Statistics allStatistics = em.unwrap(Session.class).getSessionFactory().getStatistics();
            String[] executedQueries = allStatistics.getQueries();
            statistics = allStatistics.getQueryStatistics(executedQueries[0]);
        }
    }

    private void wipeStatistics(){
        statistics = null;
    }

    public QueryStatistics getStatistics() {
        return statistics;
    }

    public EntityManager getEntityManager() {
        if (connOpened && em.isOpen()) return em;
        else return null;

    }
}
