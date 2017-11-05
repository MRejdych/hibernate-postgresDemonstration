package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "orders")
public class Order  implements Serializable {

    protected Order() {
    }

    public Order(Long customerId, Long employeeId, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate,
                 Long shipVia, Float freight, Address address) {
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
    protected Long orderId;

    @Column(name = "customer_id")
    protected Long customerId;

    @Column(name = "employee_id")
    protected Long employeeId;

    @Column(name = "order_date")
    protected LocalDate orderDate;

    @Column(name = "required_date")
    protected LocalDate requiredDate;

    @Column(name = "shipped_date")
    protected LocalDate shippedDate;

    @Column(name = "ship_via")
    protected Long shipVia;

    protected Float freight;

    @Column(name = "ship_name", length = 40)
    protected String shipName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "ship_address", length = 60)),
            @AttributeOverride(name = "city", column = @Column(name = "ship_city", length = 15)),
            @AttributeOverride(name = "postalCode", column = @Column(name = "ship_postal_code", length = 10)),
            @AttributeOverride(name = "country", column = @Column(name = "ship_country", length = 15)),
    })
    protected Address address;

    @Column(name = "ship_region", length = 15)
    protected String shipRegion;

    @OneToMany(mappedBy = "orderByOrderId")
    protected Collection<OrderDetails> orderDetailsByOrderId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
    protected Employee employeeByEmployeeId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    protected Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "ship_via", referencedColumnName = "shipper_id", insertable = false, updatable = false)
    protected Shipper shipperByShipVia;


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

    public Collection<OrderDetails> getOrderDetailsByOrderId() {
        return orderDetailsByOrderId;
    }

    public void setOrderDetailsByOrderId(Collection<OrderDetails> orderDetailsByOrderId) {
        this.orderDetailsByOrderId = orderDetailsByOrderId;
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
}
