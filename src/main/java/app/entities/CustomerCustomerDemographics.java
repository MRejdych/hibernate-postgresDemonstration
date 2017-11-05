package app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_customer_demographics")
public class CustomerCustomerDemographics implements Serializable {

    protected CustomerCustomerDemographics() {
    }

    public CustomerCustomerDemographics(Long customerId, Long customerTypeId) {
        if (customerId == null || customerTypeId == null) throw new IllegalArgumentException();
        this.customerId = customerId;
        this.customerTypeId = customerTypeId;
    }

    @Id
    @Column(name = "customer_id", nullable = false)
    protected Long customerId;

    @Id
    @Column(name = "customer_type_id", nullable = false)
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
        if (customerId != null) this.customerId = customerId;
    }

    public Long getCus1tomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Long cutomerTypeId) {
        if (cutomerTypeId != null) this.customerTypeId = cutomerTypeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerCustomerDemographics that = (CustomerCustomerDemographics) o;

        if (getCustomerId() != null ? !getCustomerId().equals(that.getCustomerId()) : that.getCustomerId() != null)
            return false;
        if (customerTypeId != null ? !customerTypeId.equals(that.customerTypeId) : that.customerTypeId != null)
            return false;
        if (getCustomerByCustomerId() != null ? !getCustomerByCustomerId().equals(that.getCustomerByCustomerId()) : that.getCustomerByCustomerId() != null)
            return false;
        return getCustomerDemographicByCustomerTypeId() != null ? getCustomerDemographicByCustomerTypeId().equals(that.getCustomerDemographicByCustomerTypeId()) : that.getCustomerDemographicByCustomerTypeId() == null;
    }

    @Override
    public int hashCode() {
        int result = getCustomerId() != null ? getCustomerId().hashCode() : 0;
        result = 31 * result + (customerTypeId != null ? customerTypeId.hashCode() : 0);
        result = 31 * result + (getCustomerByCustomerId() != null ? getCustomerByCustomerId().hashCode() : 0);
        result = 31 * result + (getCustomerDemographicByCustomerTypeId() != null ? getCustomerDemographicByCustomerTypeId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerCustomerDemographics{");
        sb.append("customerId=").append(customerId);
        sb.append(", customerTypeId=").append(customerTypeId);
        sb.append(", customerByCustomerId=").append(customerByCustomerId);
        sb.append(", customerDemographicByCustomerTypeId=").append(customerDemographicByCustomerTypeId);
        sb.append('}');
        return sb.toString();
    }
}
