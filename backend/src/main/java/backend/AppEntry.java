package backend;


import backend.database.entities.Region;
import backend.database.utils.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

//@SpringBootApplication
public class AppEntry {
    public static void main(final String[] args) {
        EntityManagerFactory emf = DatabaseConnection.getEntityManagerFactory();
        System.out.println("TEEEEEEST " + emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        tr.begin();

        em.persist(new Region("bla bla"));

        tr.commit();

        em.close();
        //SpringApplication.run(AppEntry.class, args);

    }
}

