package app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_customer_demographics")
public class CustomerCustomerDemographics implements Serializable {

    protected CustomerCustomerDemographics() {
    }

    public CustomerCustomerDemographics(Customer customer, CustomerDemographic customerDemographic) {
        if (customer == null || customerDemographic == null) throw new IllegalArgumentException();
        this.customer = customer;
        this.customerDemographic = customerDemographic;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_type_id", referencedColumnName = "customer_type_id", nullable = false)
    private CustomerDemographic customerDemographic;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerDemographic getCustomerDemographic() {
        return customerDemographic;
    }

    public void setCustomerDemographic(CustomerDemographic customerDemographic) {
        this.customerDemographic = customerDemographic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerCustomerDemographics that = (CustomerCustomerDemographics) o;

        if (!getCustomer().equals(that.getCustomer())) return false;
        return getCustomerDemographic().equals(that.getCustomerDemographic());
    }

    @Override
    public int hashCode() {
        int result = getCustomer().hashCode();
        result = 31 * result + getCustomerDemographic().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerCustomerDemographics{");
        sb.append("customer=").append(customer);
        sb.append(", customerDemographic=").append(customerDemographic);
        sb.append('}');
        return sb.toString();
    }
}
