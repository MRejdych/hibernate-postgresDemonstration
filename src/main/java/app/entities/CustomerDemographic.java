package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

import static javax.persistence.FetchType.LAZY;

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


    public short getCustomerTypeId() {
        return customerTypeId;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDemographic that = (CustomerDemographic) o;

        if (getCustomerTypeId() != that.getCustomerTypeId()) return false;
        return getCustomerDesc() != null ? getCustomerDesc().equals(that.getCustomerDesc()) : that.getCustomerDesc() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) getCustomerTypeId();
        result = 31 * result + (getCustomerDesc() != null ? getCustomerDesc().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerDemographic{");
        sb.append("customerTypeId=").append(customerTypeId);
        sb.append(", customerDesc='").append(customerDesc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
