package app.utils;

import app.entities.Address;
import app.entities.Customer;

public final class CustomerBuilder {
    private String companyName;
    private String contactName;
    private String contactTitle;
    private Address address;
    private String region;
    private String phone;
    private String fax;

    public CustomerBuilder() {
    }

    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public CustomerBuilder withContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public CustomerBuilder withContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
        return this;
    }

    public CustomerBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder withRegion(String region) {
        this.region = region;
        return this;
    }

    public CustomerBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public Customer build() {
        return new Customer(companyName, contactName, contactTitle, address, phone, region, fax);
    }
}
