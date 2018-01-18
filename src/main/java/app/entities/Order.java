package app.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    public Order() {
    }

    public Order(Customer customer, Employee employee, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate,
                 Shipper shipVia, Float freight, Address address) {
        this.customer = customer;
        this.employee = employee;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @SequenceGenerator(name="pk_sequence", sequenceName="order_id_seq", allocationSize = 1)
    @Column(name = "order_id", nullable = false)
    private Short orderId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "required_date")
    private LocalDate requiredDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "ship_via", referencedColumnName = "shipper_id")
    private Shipper shipVia;

    private Float freight;

    @Column(name = "ship_name", length = 40)
    private String shipName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "ship_address", length = 60)),
            @AttributeOverride(name = "city", column = @Column(name = "ship_city", length = 15)),
            @AttributeOverride(name = "postalCode", column = @Column(name = "ship_postal_code", length = 10)),
            @AttributeOverride(name = "country", column = @Column(name = "ship_country", length = 15)),
    })
    protected Address address;

    @Column(name = "ship_region", length = 15)
    private String shipRegion;

    @OneToMany(mappedBy = "order" , fetch = EAGER , cascade = ALL)
    protected List<OrderDetails> orderDetails = new ArrayList<>();

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Short getOrderId() {
        return orderId;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerId) {
        this.customer = customerId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeId) {
        this.employee = employeeId;
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

    public Shipper getShipVia() {
        return shipVia;
    }

    public void setShipVia(Shipper shipVia) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getOrderId() != null ? !getOrderId().equals(order.getOrderId()) : order.getOrderId() != null) return false;
        if (getCustomer() != null ? !getCustomer().equals(order.getCustomer()) : order.getCustomer() != null)
            return false;
        if (getEmployee() != null ? !getEmployee().equals(order.getEmployee()) : order.getEmployee() != null)
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
        return getShipRegion() != null ? getShipRegion().equals(order.getShipRegion()) : order.getShipRegion() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getEmployee() != null ? getEmployee().hashCode() : 0);
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
        result = 31 * result + (getRequiredDate() != null ? getRequiredDate().hashCode() : 0);
        result = 31 * result + (getShippedDate() != null ? getShippedDate().hashCode() : 0);
        result = 31 * result + (getShipVia() != null ? getShipVia().hashCode() : 0);
        result = 31 * result + (getFreight() != null ? getFreight().hashCode() : 0);
        result = 31 * result + (getShipName() != null ? getShipName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getShipRegion() != null ? getShipRegion().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", customerId=").append(customer);
        sb.append(", employeeId=").append(employee);
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
