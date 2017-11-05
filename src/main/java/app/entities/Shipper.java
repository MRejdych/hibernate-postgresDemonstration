package app.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "shippers")
public class Shipper implements Serializable {

    protected Shipper() {
    }

    public Shipper(String companyName, String phone) {
        if (companyName == null) throw new IllegalArgumentException();
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

    @OneToMany(mappedBy = "shipperByShipVia", fetch = LAZY)
    protected Collection<Order> ordersByShipperId;


    public Long getShipperId() {
        return shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if (companyName != null && !companyName.isEmpty()) this.companyName = companyName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipper shipper = (Shipper) o;

        if (getShipperId() != null ? !getShipperId().equals(shipper.getShipperId()) : shipper.getShipperId() != null)
            return false;
        if (getCompanyName() != null ? !getCompanyName().equals(shipper.getCompanyName()) : shipper.getCompanyName() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(shipper.getPhone()) : shipper.getPhone() != null) return false;
        return getOrdersByShipperId() != null ? getOrdersByShipperId().equals(shipper.getOrdersByShipperId()) : shipper.getOrdersByShipperId() == null;
    }

    @Override
    public int hashCode() {
        int result = getShipperId() != null ? getShipperId().hashCode() : 0;
        result = 31 * result + (getCompanyName() != null ? getCompanyName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getOrdersByShipperId() != null ? getOrdersByShipperId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Shipper{");
        sb.append("shipperId=").append(shipperId);
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", ordersByShipperId=").append(ordersByShipperId);
        sb.append('}');
        return sb.toString();
    }
}
