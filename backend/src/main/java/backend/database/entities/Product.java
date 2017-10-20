package backend.database.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product  implements Serializable {

    protected Product() {
    }

    public Product(String productName, Long supplierId, Long categoryId, String quantityPerUnit, Float unitPrice,
                   int unitsInStock, int unitsOnOrder, int reorderLevel, int discontinued) {
        if(productName == null) throw new IllegalArgumentException();
        this.productName = productName;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }

    @Id
    @GeneratedValue
    @Column(name = "product_id", nullable = false)
    protected Long productId;

    @NotNull
    @Column(name = "product_name", nullable = false)
    protected String productName;

    @Column(name = "supplier_id")
    protected Long supplierId;

    @Column(name = "category_id")
    protected Long categoryId;

    @Column(name = "quantity_per_unit")
    protected String quantityPerUnit;

    @Column(name = "unit_price")
    protected Float unitPrice;

    @Column(name = "units_in_stock")
    protected int unitsInStock;

    @Column(name = "units_on_order")
    protected int unitsOnOrder;

    @Column(name = "reorder_level")
    protected int reorderLevel;

    @NotNull
    @Column(nullable = false)
    protected int discontinued;


    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if(productName != null && !productName.isEmpty()) this.productName = productName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }
}
