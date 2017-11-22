package app.entities;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static app.constants.CustomersDbFields.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    protected Customer() {
    }

    public Customer(String companyName, String contactName, String contactTitle, Address address, String phone,
                    String region, String fax) {
        // if (companyName == null) throw new IllegalArgumentException();
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.phone = phone;
        this.region = region;
        this.fax = fax;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @SequenceGenerator(name = "pk_sequence", sequenceName = "customer_id_seq", allocationSize = 1)
    @Column(name = CUSTOMER_ID, nullable = false)
    private short customerId;

    @NotNull
    @Column(name = COMPANY_NAME, nullable = false, length = 40)
    private String companyName;

    @Column(name = CONTACT_NAME, length = 30)
    private String contactName;

    @Column(name = CONTACT_TITLE, length = 30)
    private String contactTitle;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = ADDRESS, length = 60)),
            @AttributeOverride(name = "city", column = @Column(name = CITY, length = 15)),
            @AttributeOverride(name = "postalCode", column = @Column(name = POSTAL_CODE, length = 15)),
            @AttributeOverride(name = "country", column = @Column(name = COUNTRY, length = 15)),
    })
    private Address address;

    @Column(name = REGION, length = 15)
    private String region;

    @Column(name = PHONE, length = 24)
    private String phone;

    @Column(name = FAX, length = 24)
    private String fax;


    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(
            name = "customer_customer_demographics",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_type_id")}
    )
    private List<CustomerDemographic> customerDemographics = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = LAZY, cascade = ALL)
    List<Order> orders = new ArrayList<>();

    public void copyStateOfAnotherCustomer(Customer that) {
        this.companyName = that.getCompanyName();
        this.contactName = that.getContactName();
        this.contactTitle = that.getContactTitle();
        this.address = that.getAddress();
        this.region = that.getRegion();
        this.phone = that.getPhone();
        this.fax = that.getFax();
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setCustomerDemographics(List<CustomerDemographic> customerDemographics) {
        this.customerDemographics = customerDemographics;
    }


    public List<CustomerDemographic> getCustomerDemographics() {
        return customerDemographics;
    }

    public short getCustomerId() {
        return customerId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (getCustomerId() != customer.getCustomerId()) return false;
        if (getCompanyName() != null ? !getCompanyName().equals(customer.getCompanyName()) : customer.getCompanyName() != null)
            return false;
        if (getContactName() != null ? !getContactName().equals(customer.getContactName()) : customer.getContactName() != null)
            return false;
        if (getContactTitle() != null ? !getContactTitle().equals(customer.getContactTitle()) : customer.getContactTitle() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(customer.getAddress()) : customer.getAddress() != null)
            return false;
        if (getRegion() != null ? !getRegion().equals(customer.getRegion()) : customer.getRegion() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(customer.getPhone()) : customer.getPhone() != null) return false;
        return getFax() != null ? getFax().equals(customer.getFax()) : customer.getFax() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) getCustomerId();
        result = 31 * result + (getCompanyName() != null ? getCompanyName().hashCode() : 0);
        result = 31 * result + (getContactName() != null ? getContactName().hashCode() : 0);
        result = 31 * result + (getContactTitle() != null ? getContactTitle().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getFax() != null ? getFax().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Customer{");
        sb.append("customerId=").append(customerId);
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", contactName='").append(contactName).append('\'');
        sb.append(", contactTitle='").append(contactTitle).append('\'');
        sb.append(", address=").append(address);
        sb.append(", region='").append(region).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", fax='").append(fax).append('\'');
        sb.append('}').append("\n");
        return sb.toString();
    }
}
