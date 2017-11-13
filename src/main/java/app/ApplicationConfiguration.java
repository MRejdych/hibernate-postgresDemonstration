package app;

import app.queries.CustomersManipulator;
import app.queries.EntitiesManipulator;
import app.queries.ProductsManipulator;
import app.utils.DatabaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("app")
public class ApplicationContext {

    @Bean
    @Scope("prototype")
    public DatabaseUtils databaseUtils(){
        return new DatabaseUtils();
    }

    @Bean
    @Scope("prototype")
    @Autowired
    public EntitiesManipulator customersManipulator(){
        return new CustomersManipulator();
    }

    @Bean
    @Scope("prototype")
    @Autowired
    public EntitiesManipulator productsManipulator(){
        return new ProductsManipulator();
    }

}
