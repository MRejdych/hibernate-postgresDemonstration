package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static app.constants.ProductsDbFields.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    protected Product() { }

    public Product(String productName, Supplier supplier, Category category, String quantityPerUnit, Float unitPrice,
                   Short unitsInStock, Short unitsOnOrder, Short reorderLevel, int discontinued) {
        if (productName == null) throw new IllegalArgumentException();
        this.productName = productName;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
        this.setSupplier(supplier);
        this.setCategory(category);
    }

    @Id
    @GeneratedValue
    @Column(name = PRODUCT_ID, nullable = false)
    private Short productId;

    @NotNull
    @Column(name = PRODUCT_NAME, nullable = false, length = 40)
    private String productName;

    @Column(name = QUANTITY_PER_UNIT, length = 20)
    private String quantityPerUnit;

    @Column(name = UNIT_PRICE)
    private Float unitPrice;

    @Column(name = UNITS_IN_STOCK)
    private Short unitsInStock;

    @Column(name = UNITS_ON_ORDER)
    private Short unitsOnOrder;

    @Column(name = REORDER_LEVEL)
    private Short reorderLevel;

    @NotNull
    @Column(name = DISCONTINUED, nullable = false)
    private int discontinued;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    @JoinColumn(name = SUPPLIER_ID, referencedColumnName = SUPPLIER_ID)
    protected Supplier supplier;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    @JoinColumn(name = CATEGORY_ID, referencedColumnName = CATEGORY_ID)
    protected Category category;

    @OneToMany(mappedBy = "product" , fetch = LAZY , cascade = ALL)
    protected List<OrderDetails> orderDetails = new ArrayList<>();

    public Short getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setProductName(String productName) {
        if (productName != null && !productName.isEmpty()) this.productName = productName;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Short getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Short unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Short getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Short unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public Short getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Short reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;

        if(!supplier.getProducts().contains(this)) supplier.addProduct(this);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;

        if(!category.getProducts().contains(this)) category.addProduct(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (getDiscontinued() != product.getDiscontinued()) return false;
        if (getProductId() != null ? !getProductId().equals(product.getProductId()) : product.getProductId() != null)
            return false;
        if (getProductName() != null ? !getProductName().equals(product.getProductName()) : product.getProductName() != null)
            return false;
        if (getQuantityPerUnit() != null ? !getQuantityPerUnit().equals(product.getQuantityPerUnit()) : product.getQuantityPerUnit() != null)
            return false;
        if (getUnitPrice() != null ? !getUnitPrice().equals(product.getUnitPrice()) : product.getUnitPrice() != null)
            return false;
        if (getUnitsInStock() != null ? !getUnitsInStock().equals(product.getUnitsInStock()) : product.getUnitsInStock() != null)
            return false;
        if (getUnitsOnOrder() != null ? !getUnitsOnOrder().equals(product.getUnitsOnOrder()) : product.getUnitsOnOrder() != null)
            return false;
        if (getReorderLevel() != null ? !getReorderLevel().equals(product.getReorderLevel()) : product.getReorderLevel() != null)
            return false;
        if (getSupplier() != null ? !getSupplier().equals(product.getSupplier()) : product.getSupplier() != null)
            return false;
        return getCategory() != null ? getCategory().equals(product.getCategory()) : product.getCategory() == null;
    }

    @Override
    public int hashCode() {
        int result = getProductId() != null ? getProductId().hashCode() : 0;
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 0);
        result = 31 * result + (getQuantityPerUnit() != null ? getQuantityPerUnit().hashCode() : 0);
        result = 31 * result + (getUnitPrice() != null ? getUnitPrice().hashCode() : 0);
        result = 31 * result + (getUnitsInStock() != null ? getUnitsInStock().hashCode() : 0);
        result = 31 * result + (getUnitsOnOrder() != null ? getUnitsOnOrder().hashCode() : 0);
        result = 31 * result + (getReorderLevel() != null ? getReorderLevel().hashCode() : 0);
        result = 31 * result + getDiscontinued();
        result = 31 * result + (getSupplier() != null ? getSupplier().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", quantityPerUnit='").append(quantityPerUnit).append('\'');
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", unitsInStock=").append(unitsInStock);
        sb.append(", unitsOnOrder=").append(unitsOnOrder);
        sb.append(", reorderLevel=").append(reorderLevel);
        sb.append(", discontinued=").append(discontinued);
        sb.append(", supplier=").append(supplier);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
