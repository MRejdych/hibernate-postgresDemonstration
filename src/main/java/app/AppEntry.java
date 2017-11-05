package app;


import app.entities.Region;
import app.utils.DatabaseConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

//@SpringBootApplication
public class AppEntry {
    /*public static void main(final String[] args) {

        SpringApplication.run(AppEntry.class, args);

    }*/

    public static void main(String[] args) {
        EntityManagerFactory emf = DatabaseConnection.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        tr.begin();

        em.persist(new Region("Test data"));

        tr.commit();

        em.close();
    }
}

