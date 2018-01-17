package app.dao;

import app.entities.OrderDetails;
import app.utils.DatabaseUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDetailsDAO /*implements DAO<OrderDetails>*/ {
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
/*
    //@Override
    public List<OrderDetails> readAll() {
        prepareConnectionToDB();
        TypedQuery<OrderDetails> generatedQuery = em.createQuery("SELECT c FROM Customer c ORDER BY c.customerId", OrderDetails.class);
        List<OrderDetails> result = generatedQuery.getResultList();
        closeConnectionToDB();
        return result;
    }




    //@Override
    public OrderDetails readById(short customerId) {
        prepareConnectionToDB();
        OrderDetails result = em.find(OrderDetails.class, customerId);
        closeConnectionToDB();
        return result;
    }

    //@Override
    public void update(OrderDetails updatedOrderDetails, short order) {
    }


    //@Override
    public void delete(short customerId) {
        prepareConnectionToDB();
        OrderDetails c = em.find(OrderDetails.class, customerId);
        if (c != null) em.remove(c);
        closeConnectionToDB();
    }
*/
    private void prepareConnectionToDB() {
        dbutils.openConnection();
        em = dbutils.getEntityManager();
    }

    private void closeConnectionToDB() {
        dbutils.closeConnection();
        em = null;
    }
}
