package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="customer_demographics")
public class CustomerDemographic implements Serializable {

    protected CustomerDemographic() {
    }

    public CustomerDemographic(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    @Id
    @GeneratedValue
    @Column(name="customer_type_id", nullable = false)
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
}
