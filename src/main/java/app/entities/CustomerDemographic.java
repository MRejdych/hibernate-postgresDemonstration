package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
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
    @SequenceGenerator(name="pk_sequence",sequenceName="customer_type_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "customer_type_id", nullable = false)
    private short customerTypeId;

    @Column(name = "customer_description")
    private String customerDesc;


    private short getCustomerTypeId() {
        return customerTypeId;
    }

    private String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }


    @ManyToMany(fetch = LAZY, cascade = {PERSIST, MERGE, DETACH, REFRESH})
    @JoinTable(
            name="customer_customer_demographics",
            joinColumns = {@JoinColumn(name = "customer_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
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
