package app.stats;

import app.dataAccessObjects.CustomersDAO;
import app.dataAccessObjects.ProductsDAO;
import app.entities.Customer;
import app.entities.Product;
import app.springconfig.ApplicationConfiguration;
import app.utils.DatabaseUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class StatisticsGenerator {
    private DatabaseUtils dbutils;

    private CustomersDAO customersDAO;
    private ProductsDAO productsDAO;

    private List<Customer> customers;
    private List<Product> products;

    private List<Function<Customer, Customer>> customerActions;
    private List<Function<Product, Product>> productActions;

    private Random random;

    public StatisticsGenerator(DatabaseUtils dbutils, CustomersDAO customersDAO, ProductsDAO productsDAO){
        this.dbutils = dbutils;
        this.customersDAO = customersDAO;
        this.productsDAO = productsDAO;
        random = new Random();
        initilizeCustomerActions();
        initializeProductActions();
    }

    public void generate() throws IOException {
        resetDatabaseToInitialState();
        executeCrudOperationsOnEachCustomer();
        executeCrudOperationsOnEachProduct();
        resetDatabaseToInitialState();
        executeNativeSqlCrudOperationsOnEachCustomer();
        executeNativeSqlCrudOperationsOnEachProduct();
        resetDatabaseToInitialState();
    }

    private int random(){
        return random.nextInt(3);
    }

    private void initializeProductActions() {
        productActions = new ArrayList<>();

        productActions.add(product -> {
            product.setProductName("test");
            return product;
        });

        productActions.add(product -> {
            product.setQuantityPerUnit("test");
            return product;
        });

        productActions.add(product -> {
            product.setDiscontinued(1);
            return product;
        });
    }

    private void initilizeCustomerActions() {
        customerActions = new ArrayList<>();

        customerActions.add(customer -> {
            customer.setCompanyName("test");
            return customer;
        });

        customerActions.add(customer -> {
            customer.setContactTitle("test");
            return customer;
        });

        customerActions.add(customer -> {
            customer.setContactTitle("test");
            return customer;
        });
    }

    private void executeCrudOperationsOnEachCustomer(){
        customers = customersDAO.readAll();

        customers.stream()
                .peek(c -> customersDAO.readById(c.getCustomerId()))
                .map(c -> customerActions.get(random()).apply(c))
                .peek(c -> customersDAO.update(c, c.getCustomerId()))
                .forEach(c -> customersDAO.delete(c.getCustomerId()));


    }

    private void executeCrudOperationsOnEachProduct(){
        products = productsDAO.readAll();

        products.stream()
                .peek(p -> productsDAO.readById(p.getProductId()))
                .map(p -> productActions.get(random()).apply(p))
                .peek(p -> productsDAO.update(p, p.getProductId()))
                .forEach(p -> productsDAO.delete(p.getProductId()));

    }

    private void executeNativeSqlCrudOperationsOnEachCustomer(){
        customers = customersDAO.readAllUsingNativeSql();

        customers.stream()
                .peek(c -> customersDAO.readByIdUsingNativeSql(c.getCustomerId()))
                .map(c -> customerActions.get(random()).apply(c))
                .peek(c -> customersDAO.updateUsingNativeSql(c, c.getCustomerId()))
                .forEach(c -> customersDAO.deleteUsingNativeSql(c.getCustomerId()));


    }

    private void executeNativeSqlCrudOperationsOnEachProduct(){
        products = productsDAO.readAllUsingNativeSql();

        products.stream()
                .peek(p -> productsDAO.readByIdUsingNativeSql(p.getProductId()))
                .map(p -> productActions.get(random()).apply(p))
                .peek(p -> productsDAO.updateUsingNativeSql(p, p.getProductId()))
                .forEach(p -> productsDAO.deleteUsingNativeSql(p.getProductId()));

    }

    private void resetDatabaseToInitialState() throws IOException {
        dbutils.resetDatabaseToInitialState();
    }

}

