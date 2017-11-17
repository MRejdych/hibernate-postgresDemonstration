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
    public List<Customer> selectAll() {
        return executeQuery(
                () -> {
                    TypedQuery<Customer> generatedQuery = em.createQuery("SELECT c FROM Customer c", Customer.class);
                    return generatedQuery.getResultList();
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> selectAllUsingNativeSql() {
        return executeQuery(() -> {

            Query nativeQuery = em.createNativeQuery("Select * From customers", Customer.class);

            return (List<Customer>) nativeQuery.getResultList();
        });
    }


    @Override
    public Customer selectById(short customerId){
        return executeQuery(() ->  em.find(Customer.class, customerId));
    }

    @Override
    public Customer selectByIdUsingNativeSql(short customerId) {
        return executeQuery(() -> {
            Query nativeQuery = em.createNativeQuery("Select * From customers Where customer_id = ?1", Customer.class);
            nativeQuery.setParameter(1, customerId);

            return (Customer) nativeQuery.getSingleResult();
        });
    }

    @Override
    public void remove(short customerId){

        executeQuery(() -> {
            Customer c = em.find(Customer.class, customerId);
            if(c != null)  em.remove(c);
        });
    }

    @Override
    public void removeUsingNativeSql(short customerId) {

        executeQuery(() -> {
            Query nativeQuery = em.createNativeQuery("DELETE FROM customers WHERE customer_id = ?1", Customer.class);
            nativeQuery.setParameter(1, customerId);

            nativeQuery.executeUpdate();
        });
    }

    @Override
    public void update(short customerId, Model model){

        executeQuery(() -> {
            Customer customer = em.find(Customer.class, customerId);

            new AttributesSettingHelper(model.asMap())
                    .ifMapContainsKey(COMPANY_NAME).then(customer::setCompanyName)
                    .ifMapContainsKey(CONTACT_NAME).then(customer::setContactName)
                    .ifMapContainsKey(CONTACT_TITLE).then(customer::setContactTitle)
                    .ifMapContainsKey(ADDRESS).then(customer.getAddress()::setAddress)
                    .ifMapContainsKey(CITY).then(customer.getAddress()::setCity)
                    .ifMapContainsKey(COUNTRY).then(customer.getAddress()::setCountry)
                    .ifMapContainsKey(POSTAL_CODE).then(customer.getAddress()::setPostalCode)
                    .ifMapContainsKey(PHONE).then(customer::setPhone)
                    .ifMapContainsKey(FAX).then(customer::setFax)
                    .ifMapContainsKey(REGION).then(customer::setRegion)
                    .end();
        });
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void updateUsingNativeSql(short customerId, Model model) {

        executeQuery(() -> {

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
    public void add(Model model){

        executeQuery(() -> {
            Map<String, Object> map = model.asMap();

            Address addressOfCustomer = new AddressBuilder()
                    .withStreet((String) map.getOrDefault(ADDRESS, null))
                    .withCity((String) map.getOrDefault(CITY, null))
                    .withPostalCode((String) map.getOrDefault(POSTAL_CODE, null))
                    .withCountry((String) map.getOrDefault(COUNTRY, null))
                    .build();


            Customer customer = new CustomerBuilder()
                    .withCompanyName((String) map.get(COMPANY_NAME))
                    .withContactName((String) map.getOrDefault(CONTACT_NAME, null))
                    .withContactTitle((String) map.getOrDefault(CONTACT_TITLE, null))
                    .withAddress(addressOfCustomer)
                    .withFax((String) map.getOrDefault(FAX, null))
                    .withPhone((String) map.getOrDefault(PHONE, null))
                    .withRegion((String) map.getOrDefault(REGION, null))
                    .build();

            em.persist(customer);
        });
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public void addUsingNativeSql(Model model) {
        executeQuery(() -> {

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
}
