package backend.database.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="customer_demographic")
public class CustomerDemographic implements Serializable {

    protected CustomerDemographic() {
    }

    public CustomerDemographic(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    @Id
    @GeneratedValue
    @Column(name="customer_type_id", nullable = false)
    protected Long customerTypeId;

    @Column(name = "customer_description")
    protected String customerDesc;





    public Long getCustomerTypeId() {
        return customerTypeId;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }
}
