package app.orders;

import app.entities.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderedProductDetails {
    public final Short productId;
    public final String productName;
    public final String amountOrdered;
    public final Float unitPrice;
    public final String quantityPerUnit;

    public OrderedProductDetails(Short productId, String productName, String amountOrdered, Float unitPrice, String quantityPerUnit) {
        this.productId = productId;
        this.productName = productName;
        this.amountOrdered = amountOrdered;
        this.unitPrice = unitPrice;
        this.quantityPerUnit = quantityPerUnit;
    }

    public static List<OrderedProductDetails> getOrderedProductsDetails(List<Product> products, Map<String, String> orderedProducts){
        Set<String> keys = orderedProducts.keySet();

        return products.stream().filter(it -> keys.contains(String.valueOf(it.getProductId())))
                .map(it -> new OrderedProductDetails(it.getProductId() ,it.getProductName(), orderedProducts.get(String.valueOf(it.getProductId())), it.getUnitPrice(), it.getQuantityPerUnit()))
                .collect(Collectors.toList());
    }
}
