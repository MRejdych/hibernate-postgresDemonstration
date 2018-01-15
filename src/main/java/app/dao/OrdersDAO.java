package app.dao;

import app.entities.Order;
import app.utils.DatabaseUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class OrdersDAO {
    protected DatabaseUtils dbutils;
    protected EntityManager em;


    public OrdersDAO(DatabaseUtils dbutils) {
        this.dbutils = dbutils;
    }

    public void create(Order order) {
        prepareConnectionToDB();
        em.persist(order);
        closeConnectionToDB();
    }

    public List<Order> selectFromDatePeriod(LocalDate from, LocalDate to){
        prepareConnectionToDB();

        TypedQuery<Order> generatedQuery = em.createQuery(
                "SELECT o " +
                        "FROM Order o " +
                        "JOIN FETCH o.customer " +
                        "JOIN FETCH o.employee " +
                        "JOIN FETCH o.shipVia " +
                        "JOIN FETCH o.orderDetails od " +
                        "WHERE o.orderDate >= ?1 AND o.orderDate <= ?2 " +
                        "ORDER BY o.orderId",
                Order.class);

        generatedQuery.setParameter(1, from);
        generatedQuery.setParameter(2, to);

        List<Order> result = generatedQuery.getResultList();

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
