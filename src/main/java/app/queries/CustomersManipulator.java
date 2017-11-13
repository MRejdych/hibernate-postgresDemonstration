package app.queries;

import app.entities.Customer;
import app.utils.DatabaseUtils;
import org.springframework.ui.Model;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class CustomersManipulator extends EntitiesManipulator {


    public CustomersManipulator(DatabaseUtils dbutils) {
        super(dbutils);
    }

    public List<Customer> selectAllCustomers() {
        prepareConnectionToDB();
        List<Customer> customers = new ArrayList<>();

        Query nativeQuery = em.createNativeQuery("Select * From customers", Customer.class);
        TypedQuery<Customer> generatedQuery = em.createQuery("SELECT c FROM Customer c", Customer.class);

        nativeSqlQueryTime = executeQuery(nativeQuery::getResultList);
        JPQLqueryTime = executeQuery(generatedQuery::getResultList, customers);

        closeConnectionToDB();
        return customers;
    }

    public Customer selectCutomerById(short customerId){
        prepareConnectionToDB();
        EntityKeeper<Customer> keeper = new EntityKeeper<>();

        Query nativeQuery = em.createNativeQuery("Select * From customers Where customer_id = ?1", Customer.class);
        nativeQuery.setParameter(1, customerId);

        nativeSqlQueryTime = executeQuery(nativeQuery::getSingleResult);
        JPQLqueryTime = executeQuery(() -> em.find(Customer.class, customerId), keeper);

        closeConnectionToDB();
        return keeper.getEntity();
    }

    public void deleteCustomerById(short customerId){
        prepareConnectionToDB();

        Query nativeQuery = em.createNativeQuery("DELETE FROM customers WHERE customer_id = ?1", Customer.class);
        nativeQuery.setParameter(1, customerId);

        nativeSqlQueryTime = executeQuery(nativeQuery::executeUpdate);
        JPQLqueryTime = executeQuery(() -> {
            Customer c = em.find(Customer.class, customerId);
            if(c != null) em.remove(c);
        });

    }


    public void deleteCustomer(){

    }


    public void updateCustomer(short customerId){


    }

    public void addCustomer(Model model){

    }
}
