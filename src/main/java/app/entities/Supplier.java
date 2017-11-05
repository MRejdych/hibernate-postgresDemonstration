package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

    protected Supplier() {
    }

    public Supplier(String companyName, String contactName, String contactTitle, Address address, String phone,
                    String fax, String homePage) {
        if(companyName == null) throw new IllegalArgumentException();
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.homePage = homePage;
    }

    @Id
    @GeneratedValue
    @Column(name = "supplier_id", nullable = false)
    protected Long supplierId;

    @NotNull
    @Column(name = "company_name", nullable = false, length = 40)
    protected String companyName;

    @Column(name = "contact_name", length = 30)
    protected String contactName;

    @Column(name = "contact_title", length = 30)
    protected String contactTitle;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address", length = 60)),
            @AttributeOverride(name = "city", column = @Column(name = "city", length = 15)),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postal_code", length = 10)),
            @AttributeOverride(name = "country", column = @Column(name = "country", length = 15)),
    })
    protected Address address;

    @Column(length = 15)
    protected String region;

    @Column(length = 24)
    protected String phone;

    @Column(length = 24)
    protected String fax;

    @Column(name = "home_page")
    protected String homePage;

    @OneToMany(mappedBy = "supplierBySupplierId")
    protected Collection<Product> productsBySupplierId;


    public Long getSupplierId() {
        return supplierId;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public Collection<Product> getProductsBySupplierId() {
        return productsBySupplierId;
    }

    public void setProductsBySupplierId(Collection<Product> productsBySupplierId) {
        this.productsBySupplierId = productsBySupplierId;
    }
}
