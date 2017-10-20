package backend.database.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="customers")
public class Customer implements Serializable{

    protected Customer() {
    }

    public Customer(String companyName, String contactName, String contactTitle, Address address, String phone) {
        if(companyName == null) throw new IllegalArgumentException();
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.phone = phone;
    }

    @Id
    @GeneratedValue
    @Column(name="customer_id", nullable = false)
    protected Long customerId;

    @NotNull
    @Column(name="company_name", nullable = false)
    protected String companyName;

    @Column(name="contact_name")
    protected String contactName;

    @Column(name="contact_title")
    protected String contactTitle;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "country")),
    })
    protected Address address;

    protected String phone;


    public Long getCustomerId() {
        return customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if(companyName != null && !companyName.isEmpty()) this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
