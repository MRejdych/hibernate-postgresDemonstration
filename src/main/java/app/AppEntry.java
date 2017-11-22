package app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// create @Repository
@SpringBootApplication
public class AppEntry {
    public static void main(final String[] args) {

        SpringApplication.run(AppEntry.class, args);
       /* DatabaseUtils dbutils = new DatabaseUtils();
        try {
            dbutils.resetDatabaseToInitialState();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
}

