package app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppEntry {
    public static void main(final String[] args) {

        SpringApplication.run(AppEntry.class, args);


    }

    public static void main2(String[] args) {
        /*EntityManagerFactory emf = DatabaseConnection.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        tr.begin();

        em.persist(new Region("Test data"));

        tr.commit();

        em.close();*/

    }
}

