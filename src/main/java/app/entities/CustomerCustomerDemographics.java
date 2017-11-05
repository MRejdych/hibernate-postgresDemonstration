package app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="customer_customer_demographics")
public class CustomerCustomerDemographics implements Serializable {

    protected CustomerCustomerDemographics() {
    }

    public CustomerCustomerDemographics(Long customerId, Long customerTypeId) {
        if(customerId == null || customerTypeId == null) throw new IllegalArgumentException();
        this.customerId = customerId;
        this.customerTypeId = customerTypeId;
    }

    @Id
    @Column(name="customer_id", nullable = false)
    protected Long customerId;

    @Id
    @Column(name="customer_type_id", nullable = false)
    protected Long customerTypeId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false, insertable = false, updatable = false)
    protected Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "customer_type_id", referencedColumnName = "customer_type_id", nullable = false, insertable = false, updatable = false)
    protected CustomerDemographic customerDemographicByCustomerTypeId;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        if(customerId != null) this.customerId = customerId;
    }

    public Long getCus1tomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Long cutomerTypeId) {
        if(cutomerTypeId != null) this.customerTypeId = cutomerTypeId;
    }

    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    public CustomerDemographic getCustomerDemographicByCustomerTypeId() {
        return customerDemographicByCustomerTypeId;
    }

    public void setCustomerDemographicByCustomerTypeId(CustomerDemographic customerDemographicByCustomerTypeId) {
        this.customerDemographicByCustomerTypeId = customerDemographicByCustomerTypeId;
    }
}
