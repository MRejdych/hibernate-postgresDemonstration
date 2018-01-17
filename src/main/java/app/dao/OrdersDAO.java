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

    public List<Order> selectById(short orderId) {
        prepareConnectionToDB();

        TypedQuery<Order> generatedQuery = em.createQuery(
                "SELECT o " +
                        "FROM Order o " +
                        "JOIN FETCH o.customer " +
                        "JOIN FETCH o.employee " +
                        "JOIN FETCH o.shipVia " +
                        "WHERE o.orderId = ?1 ",
                Order.class);

        generatedQuery.setParameter(1, orderId);



        List<Order> orders = generatedQuery.getResultList();



        closeConnectionToDB();
        return orders;
    }


    public List<Order> selectFromDatePeriod(LocalDate from, LocalDate to) {
        if (from == null) from = LocalDate.of(1000, 12, 1);
        if (to == null) to = LocalDate.of(4000, 12, 1);
        prepareConnectionToDB();
        TypedQuery<Order> generatedQuery;

        generatedQuery = em.createQuery(
                "SELECT o " +
                        "FROM Order o " +
                        "JOIN FETCH o.customer " +
                        "JOIN FETCH o.employee " +
                        "JOIN FETCH o.shipVia " +
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
