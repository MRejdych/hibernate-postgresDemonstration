package app.dao;

import app.entities.Product;
import app.utils.DatabaseUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static app.stats.StatisticType.*;

public class ProductsDAO extends EntitiesDAO<Product> {

    public ProductsDAO(DatabaseUtils dbutils) {
        super(dbutils);
    }

    public ProductsDAO(DatabaseUtils dbutils, Boolean statisticsGenerationMode){
        super(dbutils, statisticsGenerationMode);
    }

    @Override
    public void create(Product product) {
        executeQueryAndSaveStatistics(() -> {
            em.persist(product);
        }, CREATE);
    }

    public List<Product> readAll() {
        return executeQueryAndSaveStatistics(() -> {

            TypedQuery<Product> generatedQuery = em.createQuery("SELECT pr FROM Product pr ORDER BY pr.productId", Product.class);

            return generatedQuery.getResultList();
        }, READ);
    }

    @Override
    public Product readById(short productId) {
        return executeQueryAndSaveStatistics(() -> {
            TypedQuery<Product> generatedQuery = em.createQuery("SELECT pr FROM Product pr WHERE pr.productId = ?1", Product.class);
            generatedQuery.setParameter(1, productId);

            return generatedQuery.getSingleResult();
        }, READ);
    }

    @Override
    public void update(Product updatedProduct, short productId) {
        executeQueryAndSaveStatistics(() -> {
            Product product = em.find(Product.class, productId);

            if (product != null)
                product.copyStateOfAnotherProduct(updatedProduct);

        }, UPDATE);
    }

    @Override
    public void delete(short productId) {
        executeQueryAndSaveStatistics(() -> {

            Query generatedQuery = em.createQuery("DELETE FROM Product pr WHERE pr.productId = ?1");
            generatedQuery.setParameter(1, productId);
            generatedQuery.executeUpdate();
        }, DELETE);
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public void createUsingNativeSql(Product product) {
        executeQueryAndSaveStatistics(() -> {

            String query =
                    "INSERT into products(product_name, quantity_per_unit, unit_price, units_in_stock, units_on_order," +
                            " reorder_level, discontinued, supplier_id, category_id)" +
                            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9);";

            Query nativeQuery = em.createNativeQuery(query, Product.class);
            nativeQuery.setParameter(1, product.getProductName())
                    .setParameter(2, product.getQuantityPerUnit())
                    .setParameter(3, product.getUnitPrice())
                    .setParameter(4, product.getUnitsInStock())
                    .setParameter(5, product.getUnitsOnOrder())
                    .setParameter(6, product.getReorderLevel())
                    .setParameter(7, product.getDiscontinued())
                    .setParameter(8, product.getSupplier().getSupplierId())
                    .setParameter(9, product.getCategory().getCategoryId());

            nativeQuery.executeUpdate();
        }, NATIVE_CREATE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> readAllUsingNativeSql() {
        return executeQueryAndSaveStatistics(() -> {

            Query nativeQuery = em.createNativeQuery("SELECT * FROM products ORDER BY product_id", Product.class);

            return (List<Product>) nativeQuery.getResultList();
        }, NATIVE_READ);
    }

    @Override
    public Product readByIdUsingNativeSql(short productId) {
        return executeQueryAndSaveStatistics(() -> {

            Query nativeQuery = em.createNativeQuery("SELECT * FROM products WHERE product_id = ?1", Product.class);
            nativeQuery.setParameter(1, productId);

            return (Product) nativeQuery.getSingleResult();
        }, NATIVE_READ);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void updateUsingNativeSql(Product updatedProduct, short productId) {

        executeQueryAndSaveStatistics(() -> {
            String query =
                    "UPDATE products SET product_name = (?1), quantity_per_unit = (?2), unit_price = (?3), units_in_stock = (?4)," +
                            " units_on_order = (?5), reorder_level = (?6), discontinued = (?7), supplier_id = (?8), category_id = (?9)" +
                            " WHERE product_id = (?10);";

            Query nativeQuery = em.createNativeQuery(query, Product.class);
            nativeQuery.setParameter(1, updatedProduct.getProductName())
                    .setParameter(2, updatedProduct.getQuantityPerUnit())
                    .setParameter(3, updatedProduct.getUnitPrice())
                    .setParameter(4, updatedProduct.getUnitsInStock())
                    .setParameter(5, updatedProduct.getUnitsOnOrder())
                    .setParameter(6, updatedProduct.getReorderLevel())
                    .setParameter(7, updatedProduct.getDiscontinued())
                    .setParameter(8, updatedProduct.getSupplier().getSupplierId())
                    .setParameter(9, updatedProduct.getCategory().getCategoryId())
                    .setParameter(10, productId);

            nativeQuery.executeUpdate();
        }, NATIVE_UPDATE);
    }

    @Override
    public void deleteUsingNativeSql(short productId) {

        executeQueryAndSaveStatistics(() -> {
            Query nativeQuery = em.createNativeQuery("DELETE FROM products WHERE product_id = ?1", Product.class);
            nativeQuery.setParameter(1, productId);

            nativeQuery.executeUpdate();
        }, NATIVE_DELETE);
    }
}
