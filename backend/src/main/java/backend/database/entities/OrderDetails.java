package backend.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class OrderDetails  implements Serializable {

    protected OrderDetails() {
    }

    public OrderDetails(Long orderId, Long productId, Float unitPrice, Long quantity, Float discount) {
        if(orderId == null || productId == null || unitPrice == null || quantity == null
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


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        if(orderId != null) this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        if(productId != null) this.productId = productId;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        if(unitPrice != null) this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        if(quantity != null) this.quantity = quantity;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        if(discount != null) this.discount = discount;
    }
}
