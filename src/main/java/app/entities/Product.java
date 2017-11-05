package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    protected Product() {
    }

    public Product(String productName, Long supplierId, Long categoryId, String quantityPerUnit, Float unitPrice,
                   int unitsInStock, int unitsOnOrder, int reorderLevel, int discontinued) {
        if (productName == null) throw new IllegalArgumentException();
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
    @Column(name = "product_name", nullable = false, length = 40)
    protected String productName;

    @Column(name = "supplier_id")
    protected Long supplierId;

    @Column(name = "category_id")
    protected Long categoryId;

    @Column(name = "quantity_per_unit", length = 20)
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

    @OneToMany(mappedBy = "productByProductId")
    protected Collection<OrderDetails> orderDetailsByProductId;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id", insertable = false, updatable = false)
    protected Supplier supplierBySupplierId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    protected Category categoryByCategoryId;


    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName != null && !productName.isEmpty()) this.productName = productName;
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

    public Collection<OrderDetails> getOrderDetailsByProductId() {
        return orderDetailsByProductId;
    }

    public void setOrderDetailsByProductId(Collection<OrderDetails> orderDetailsByProductId) {
        this.orderDetailsByProductId = orderDetailsByProductId;
    }

    public Supplier getSupplierBySupplierId() {
        return supplierBySupplierId;
    }

    public void setSupplierBySupplierId(Supplier supplierBySupplierId) {
        this.supplierBySupplierId = supplierBySupplierId;
    }

    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (getUnitsInStock() != product.getUnitsInStock()) return false;
        if (getUnitsOnOrder() != product.getUnitsOnOrder()) return false;
        if (getReorderLevel() != product.getReorderLevel()) return false;
        if (getDiscontinued() != product.getDiscontinued()) return false;
        if (getProductId() != null ? !getProductId().equals(product.getProductId()) : product.getProductId() != null)
            return false;
        if (getProductName() != null ? !getProductName().equals(product.getProductName()) : product.getProductName() != null)
            return false;
        if (getSupplierId() != null ? !getSupplierId().equals(product.getSupplierId()) : product.getSupplierId() != null)
            return false;
        if (getCategoryId() != null ? !getCategoryId().equals(product.getCategoryId()) : product.getCategoryId() != null)
            return false;
        if (getQuantityPerUnit() != null ? !getQuantityPerUnit().equals(product.getQuantityPerUnit()) : product.getQuantityPerUnit() != null)
            return false;
        if (getUnitPrice() != null ? !getUnitPrice().equals(product.getUnitPrice()) : product.getUnitPrice() != null)
            return false;
        if (getOrderDetailsByProductId() != null ? !getOrderDetailsByProductId().equals(product.getOrderDetailsByProductId()) : product.getOrderDetailsByProductId() != null)
            return false;
        if (getSupplierBySupplierId() != null ? !getSupplierBySupplierId().equals(product.getSupplierBySupplierId()) : product.getSupplierBySupplierId() != null)
            return false;
        return getCategoryByCategoryId() != null ? getCategoryByCategoryId().equals(product.getCategoryByCategoryId()) : product.getCategoryByCategoryId() == null;
    }

    @Override
    public int hashCode() {
        int result = getProductId() != null ? getProductId().hashCode() : 0;
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 0);
        result = 31 * result + (getSupplierId() != null ? getSupplierId().hashCode() : 0);
        result = 31 * result + (getCategoryId() != null ? getCategoryId().hashCode() : 0);
        result = 31 * result + (getQuantityPerUnit() != null ? getQuantityPerUnit().hashCode() : 0);
        result = 31 * result + (getUnitPrice() != null ? getUnitPrice().hashCode() : 0);
        result = 31 * result + getUnitsInStock();
        result = 31 * result + getUnitsOnOrder();
        result = 31 * result + getReorderLevel();
        result = 31 * result + getDiscontinued();
        result = 31 * result + (getOrderDetailsByProductId() != null ? getOrderDetailsByProductId().hashCode() : 0);
        result = 31 * result + (getSupplierBySupplierId() != null ? getSupplierBySupplierId().hashCode() : 0);
        result = 31 * result + (getCategoryByCategoryId() != null ? getCategoryByCategoryId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", supplierId=").append(supplierId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", quantityPerUnit='").append(quantityPerUnit).append('\'');
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", unitsInStock=").append(unitsInStock);
        sb.append(", unitsOnOrder=").append(unitsOnOrder);
        sb.append(", reorderLevel=").append(reorderLevel);
        sb.append(", discontinued=").append(discontinued);
        sb.append(", orderDetailsByProductId=").append(orderDetailsByProductId);
        sb.append(", supplierBySupplierId=").append(supplierBySupplierId);
        sb.append(", categoryByCategoryId=").append(categoryByCategoryId);
        sb.append('}');
        return sb.toString();
    }
}
