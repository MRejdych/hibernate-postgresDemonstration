package app.utils;

import app.entities.Address;

public final class AddressBuilder {
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public AddressBuilder() {
    }

    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public Address build() {
        return new Address(street, city, postalCode, country);
    }
}
