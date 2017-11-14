package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class OrderDetails implements Serializable {

    protected OrderDetails() {
    }

    public OrderDetails(Order order, Product product, Float unitPrice, Short quantity, Float discount) {
        if (order == null || product == null || unitPrice == null || quantity == null
                || discount == null) throw new IllegalArgumentException();
        this.order = order;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @NotNull
    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

    @NotNull
    @Column(nullable = false)
    private Short quantity;

    @NotNull
    @Column(nullable = false)
    private Float discount;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderId) {
        if (orderId != null) this.order = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productId) {
        if (productId != null) this.product = productId;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        if (unitPrice != null) this.unitPrice = unitPrice;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        if (quantity != null) this.quantity = quantity;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        if (discount != null) this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetails that = (OrderDetails) o;

        if (getOrder() != null ? !getOrder().equals(that.getOrder()) : that.getOrder() != null) return false;
        if (getProduct() != null ? !getProduct().equals(that.getProduct()) : that.getProduct() != null) return false;
        if (getUnitPrice() != null ? !getUnitPrice().equals(that.getUnitPrice()) : that.getUnitPrice() != null)
            return false;
        if (getQuantity() != null ? !getQuantity().equals(that.getQuantity()) : that.getQuantity() != null)
            return false;
        return getDiscount() != null ? getDiscount().equals(that.getDiscount()) : that.getDiscount() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrder() != null ? getOrder().hashCode() : 0;
        result = 31 * result + (getProduct() != null ? getProduct().hashCode() : 0);
        result = 31 * result + (getUnitPrice() != null ? getUnitPrice().hashCode() : 0);
        result = 31 * result + (getQuantity() != null ? getQuantity().hashCode() : 0);
        result = 31 * result + (getDiscount() != null ? getDiscount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDetails{");
        sb.append("order=").append(order);
        sb.append(", product=").append(product);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", quantity=").append(quantity);
        sb.append(", discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }
}
