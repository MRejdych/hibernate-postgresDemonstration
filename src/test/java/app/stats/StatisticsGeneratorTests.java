package app.stats;

import app.dataAccessObjects.CustomersDAO;
import app.dataAccessObjects.ProductsDAO;
import app.entities.Customer;
import app.entities.Product;
import app.utils.DatabaseUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StatisticsGeneratorTests {
    private DatabaseUtils dbutils;
    private CustomersDAO customersDAO;
    private ProductsDAO productsDAO;
    private List<Customer> customerList;
    private List<Product> productsList;
    private Customer cust1;
    private Customer cust2;
    private Product prod1;
    private Product prod2;

    @Before
    public void before() {
        cust1 = mock(Customer.class);
        cust2 = mock(Customer.class);
        when(cust1.getCustomerId()).thenReturn((short) 1);
        when(cust2.getCustomerId()).thenReturn((short) 2);

        prod1 = mock(Product.class);
        prod2 = mock(Product.class);
        when(prod1.getProductId()).thenReturn((short) 1);
        when(prod2.getProductId()).thenReturn((short) 2);

        customerList = new LinkedList<>();
        customerList.add(cust1);
        customerList.add(cust2);

        productsList = new LinkedList<>();
        productsList.add(prod1);
        productsList.add(prod2);

        dbutils = mock(DatabaseUtils.class);

        customersDAO = mock(CustomersDAO.class);
        when(customersDAO.readAll()).thenReturn(customerList);
        when(customersDAO.readAllUsingNativeSql()).thenReturn(customerList);
        when(customersDAO.readById((short) 1)).thenReturn(cust1);
        when(customersDAO.readById((short) 2)).thenReturn(cust2);
        when(customersDAO.readByIdUsingNativeSql((short) 1)).thenReturn(cust1);
        when(customersDAO.readByIdUsingNativeSql((short) 2)).thenReturn(cust2);


        productsDAO = mock(ProductsDAO.class);
        when(productsDAO.readAll()).thenReturn(productsList);
        when(productsDAO.readAllUsingNativeSql()).thenReturn(productsList);
        when(productsDAO.readById((short) 1)).thenReturn(prod1);
        when(productsDAO.readById((short) 2)).thenReturn(prod2);
        when(productsDAO.readByIdUsingNativeSql((short) 1)).thenReturn(prod1);
        when(productsDAO.readByIdUsingNativeSql((short) 2)).thenReturn(prod2);
    }

    @AfterClass
    public static void afterClass(){
        StatisticsKeeper.clearStatistics();
    }

    @Test
    public void generateStatisticsTest() throws IOException {
        StatisticsGenerator generator = new StatisticsGenerator(dbutils, customersDAO, productsDAO);
        generator.generate();

        verify(dbutils, times(3)).resetDatabaseToInitialState();
        verify(customersDAO, times(1)).readAll();
        verify(customersDAO, times(1)).readAllUsingNativeSql();
        verify(customersDAO, times(customerList.size())).readById(anyShort());
        verify(customersDAO, times(customerList.size())).update(any(), anyShort());
        verify(customersDAO, times(customerList.size())).delete(anyShort());

        verify(customersDAO, times(customerList.size())).readByIdUsingNativeSql(anyShort());
        verify(customersDAO, times(customerList.size())).updateUsingNativeSql(any(), anyShort());
        verify(customersDAO, times(customerList.size())).deleteUsingNativeSql(anyShort());

        verify(productsDAO, times(1)).readAll();
        verify(productsDAO, times(1)).readAllUsingNativeSql();
        verify(productsDAO, times(productsList.size())).readById(anyShort());
        verify(productsDAO, times(productsList.size())).update(any(), anyShort());
        verify(productsDAO, times(productsList.size())).delete(anyShort());

        verify(productsDAO, times(productsList.size())).readByIdUsingNativeSql(anyShort());
        verify(productsDAO, times(productsList.size())).updateUsingNativeSql(any(), anyShort());
        verify(productsDAO, times(productsList.size())).deleteUsingNativeSql(anyShort());

        verify(cust1, times(6)).getCustomerId();
        verify(cust2, times(6)).getCustomerId();
        verify(prod1, times(6)).getProductId();
        verify(prod2, times(6)).getProductId();
    }


}
