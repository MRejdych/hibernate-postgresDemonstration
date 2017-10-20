package backend.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order  implements Serializable {

    protected Order() {
    }

    public Order(Long customerId, Long employeeId, LocalDate orderData, LocalDate requiredDate, LocalDate shippedDate,
                 Long shipVia, Float freight, Address address) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.orderData = orderData;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.address = address;
    }

    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    protected Long orderId;

    @Column(name = "customer_id")
    protected Long customerId;

    @Column(name = "employee_id")
    protected Long employeeId;

    @Column(name = "order_data")
    protected LocalDate orderData;

    @Column(name = "required_date")
    protected LocalDate requiredDate;

    @Column(name = "shipped_date")
    protected LocalDate shippedDate;

    @Column(name = "ship_via")
    protected Long shipVia;

    protected Float freight;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "country")),
    })
    protected Address address;


    public Long getOrderId() {
        return orderId;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getOrderData() {
        return orderData;
    }

    public void setOrderData(LocalDate orderData) {
        this.orderData = orderData;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Long getShipVia() {
        return shipVia;
    }

    public void setShipVia(Long shipVia) {
        this.shipVia = shipVia;
    }

    public Float getFreight() {
        return freight;
    }

    public void setFreight(Float freight) {
        this.freight = freight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
