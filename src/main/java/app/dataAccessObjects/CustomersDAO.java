package app.dataAccessObjects;

import app.constants.CustomersDbFields;
import app.entities.Address;
import app.entities.Customer;
import app.utils.AddressBuilder;
import app.utils.CustomerBuilder;
import app.utils.DatabaseUtils;
import org.springframework.ui.Model;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import static app.constants.CustomersDbFields.*;


public class CustomersDAO extends EntitiesDAO<Customer> {


    public CustomersDAO(DatabaseUtils dbutils) {
        super(dbutils);
    }


    @Override
    public void create(Customer customer){
        executeQueryAndSaveStatistics(() -> {
            em.persist(customer);
        });
    }

    @Override
    public List<Customer> readAll() {
        return executeQueryAndSaveStatistics(
                () -> {
                    TypedQuery<Customer> generatedQuery = em.createQuery("SELECT c FROM Customer c ORDER BY c.customerId", Customer.class);
                    return generatedQuery.getResultList();
                });
    }


    @Override
    public Customer readById(short customerId){
        return executeQueryAndSaveStatistics(() ->  em.find(Customer.class, customerId));
    }

    @Override
    public void update(Customer updatedCustomer, short customerId){

        executeQueryAndSaveStatistics(() -> {
            Customer customer = em.find(Customer.class, customerId);
            if(customer != null)
                customer.copyStateOfAnotherCustomer(updatedCustomer);
        });
    }


    @Override
    public void delete(short customerId){

        executeQueryAndSaveStatistics(() -> {
            Customer c = em.find(Customer.class, customerId);
            if(c != null)  em.remove(c);
            System.out.println(em.contains(c));
        });
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public void createUsingNativeSql(Model model) {
        executeQueryAndSaveStatistics(() -> {

            Map<String, Object> attributesMap = model.asMap();
            Set<String> legalTableColumns = CustomersDbFields.asSet();

            StringJoiner columnsJoiner = new StringJoiner(", ");
            StringJoiner valuesJoiner = new StringJoiner(", ");

            attributesMap.entrySet()
                    .stream()
                    .filter(e -> legalTableColumns.contains(e.getKey()))
                    .forEach(mapEntry -> {
                        columnsJoiner.add(mapEntry.getKey());
                        valuesJoiner.add((String) mapEntry.getValue());
                    });

            String columns = columnsJoiner.toString();
            String values = valuesJoiner.toString();

            String query = new StringBuilder()
                    .append("INSERT into customers(")
                    .append(columns)
                    .append(") VALUES (")
                    .append(values)
                    .append(");")
                    .toString();


            Query nativeQuery = em.createNativeQuery(query, Customer.class);

            nativeQuery.executeUpdate();

        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> readAllUsingNativeSql() {
        return executeQueryAndSaveStatistics(() -> {

            Query nativeQuery = em.createNativeQuery("Select * From customers ORDER BY customer_id", Customer.class);

            return (List<Customer>) nativeQuery.getResultList();
        });
    }

    @Override
    public Customer readByIdUsingNativeSql(short customerId) {
        return executeQueryAndSaveStatistics(() -> {
            Query nativeQuery = em.createNativeQuery("Select * From customers Where customer_id = ?1", Customer.class);
            nativeQuery.setParameter(1, customerId);

            return (Customer) nativeQuery.getSingleResult();
        });
    }



    @SuppressWarnings("Duplicates")
    @Override
    public void updateUsingNativeSql(short customerId, Model model) {

        executeQueryAndSaveStatistics(() -> {

            Map<String, Object> attributesMap = model.asMap();
            Set<String> legalTableColumns = CustomersDbFields.asSet();

            String queryPrefix = "UPDATE customers SET ";
            String querySuffix = " WHERE customer_id = ?1;";

            StringBuilder queryBuilder = new StringBuilder(queryPrefix);

            queryBuilder.append(COMPANY_NAME + " = ").append(attributesMap.get(COMPANY_NAME));

            attributesMap.entrySet()
                    .stream()
                    .filter(e -> legalTableColumns.contains(e.getKey()))
                    .forEach(mapEntry -> {
                        String key = mapEntry.getKey();
                        String value = (String) mapEntry.getValue();
                        if (!key.equals(COMPANY_NAME))
                            queryBuilder.append(", ").append(key).append(" = ").append(value);
                    });

            queryBuilder.append(querySuffix);

            String query = queryBuilder.toString();

            Query nativeQuery = em.createNativeQuery(query, Customer.class);
            nativeQuery.setParameter(1, customerId);

            nativeQuery.executeUpdate();

        });

    }


    @Override
    public void deleteUsingNativeSql(short customerId) {

        executeQueryAndSaveStatistics(() -> {
            Query nativeQuery = em.createNativeQuery("DELETE FROM customers WHERE customer_id = ?1", Customer.class);
            nativeQuery.setParameter(1, customerId);

            nativeQuery.executeUpdate();
        });
    }
}
