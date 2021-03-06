package app.springconfig;

import app.dao.*;
import app.stats.StatisticsAggregator;
import app.stats.StatisticsGenerator;
import app.stats.StatisticsKeeper;
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
    public OrdersDAO ordersManipulator() { return new OrdersDAO(databaseUtils()); }

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

    @Bean
    public StatisticsGenerator statisticsGenerator(){
        return new StatisticsGenerator(databaseUtils(), customersManipulatorGeneratorMode(), productsManipulatorGeneratorMode());
    }

    @Bean
    @Scope("prototype")
    public StatisticsAggregator statisticsAggregator(){
        return new StatisticsAggregator(StatisticsKeeper.getStatisticsMap());
    }

    private CustomersDAO customersManipulatorGeneratorMode(){
        return new CustomersDAO(databaseUtils(), true);
    }

    private ProductsDAO productsManipulatorGeneratorMode(){
        return new ProductsDAO(databaseUtils(), true);
    }

}
