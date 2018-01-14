package app.dao;

import app.entities.Customer;
import app.entities.Product;
import app.utils.DatabaseUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static app.stats.StatisticType.*;


public class CustomersDAO extends EntitiesDAO<Customer> {


    public CustomersDAO(DatabaseUtils dbutils) {
        super(dbutils);
    }

    public CustomersDAO(DatabaseUtils dbutils, Boolean statisticsGenerationMode) {
        super(dbutils, statisticsGenerationMode);
    }


    @Override
    public void create(Customer customer){
        executeQueryAndSaveStatistics(() -> {
            em.persist(customer);
        }, CREATE);
    }

    @Override
    public List<Customer> readAll() {
        return executeQueryAndSaveStatistics(
                () -> {
                    TypedQuery<Customer> generatedQuery = em.createQuery("SELECT c FROM Customer c ORDER BY c.customerId", Customer.class);
                    return generatedQuery.getResultList();
                }, READ);
    }


    @Override
    public Customer readById(short customerId){
        TypedQuery<Customer> generatedQuery = em.createQuery("SELECT c FROM Customer c WHERE c.customerId = ?1", Customer.class);
        generatedQuery.setParameter(1, customerId);

        return executeQueryAndSaveStatistics(generatedQuery::getSingleResult, READ);
    }

    @Override
    public void update(Customer updatedCustomer, short customerId){

        executeQueryAndSaveStatistics(() -> {
            Customer customer = em.find(Customer.class, customerId);
            if(customer != null)
                customer.copyStateOfAnotherCustomer(updatedCustomer);
        }, UPDATE);
    }


    @Override
    public void delete(short customerId){
        TypedQuery<Customer> generatedQuery = em.createQuery("DELETE FROM Customer c WHERE c.customerId = ?1", Customer.class);
        generatedQuery.setParameter(1, customerId);
        executeQueryAndSaveStatistics(generatedQuery::executeUpdate, DELETE);
    }

    @Override
    public void createUsingNativeSql(Customer customer) {
        String query =
                "INSERT into customers(company_name, contact_name, contact_title, address, city, postal_code, country, region, phone, fax)" +
                        "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10);";

        Query nativeQuery = em.createNativeQuery(query, Customer.class);
        nativeQuery.setParameter(1, customer.getCompanyName())
                .setParameter(2, customer.getContactName())
                .setParameter(3, customer.getContactTitle())
                .setParameter(4, customer.getAddress().getAddress())
                .setParameter(5, customer.getAddress().getCity())
                .setParameter(6, customer.getAddress().getPostalCode())
                .setParameter(7, customer.getAddress().getCountry())
                .setParameter(8, customer.getRegion())
                .setParameter(9, customer.getPhone())
                .setParameter(10, customer.getFax());

        executeQueryAndSaveStatistics(nativeQuery::executeUpdate, NATIVE_CREATE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> readAllUsingNativeSql() {
        Query nativeQuery = em.createNativeQuery("Select * From customers ORDER BY customer_id", Customer.class);

        return executeQueryAndSaveStatistics(() -> (List<Customer>) nativeQuery.getResultList(), NATIVE_READ);
    }

    @Override
    public Customer readByIdUsingNativeSql(short customerId) {
        Query nativeQuery = em.createNativeQuery("Select * From customers Where customer_id = ?1", Customer.class);
        nativeQuery.setParameter(1, customerId);

        return executeQueryAndSaveStatistics(() -> (Customer) nativeQuery.getSingleResult(), NATIVE_READ);
    }



    @Override
    public void updateUsingNativeSql(Customer updatedCustomer, short customerId) {
        String query =
                "UPDATE customers SET company_name = (?1), contact_name = (?2), contact_title = (?3), address = (?4), " +
                        "city = (?5), postal_code = (?6), country = (?7), region = (?8), phone = (?9), fax = (?10) " +
                        "WHERE customer_id = (?11);";


        Query nativeQuery = em.createNativeQuery(query, Customer.class);
        nativeQuery.setParameter(1, updatedCustomer.getCompanyName())
                .setParameter(2, updatedCustomer.getContactName())
                .setParameter(3, updatedCustomer.getContactTitle())
                .setParameter(4, updatedCustomer.getAddress().getAddress())
                .setParameter(5, updatedCustomer.getAddress().getCity())
                .setParameter(6, updatedCustomer.getAddress().getPostalCode())
                .setParameter(7, updatedCustomer.getAddress().getCountry())
                .setParameter(8, updatedCustomer.getRegion())
                .setParameter(9, updatedCustomer.getPhone())
                .setParameter(10, updatedCustomer.getFax())
                .setParameter(11, customerId);

        executeQueryAndSaveStatistics(nativeQuery::executeUpdate, NATIVE_UPDATE);

    }


    @Override
    public void deleteUsingNativeSql(short customerId) {
        Query nativeQuery = em.createNativeQuery("DELETE FROM customers WHERE customer_id = ?1", Customer.class);
        nativeQuery.setParameter(1, customerId);

        executeQueryAndSaveStatistics(nativeQuery::executeUpdate, NATIVE_DELETE);
    }
}
