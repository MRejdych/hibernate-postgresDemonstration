package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "customer_demographics")
public class CustomerDemographic implements Serializable {

    protected CustomerDemographic() {
    }

    public CustomerDemographic(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    @Id
    @GeneratedValue
    @Column(name = "customer_type_id", nullable = false)
    protected short customerTypeId;

    @Column(name = "customer_description")
    protected String customerDesc;

    @OneToMany(mappedBy = "customerDemographicByCustomerTypeId")
    protected Collection<CustomerCustomerDemographics> customerCustomerDemographicsByCustomerTypeId;


    public short getCustomerTypeId() {
        return customerTypeId;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    public Collection<CustomerCustomerDemographics> getCustomerCustomerDemographicsByCustomerTypeId() {
        return customerCustomerDemographicsByCustomerTypeId;
    }

    public void setCustomerCustomerDemographicsByCustomerTypeId(Collection<CustomerCustomerDemographics> customerCustomerDemographicsByCustomerTypeId) {
        this.customerCustomerDemographicsByCustomerTypeId = customerCustomerDemographicsByCustomerTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDemographic that = (CustomerDemographic) o;

        if (getCustomerTypeId() != that.getCustomerTypeId()) return false;
        if (getCustomerDesc() != null ? !getCustomerDesc().equals(that.getCustomerDesc()) : that.getCustomerDesc() != null)
            return false;
        return getCustomerCustomerDemographicsByCustomerTypeId() != null ? getCustomerCustomerDemographicsByCustomerTypeId().equals(that.getCustomerCustomerDemographicsByCustomerTypeId()) : that.getCustomerCustomerDemographicsByCustomerTypeId() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) getCustomerTypeId();
        result = 31 * result + (getCustomerDesc() != null ? getCustomerDesc().hashCode() : 0);
        result = 31 * result + (getCustomerCustomerDemographicsByCustomerTypeId() != null ? getCustomerCustomerDemographicsByCustomerTypeId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerDemographic{");
        sb.append("customerTypeId=").append(customerTypeId);
        sb.append(", customerDesc='").append(customerDesc).append('\'');
        sb.append(", customerCustomerDemographicsByCustomerTypeId=").append(customerCustomerDemographicsByCustomerTypeId);
        sb.append('}');
        return sb.toString();
    }
}
