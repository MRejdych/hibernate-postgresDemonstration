package app.utils;

import app.entities.Product;

public final class ProductBuilder {
    private String productName;
    private Short supplierId;
    private Short categoryId;
    private String quantityPerUnit;
    private Float unitPrice;
    private Short unitsInStock;
    private Short unitsOnOrder;
    private Short reorderLevel;
    private int discontinued;

    public ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductBuilder withSupplierId(Short supplierId) {
        this.supplierId = supplierId;
        return this;
    }

    public ProductBuilder withCategoryId(Short categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ProductBuilder withQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
        return this;
    }

    public ProductBuilder withUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public ProductBuilder withUnitsInStock(Short unitsInStock) {
        this.unitsInStock = unitsInStock;
        return this;
    }

    public ProductBuilder withUnitsOnOrder(Short unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
        return this;
    }

    public ProductBuilder withReorderLevel(Short reorderLevel) {
        this.reorderLevel = reorderLevel;
        return this;
    }

    public ProductBuilder withDiscontinued(int discontinued) {
        this.discontinued = discontinued;
        return this;
    }

    public Product build() {
        return new Product(productName, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
    }
}
