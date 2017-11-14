package app.queries;

import app.entities.Category;
import app.entities.Product;
import app.entities.Supplier;
import app.utils.DatabaseUtils;
import app.utils.ProductBuilder;
import org.springframework.ui.Model;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static app.constants.ProductsDbFields.*;

public class ProductsManipulator extends EntitiesManipulator <Product> {

    public ProductsManipulator(DatabaseUtils dbutils) {
        super(dbutils);
    }

    public List<Product> selectAll() {
        prepareConnectionToDB();
        List<Product> products = new ArrayList<>();


        TypedQuery<Product> generatedQuery = em.createQuery("SELECT pr FROM Product pr", Product.class);
        Query nativeQuery = em.createNativeQuery("Select * From products", Product.class);


        JPQLqueryTime = executeQuery(generatedQuery::getResultList, products);
        nativeSqlQueryTime = executeQuery(nativeQuery::getResultList);


        closeConnectionToDB();
        return products;
    }

    @Override
    public Product selectById(short productId) {
        prepareConnectionToDB();
        EntityKeeper keeper = new EntityKeeper();

        Query nativeQuery = em.createNativeQuery("Select * From products Where product_id = ?1", Product.class);
        nativeQuery.setParameter(1, productId);

        nativeSqlQueryTime = executeQuery(nativeQuery::getSingleResult);
        JPQLqueryTime = executeQuery(() -> em.find(Product.class, productId), keeper);

        closeConnectionToDB();
        return keeper.getEntity();
    }

    @Override
    public void add(Model model) {
        prepareConnectionToDB();
        Map<String, Object> map = model.asMap();

        Product product = new ProductBuilder()
                .withProductName((String) map.get(PRODUCT_NAME))
                .withCategory((Category) map.get(CATEGORY))
                .withSupplier((Supplier) map.get(SUPPLIER))
                .withQuantityPerUnit((String) map.get(QUANTITY_PER_UNIT))
                .withUnitPrice((Float) map.get(UNIT_PRICE))
                .withUnitsInStock((Short) map.get(UNITS_IN_STOCK))
                .withUnitsOnOrder((Short) map.get(UNITS_ON_ORDER))
                .withReorderLevel((Short) map.get(REORDER_LEVEL))
                .withDiscontinued((Integer) map.get(DISCONTINUED))
                .build();

        em.persist(product);
        closeConnectionToDB();
    }

    @Override
    public void update(short productId, Model model) {
        prepareConnectionToDB();

        Product product = em.find(Product.class, productId);

        new AttributesSettingHelper(model.asMap())

                .ifMapContainsKey(PRODUCT_NAME).thenSetValueOf(product::setProductName)
                .ifMapContainsKey(SUPPLIER).thenSetValueOf(product::setSupplier)
                .ifMapContainsKey(CATEGORY).thenSetValueOf(product::setCategory)
                .ifMapContainsKey(QUANTITY_PER_UNIT).thenSetValueOf(product::setQuantityPerUnit)
                .ifMapContainsKey(UNIT_PRICE).thenSetValueOf(product::setUnitPrice)
                .ifMapContainsKey(UNITS_IN_STOCK).thenSetValueOf(product::setUnitsInStock)
                .ifMapContainsKey(UNITS_ON_ORDER).thenSetValueOf(product::setUnitsOnOrder)
                .ifMapContainsKey(REORDER_LEVEL).thenSetValueOf(product::setReorderLevel)
                .ifMapContainsKey(DISCONTINUED).thenSetValueOf(product::setDiscontinued)
                .end();

        closeConnectionToDB();
    }

    @Override
    public void remove(short productId) {
        prepareConnectionToDB();

        JPQLqueryTime = executeQuery(() -> {
            Product pr = em.find(Product.class, productId);
            if(pr != null) em.remove(pr);
        });

        closeConnectionToDB();
    }

}
