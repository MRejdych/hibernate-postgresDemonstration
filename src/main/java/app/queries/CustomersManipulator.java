package app.queries;

import app.entities.Address;
import app.entities.Customer;
import app.utils.AddressBuilder;
import app.utils.CustomerBuilder;
import app.utils.DatabaseUtils;
import org.springframework.ui.Model;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static app.constants.CustomersDbFields.*;


public class CustomersManipulator extends EntitiesManipulator <Customer> {


    public CustomersManipulator(DatabaseUtils dbutils) {
        super(dbutils);
    }

    @Override
    public List<Customer> selectAll() {
        prepareConnectionToDB();
        List<Customer> customers = new ArrayList<>();

        Query nativeQuery = em.createNativeQuery("Select * From customers", Customer.class);
        TypedQuery<Customer> generatedQuery = em.createQuery("SELECT c FROM Customer c", Customer.class);

        nativeSqlQueryTime = executeQuery(nativeQuery::getResultList);
        JPQLqueryTime = executeQuery(generatedQuery::getResultList, customers);

        closeConnectionToDB();
        return customers;
    }

    @Override
    public Customer selectById(short customerId){
        prepareConnectionToDB();
        EntityKeeper keeper = new EntityKeeper();

        Query nativeQuery = em.createNativeQuery("Select * From customers Where customer_id = ?1", Customer.class);
        nativeQuery.setParameter(1, customerId);

        nativeSqlQueryTime = executeQuery(nativeQuery::getSingleResult);
        JPQLqueryTime = executeQuery(() -> em.find(Customer.class, customerId), keeper);

        closeConnectionToDB();
        return keeper.getEntity();
    }

    @Override
    public void remove(short customerId){
        prepareConnectionToDB();

        //Query nativeQuery = em.createNativeQuery("DELETE FROM customers WHERE customer_id = ?1", Customer.class);
        //nativeQuery.setParameter(1, customerId);

        //nativeSqlQueryTime = executeQuery(nativeQuery::executeUpdate);
        JPQLqueryTime = executeQuery(() -> {
            Customer c = em.find(Customer.class, customerId);
            if(c != null) em.remove(c);
        });

        closeConnectionToDB();

    }

    @Override
    public void update(short customerId, Model model){
        prepareConnectionToDB();

        Customer customer = em.find(Customer.class, customerId);

        new AttributesSettingHelper(model.asMap())

                .ifMapContainsKey(COMPANY_NAME).thenSetValueOf(customer::setCompanyName)
                .ifMapContainsKey(CONTACT_NAME).thenSetValueOf(customer::setContactName)
                .ifMapContainsKey(CONTACT_TITLE).thenSetValueOf(customer::setContactTitle)
                .ifMapContainsKey(ADDRESS).thenSetValueOf(customer.getAddress()::setAddress)
                .ifMapContainsKey(CITY).thenSetValueOf(customer.getAddress()::setCity)
                .ifMapContainsKey(COUNTRY).thenSetValueOf(customer.getAddress()::setCountry)
                .ifMapContainsKey(POSTAL_CODE).thenSetValueOf(customer.getAddress()::setPostalCode)
                .ifMapContainsKey(PHONE).thenSetValueOf(customer::setPhone)
                .ifMapContainsKey(FAX).thenSetValueOf(customer::setFax)
                .ifMapContainsKey(REGION).thenSetValueOf(customer::setRegion)
                .end();

        closeConnectionToDB();

    }

    @Override
    public void add(Model model){
        prepareConnectionToDB();
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
        closeConnectionToDB();
    }
}
