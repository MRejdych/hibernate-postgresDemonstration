package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class OrderDetails implements Serializable {

    protected OrderDetails() {
    }

    public OrderDetails(Long orderId, Long productId, Float unitPrice, Long quantity, Float discount) {
        if (orderId == null || productId == null || unitPrice == null || quantity == null
                || discount == null) throw new IllegalArgumentException();
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    @Id
    @Column(name = "order_id", nullable = false)
    protected Long orderId;

    @Id
    @Column(name = "product_id", nullable = false)
    protected Long productId;

    @NotNull
    @Column(name = "unit_price", nullable = false)
    protected Float unitPrice;

    @NotNull
    @Column(nullable = false)
    protected Long quantity;

    @NotNull
    @Column(nullable = false)
    protected Float discount;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
    protected Order orderByOrderId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    protected Product productByProductId;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        if (orderId != null) this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        if (productId != null) this.productId = productId;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        if (unitPrice != null) this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        if (quantity != null) this.quantity = quantity;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        if (discount != null) this.discount = discount;
    }

    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetails that = (OrderDetails) o;

        if (getOrderId() != null ? !getOrderId().equals(that.getOrderId()) : that.getOrderId() != null) return false;
        if (getProductId() != null ? !getProductId().equals(that.getProductId()) : that.getProductId() != null)
            return false;
        if (getUnitPrice() != null ? !getUnitPrice().equals(that.getUnitPrice()) : that.getUnitPrice() != null)
            return false;
        if (getQuantity() != null ? !getQuantity().equals(that.getQuantity()) : that.getQuantity() != null)
            return false;
        if (getDiscount() != null ? !getDiscount().equals(that.getDiscount()) : that.getDiscount() != null)
            return false;
        if (getOrderByOrderId() != null ? !getOrderByOrderId().equals(that.getOrderByOrderId()) : that.getOrderByOrderId() != null)
            return false;
        return getProductByProductId() != null ? getProductByProductId().equals(that.getProductByProductId()) : that.getProductByProductId() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
        result = 31 * result + (getUnitPrice() != null ? getUnitPrice().hashCode() : 0);
        result = 31 * result + (getQuantity() != null ? getQuantity().hashCode() : 0);
        result = 31 * result + (getDiscount() != null ? getDiscount().hashCode() : 0);
        result = 31 * result + (getOrderByOrderId() != null ? getOrderByOrderId().hashCode() : 0);
        result = 31 * result + (getProductByProductId() != null ? getProductByProductId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDetails{");
        sb.append("orderId=").append(orderId);
        sb.append(", productId=").append(productId);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", quantity=").append(quantity);
        sb.append(", discount=").append(discount);
        sb.append(", orderByOrderId=").append(orderByOrderId);
        sb.append(", productByProductId=").append(productByProductId);
        sb.append('}');
        return sb.toString();
    }
}
