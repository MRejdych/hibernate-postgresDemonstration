package app.queries;

import app.entities.Product;
import app.utils.DatabaseUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProductsManipulator extends EntitiesManipulator {

    public ProductsManipulator(DatabaseUtils dbutils) {
        super(dbutils);
    }

    public List<Product> selectAllProducts() {
        prepareConnectionToDB();
        List<Product> products = new ArrayList<>();


        TypedQuery<Product> generatedQuery = em.createQuery("SELECT pr FROM Product pr", Product.class);
        Query nativeQuery = em.createNativeQuery("Select * From products", Product.class);


        JPQLqueryTime = executeQuery(generatedQuery::getResultList, products);
        nativeSqlQueryTime = executeQuery(nativeQuery::getResultList);


        closeConnectionToDB();
        return products;
    }
}
