package app.queries;

import app.utils.DatabaseConnection;
import app.utils.Timer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class EntitiesManipulator {
    protected long JPQLqueryTime;
    protected long nativeSqlQueryTime;
    protected Timer timer;
    protected DatabaseConnection dbconn;
    protected EntityManager em;

    public EntitiesManipulator() {
        timer = new Timer();
        dbconn = new DatabaseConnection();
        em = null;
    }

    public long getJPQLqueryTime() {
        return JPQLqueryTime;
    }


    public long getNativeSqlQueryTime() {
        return nativeSqlQueryTime;
    }

    protected long executeQuery(Runnable query) {
        timer.startTimer();
        query.run();
        return timer.stopTimerAndGetQueryTimeInMiliseconds();
    }

    protected <T> long executeQuery(Callable<List<T>> query, List<T> result) {
        timer.startTimer();
        try {
            result.addAll(query.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timer.stopTimerAndGetQueryTimeInMiliseconds();
    }

    protected void prepareConnectionToDB() {
        dbconn.openConnection();
        em = dbconn.getEntityManager();
    }

    protected void cleanAndCloseConnectionToDB() {
        dbconn.closeConnection();
        em = null;
    }
}
