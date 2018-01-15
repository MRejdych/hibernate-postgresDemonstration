package app.stats;

import app.dao.CustomersDAO;
import app.dao.ProductsDAO;
import app.entities.Customer;
import app.entities.Product;
import app.utils.CustomerBuilder;
import app.utils.DatabaseUtils;
import app.utils.ProductBuilder;

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

    public void generate() throws IOException, InterruptedException {
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
                .peek(c -> customersDAO.delete(c.getCustomerId()))
                .map(this::copyCustomer)
                .forEach(customersDAO::create);

    }

    private void executeCrudOperationsOnEachProduct(){
        products = productsDAO.readAll();

        products.stream()
                .peek(p -> productsDAO.readById(p.getProductId()))
                .map(p -> productActions.get(random()).apply(p))
                .peek(p -> productsDAO.update(p, p.getProductId()))
                //.peek(p -> productsDAO.delete(p.getProductId()))
                .map(this::copyProduct)
                .forEach(productsDAO::create);

    }

    private void executeNativeSqlCrudOperationsOnEachCustomer(){
        customers = customersDAO.readAllUsingNativeSql();

        customers.stream()
                .peek(c -> customersDAO.readByIdUsingNativeSql(c.getCustomerId()))
                .map(c -> customerActions.get(random()).apply(c))
                .peek(c -> customersDAO.updateUsingNativeSql(c, c.getCustomerId()))
                .peek(c -> customersDAO.deleteUsingNativeSql(c.getCustomerId()))
                .map(this::copyCustomer)
                .forEach(customersDAO::createUsingNativeSql);

    }

    private void executeNativeSqlCrudOperationsOnEachProduct(){
        products = productsDAO.readAllUsingNativeSql();

        products.stream()
                .peek(p -> productsDAO.readByIdUsingNativeSql(p.getProductId()))
                .map(p -> productActions.get(random()).apply(p))
                .peek(p -> productsDAO.updateUsingNativeSql(p, p.getProductId()))
                //.peek(p -> productsDAO.deleteUsingNativeSql(p.getProductId()))
                .map(this::copyProduct)
                .forEach(productsDAO::createUsingNativeSql);

    }

    private void resetDatabaseToInitialState() throws IOException, InterruptedException {
        dbutils.resetDatabaseToInitialState();
        Thread.sleep(100);
    }

    private Customer copyCustomer(Customer that){
        Customer cust = new CustomerBuilder().withCompanyName("temp").build();
        cust.copyStateOfAnotherCustomer(that);
        return cust;
    }

    private Product copyProduct(Product that){
        Product prod = new ProductBuilder().withProductName("temp").withDiscontinued(1).build();
        prod.copyStateOfAnotherProduct(that);
        return prod;
    }

}

