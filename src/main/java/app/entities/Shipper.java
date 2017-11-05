package app.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "shippers")
public class Shipper  implements Serializable {

    protected Shipper() {
    }

    public Shipper(String companyName, String phone) {
        if(companyName == null) throw new IllegalArgumentException();
        this.companyName = companyName;
        this.phone = phone;
    }

    @Id
    @GeneratedValue
    @Column(name = "shipper_id", nullable = false)
    protected Long shipperId;

    @NotNull
    @Column(name = "company_name", nullable = false, length = 40)
    protected String companyName;

    @Column(length = 24)
    protected String phone;

    @OneToMany(mappedBy = "shipperByShipVia")
    protected Collection<Order> ordersByShipperId;



    public Long getShipperId() {
        return shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if(companyName != null && !companyName.isEmpty()) this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Order> getOrdersByShipperId() {
        return ordersByShipperId;
    }

    public void setOrdersByShipperId(Collection<Order> ordersByShipperId) {
        this.ordersByShipperId = ordersByShipperId;
    }
}
