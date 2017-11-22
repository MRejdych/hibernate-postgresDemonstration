package app.dataAccessObjects;

import app.constants.ProductsDbFields;
import app.entities.Product;
import app.utils.DatabaseUtils;
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

    @Override
    public void create(Product product) {
        executeQueryAndSaveStatistics(() -> {
            em.persist(product);
        });
    }

    public List<Product> readAll() {
        return executeQueryAndSaveStatistics(() -> {

            TypedQuery<Product> generatedQuery = em.createQuery("SELECT pr FROM Product pr ORDER BY pr.productId", Product.class);

            return generatedQuery.getResultList();
        });
    }

    @Override
    public Product readById(short productId) {
        return executeQueryAndSaveStatistics(() -> em.find(Product.class, productId));
    }

    @Override
    public void update(Product updatedProduct, short productId) {
        executeQueryAndSaveStatistics(() -> {
            Product product = em.find(Product.class, productId);

            if (product != null)
                product.copyStateOfAnotherProduct(updatedProduct);

        });
    }

    @Override
    public void delete(short productId) {
        executeQueryAndSaveStatistics(() -> {
            Product pr = em.find(Product.class, productId);
            if (pr != null) em.remove(pr);
        });
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public void createUsingNativeSql(Model model) {
        executeQueryAndSaveStatistics(() -> {

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> readAllUsingNativeSql() {
        return executeQueryAndSaveStatistics(() -> {

            Query nativeQuery = em.createNativeQuery("SELECT * FROM products ORDER BY product_id", Product.class);

            return (List<Product>) nativeQuery.getResultList();
        });
    }

    @Override
    public Product readByIdUsingNativeSql(short productId) {
        return executeQueryAndSaveStatistics(() -> {

            Query nativeQuery = em.createNativeQuery("SELECT * FROM products WHERE product_id = ?1", Product.class);
            nativeQuery.setParameter(1, productId);

            return (Product) nativeQuery.getSingleResult();
        });
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void updateUsingNativeSql(short productId, Model model) {

        executeQueryAndSaveStatistics(() -> {

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
                        if (!key.equals(PRODUCT_NAME))
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
    public void deleteUsingNativeSql(short productId) {

        executeQueryAndSaveStatistics(() -> {
            Query nativeQuery = em.createNativeQuery("DELETE FROM products WHERE product_id = ?1", Product.class);
            nativeQuery.setParameter(1, productId);

            nativeQuery.executeUpdate();
        });
    }
}
