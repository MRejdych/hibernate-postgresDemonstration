package app.springconfig;

import app.dataAccessObjects.*;
import app.utils.DatabaseUtils;
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
    public CustomersDAO customersManipulator(){
        return new CustomersDAO(databaseUtils());
    }

    @Bean
    @Scope("prototype")
    public ProductsDAO productsManipulator(){
        return new ProductsDAO(databaseUtils());
    }

    @Bean
    @Scope("prototype")
    public SuppliersHelper suppliersHelper(){
        return new SuppliersHelper(databaseUtils());
    }

    @Bean
    @Scope("prototype")
    public CategoriesHelper categoriesHelper(){
        return new CategoriesHelper(databaseUtils());
    }

}
