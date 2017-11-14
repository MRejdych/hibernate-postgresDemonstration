package app.utils;

import app.entities.Category;
import app.entities.Product;
import app.entities.Supplier;

public final class ProductBuilder {
    private String productName;
    private Supplier supplier;
    private Category category;
    private String quantityPerUnit;
    private Float unitPrice;
    private Short unitsInStock;
    private Short unitsOnOrder;
    private Short reorderLevel;
    private int discontinued;

    public ProductBuilder() {
    }


    public ProductBuilder withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductBuilder withSupplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public ProductBuilder withCategory(Category category) {
        this.category = category;
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
        return new Product(productName, supplier, category, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
    }
}
