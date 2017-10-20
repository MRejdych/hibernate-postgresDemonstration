package backend.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
}
