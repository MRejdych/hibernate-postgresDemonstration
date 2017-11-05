package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

    protected Supplier() {
    }

    public Supplier(String companyName, String contactName, String contactTitle, Address address, String phone,
                    String fax, String homePage) {
        if (companyName == null) throw new IllegalArgumentException();
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

    @OneToMany(mappedBy = "supplierBySupplierId", fetch = LAZY)
    protected Collection<Product> productsBySupplierId;


    public Long getSupplierId() {
        return supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if (companyName != null && !companyName.isEmpty()) this.companyName = companyName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (getSupplierId() != null ? !getSupplierId().equals(supplier.getSupplierId()) : supplier.getSupplierId() != null)
            return false;
        if (getCompanyName() != null ? !getCompanyName().equals(supplier.getCompanyName()) : supplier.getCompanyName() != null)
            return false;
        if (getContactName() != null ? !getContactName().equals(supplier.getContactName()) : supplier.getContactName() != null)
            return false;
        if (getContactTitle() != null ? !getContactTitle().equals(supplier.getContactTitle()) : supplier.getContactTitle() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(supplier.getAddress()) : supplier.getAddress() != null)
            return false;
        if (getRegion() != null ? !getRegion().equals(supplier.getRegion()) : supplier.getRegion() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(supplier.getPhone()) : supplier.getPhone() != null) return false;
        if (getFax() != null ? !getFax().equals(supplier.getFax()) : supplier.getFax() != null) return false;
        if (getHomePage() != null ? !getHomePage().equals(supplier.getHomePage()) : supplier.getHomePage() != null)
            return false;
        return getProductsBySupplierId() != null ? getProductsBySupplierId().equals(supplier.getProductsBySupplierId()) : supplier.getProductsBySupplierId() == null;
    }

    @Override
    public int hashCode() {
        int result = getSupplierId() != null ? getSupplierId().hashCode() : 0;
        result = 31 * result + (getCompanyName() != null ? getCompanyName().hashCode() : 0);
        result = 31 * result + (getContactName() != null ? getContactName().hashCode() : 0);
        result = 31 * result + (getContactTitle() != null ? getContactTitle().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getFax() != null ? getFax().hashCode() : 0);
        result = 31 * result + (getHomePage() != null ? getHomePage().hashCode() : 0);
        result = 31 * result + (getProductsBySupplierId() != null ? getProductsBySupplierId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Supplier{");
        sb.append("supplierId=").append(supplierId);
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", contactName='").append(contactName).append('\'');
        sb.append(", contactTitle='").append(contactTitle).append('\'');
        sb.append(", address=").append(address);
        sb.append(", region='").append(region).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", fax='").append(fax).append('\'');
        sb.append(", homePage='").append(homePage).append('\'');
        sb.append(", productsBySupplierId=").append(productsBySupplierId);
        sb.append('}');
        return sb.toString();
    }
}