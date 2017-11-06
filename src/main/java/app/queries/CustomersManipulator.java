package app.queries;

import app.entities.Customer;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class CustomersManipulator extends EntitiesManipulator {


    public List<Customer> selectAllCustomers() {
        prepareConnectionToDB();
        List<Customer> customers = new ArrayList<>();

        TypedQuery<Customer> generatedQuery = em.createQuery("SELECT c FROM Customer c", Customer.class);
        Query nativeQuery = em.createNativeQuery("Select * From customers", Customer.class);

        JPQLqueryTime = executeQuery(generatedQuery::getResultList, customers);
        nativeSqlQueryTime = executeQuery(nativeQuery::getResultList);


        cleanAndCloseConnectionToDB();
        return customers;
    }

}
