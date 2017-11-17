package app.dataAccessObjects;

import app.constants.ProductsDbFields;
import app.entities.Category;
import app.entities.Product;
import app.entities.Supplier;
import app.utils.DatabaseUtils;
import app.utils.ProductBuilder;
import org.springframework.ui.Model;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import static app.constants.ProductsDbFields.*;

public class ProductsDAO extends EntitiesDAO<Product> {

    public ProductsDAO(DatabaseUtils dbutils) {
        super(dbutils);
    }

    public List<Product> selectAll() {
        return executeQuery(() -> {

            TypedQuery<Product> generatedQuery = em.createQuery("SELECT pr FROM Product pr", Product.class);

            return generatedQuery.getResultList();
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> selectAllUsingNativeSql() {
        return executeQuery(() -> {

            Query nativeQuery = em.createNativeQuery("Select * From products", Product.class);

            return (List<Product>) nativeQuery.getResultList();
        });
    }

    @Override
    public Product selectById(short productId) {
        return executeQuery(() -> em.find(Product.class, productId));
    }

    @Override
    public Product selectByIdUsingNativeSql(short productId) {
        return executeQuery(() -> {

            Query nativeQuery = em.createNativeQuery("Select * From products Where product_id = ?1", Product.class);
            nativeQuery.setParameter(1, productId);

            return (Product) nativeQuery.getSingleResult();
        });
    }

    @Override
    public void add(Model model) {
        executeQuery(() -> {
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
        });
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public void addUsingNativeSql(Model model) {
        executeQuery(() -> {

            Map<String, Object> attributesMap = model.asMap();
            Set<String> legalTableColumns = ProductsDbFields.asSet();

            StringJoiner columnsJoiner = new StringJoiner(", ");
            StringJoiner valuesJoiner = new StringJoiner(", ");

            attributesMap.entrySet()
                    .stream()
                    .filter(e -> legalTableColumns.contains(e.getKey()))
                    .forEach(mapEntry -> {
                        columnsJoiner.add(mapEntry.getKey());
                        valuesJoiner.add((String) mapEntry.getValue());
                    });

            String columns = columnsJoiner.toString();
            String values = valuesJoiner.toString();

            String query = new StringBuilder()
                    .append("INSERT into products(")
                    .append(columns)
                    .append(") VALUES (")
                    .append(values)
                    .append(");")
                    .toString();


            Query nativeQuery = em.createNativeQuery(query, Product.class);

            nativeQuery.executeUpdate();

        });
    }

    @Override
    public void update(short productId, Model model) {
        executeQuery(() -> {
            Product product = em.find(Product.class, productId);

            new AttributesSettingHelper(model.asMap())

                    .ifMapContainsKey(PRODUCT_NAME).then(product::setProductName)
                    .ifMapContainsKey(SUPPLIER).then(product::setSupplier)
                    .ifMapContainsKey(CATEGORY).then(product::setCategory)
                    .ifMapContainsKey(QUANTITY_PER_UNIT).then(product::setQuantityPerUnit)
                    .ifMapContainsKey(UNIT_PRICE).then(product::setUnitPrice)
                    .ifMapContainsKey(UNITS_IN_STOCK).then(product::setUnitsInStock)
                    .ifMapContainsKey(UNITS_ON_ORDER).then(product::setUnitsOnOrder)
                    .ifMapContainsKey(REORDER_LEVEL).then(product::setReorderLevel)
                    .ifMapContainsKey(DISCONTINUED).then(product::setDiscontinued)
                    .end();

        });
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void updateUsingNativeSql(short productId, Model model) {

        executeQuery(() -> {

            Map<String, Object> attributesMap = model.asMap();
            Set<String> legalTableColumns = ProductsDbFields.asSet();

            String queryPrefix = "UPDATE products SET ";
            String querySuffix = " WHERE product_id = ?1;";

            StringBuilder queryBuilder = new StringBuilder(queryPrefix);

            queryBuilder.append(PRODUCT_NAME + " = ").append(attributesMap.get(PRODUCT_NAME));


            attributesMap.entrySet()
                    .stream()
                    .filter(e -> legalTableColumns.contains(e.getKey()))
                    .forEach(mapEntry -> {
                        String key = mapEntry.getKey();
                        String value = (String) mapEntry.getValue();
                            if(!key.equals(PRODUCT_NAME))
                            queryBuilder.append(", ").append(key).append(" = ").append(value);
                    });

            queryBuilder.append(querySuffix);

            String query = queryBuilder.toString();

            Query nativeQuery = em.createNativeQuery(query, Product.class);
            nativeQuery.setParameter(1, productId);

            nativeQuery.executeUpdate();

        });
    }

    @Override
    public void remove(short productId) {
        executeQuery(() -> {
            Product pr = em.find(Product.class, productId);
            if(pr != null) em.remove(pr);
        });
    }

    @Override
    public void removeUsingNativeSql(short productId) {

        executeQuery(() -> {
            Query nativeQuery = em.createNativeQuery("DELETE FROM products WHERE product_id = ?1", Product.class);
            nativeQuery.setParameter(1, productId);

            nativeQuery.executeUpdate();
        });
    }

}
