package backend.database.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
    @Column(name = "company_name", nullable = false)
    protected String companyName;

    protected String phone;


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
}
