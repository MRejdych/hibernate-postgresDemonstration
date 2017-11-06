package app.utils;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class DatabaseConnection {

    private static EntityManagerFactory entityManagerFactory = null;
    private EntityManager em;
    private EntityTransaction tr;
    private boolean connOpened;


    public DatabaseConnection() {
        connOpened = false;
        em = null;
        tr = null;
    }

    private static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
        }
        return entityManagerFactory;
    }

    public void openConnection() {
        if (!connOpened) {
            em = getEntityManagerFactory().createEntityManager();
            tr = em.getTransaction();
            tr.begin();
            connOpened = true;
        }
    }

    public void closeConnection() {
        if (connOpened) {
            tr.commit();
            em.close();
            em = null;
            tr = null;
            connOpened = false;
        }
    }

    public EntityManager getEntityManager() {
        if (connOpened && em.isOpen()) return em;
        else return null;

    }
}
