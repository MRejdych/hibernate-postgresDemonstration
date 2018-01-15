package app.dao;

import app.entities.OrderDetails;
import app.utils.DatabaseUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDetailsDAO {
    protected DatabaseUtils dbutils;
    protected EntityManager em;


    public OrderDetailsDAO(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
    }

    public void create(OrderDetails orderDetails) {
        prepareConnectionToDB();
        em.persist(orderDetails);
        closeConnectionToDB();
    }

    public List<OrderDetails> selectWhereOrderId(short orderId) {
        prepareConnectionToDB();
        TypedQuery<OrderDetails> generatedQuery = em.createQuery(
                "SELECT od " +
                "FROM OrderDetails od " +
                        "WHERE od.orderId == ?1 " +
                        "ORDER BY od.orderId",
                OrderDetails.class);
        generatedQuery.setParameter(1, orderId);
        List<OrderDetails> result = generatedQuery.getResultList();
        closeConnectionToDB();
        return result;
    }

    private void prepareConnectionToDB() {
        dbutils.openConnection();
        em = dbutils.getEntityManager();
    }

    private void closeConnectionToDB() {
        dbutils.closeConnection();
        em = null;
    }
}
