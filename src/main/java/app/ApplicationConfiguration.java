package app;

import app.queries.CustomersManipulator;
import app.queries.EntitiesManipulator;
import app.queries.ProductsManipulator;
import app.utils.CustomerBuilder;
import app.utils.DatabaseUtils;
import app.utils.ProductBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Scope("prototype")
    public DatabaseUtils databaseUtils(){
        return new DatabaseUtils();
    }

    @Bean
    @Scope("prototype")
    public CustomersManipulator customersManipulator(){
        return new CustomersManipulator(databaseUtils());
    }

    @Bean
    @Scope("prototype")
    public ProductsManipulator productsManipulator(){
        return new ProductsManipulator(databaseUtils());
    }

    @Bean
    @Scope("prototype")
    public CustomerBuilder customerBuilder(){
        return new CustomerBuilder();
    }

    @Bean
    @Scope("prototype")
    public ProductBuilder productBuilder(){
        return new ProductBuilder();
    }

}
