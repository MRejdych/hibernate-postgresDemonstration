package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    protected Order() {
    }

    public Order(Short customerId, Short employeeId, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate,
                 Short shipVia, Float freight, Address address) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.address = address;
    }

    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    protected Short orderId;

    @Column(name = "customer_id")
    protected Short customerId;

    @Column(name = "employee_id")
    protected Short employeeId;

    @Column(name = "order_date")
    protected LocalDate orderDate;

    @Column(name = "required_date")
    protected LocalDate requiredDate;

    @Column(name = "shipped_date")
    protected LocalDate shippedDate;

    @Column(name = "ship_via")
    protected Short shipVia;

    protected Float freight;

    @Column(name = "ship_name", length = 40)
    protected String shipName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "ship_address", length = 60)),
            @AttributeOverride(name = "city", column = @Column(name = "ship_city", length = 15)),
            @AttributeOverride(name = "postalCode", column = @Column(name = "ship_postal_code", length = 10)),
            @AttributeOverride(name = "country", column = @Column(name = "ship_country", length = 15)),
    })
    protected Address address;

    @Column(name = "ship_region", length = 15)
    protected String shipRegion;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
    protected Employee employeeByEmployeeId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    protected Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "ship_via", referencedColumnName = "shipper_id", insertable = false, updatable = false)
    protected Shipper shipperByShipVia;


    public Short getOrderId() {
        return orderId;
    }


    public Short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }

    public Short getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Short employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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

    public Short getShipVia() {
        return shipVia;
    }

    public void setShipVia(Short shipVia) {
        this.shipVia = shipVia;
    }

    public Float getFreight() {
        return freight;
    }

    public void setFreight(Float freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    public Shipper getShipperByShipVia() {
        return shipperByShipVia;
    }

    public void setShipperByShipVia(Shipper shipperByShipVia) {
        this.shipperByShipVia = shipperByShipVia;
    }

    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getOrderId() != null ? !getOrderId().equals(order.getOrderId()) : order.getOrderId() != null) return false;
        if (getCustomerId() != null ? !getCustomerId().equals(order.getCustomerId()) : order.getCustomerId() != null)
            return false;
        if (getEmployeeId() != null ? !getEmployeeId().equals(order.getEmployeeId()) : order.getEmployeeId() != null)
            return false;
        if (getOrderDate() != null ? !getOrderDate().equals(order.getOrderDate()) : order.getOrderDate() != null)
            return false;
        if (getRequiredDate() != null ? !getRequiredDate().equals(order.getRequiredDate()) : order.getRequiredDate() != null)
            return false;
        if (getShippedDate() != null ? !getShippedDate().equals(order.getShippedDate()) : order.getShippedDate() != null)
            return false;
        if (getShipVia() != null ? !getShipVia().equals(order.getShipVia()) : order.getShipVia() != null) return false;
        if (getFreight() != null ? !getFreight().equals(order.getFreight()) : order.getFreight() != null) return false;
        if (getShipName() != null ? !getShipName().equals(order.getShipName()) : order.getShipName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(order.getAddress()) : order.getAddress() != null) return false;
        if (getShipRegion() != null ? !getShipRegion().equals(order.getShipRegion()) : order.getShipRegion() != null)
            return false;
        if (getEmployeeByEmployeeId() != null ? !getEmployeeByEmployeeId().equals(order.getEmployeeByEmployeeId()) : order.getEmployeeByEmployeeId() != null)
            return false;
        if (getCustomerByCustomerId() != null ? !getCustomerByCustomerId().equals(order.getCustomerByCustomerId()) : order.getCustomerByCustomerId() != null)
            return false;
        return getShipperByShipVia() != null ? getShipperByShipVia().equals(order.getShipperByShipVia()) : order.getShipperByShipVia() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getCustomerId() != null ? getCustomerId().hashCode() : 0);
        result = 31 * result + (getEmployeeId() != null ? getEmployeeId().hashCode() : 0);
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
        result = 31 * result + (getRequiredDate() != null ? getRequiredDate().hashCode() : 0);
        result = 31 * result + (getShippedDate() != null ? getShippedDate().hashCode() : 0);
        result = 31 * result + (getShipVia() != null ? getShipVia().hashCode() : 0);
        result = 31 * result + (getFreight() != null ? getFreight().hashCode() : 0);
        result = 31 * result + (getShipName() != null ? getShipName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getShipRegion() != null ? getShipRegion().hashCode() : 0);
        result = 31 * result + (getEmployeeByEmployeeId() != null ? getEmployeeByEmployeeId().hashCode() : 0);
        result = 31 * result + (getCustomerByCustomerId() != null ? getCustomerByCustomerId().hashCode() : 0);
        result = 31 * result + (getShipperByShipVia() != null ? getShipperByShipVia().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", customerId=").append(customerId);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", requiredDate=").append(requiredDate);
        sb.append(", shippedDate=").append(shippedDate);
        sb.append(", shipVia=").append(shipVia);
        sb.append(", freight=").append(freight);
        sb.append(", shipName='").append(shipName).append('\'');
        sb.append(", address=").append(address);
        sb.append(", shipRegion='").append(shipRegion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
