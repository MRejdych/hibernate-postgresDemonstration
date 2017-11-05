package app.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    protected Address() {
    }

    public Address(String street, String city, String postalCode, String country) {
        if (street != null && city != null && postalCode != null && country != null) {
            this.street = street;
            this.city = city;
            this.postalCode = postalCode;
            this.country = country;
        } else throw new IllegalArgumentException("Parameters can not be null");
    }

    @NotNull
    @Length(max = 40)
    protected String street;

    @NotNull
    @Length(max = 15)
    protected String city;

    @NotNull
    @Length(max = 10)
    protected String postalCode;

    @NotNull
    @Length(max = 15)
    protected String country;


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street != null && !street.isEmpty()) this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city != null && !city.isEmpty()) this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if (postalCode != null && !postalCode.isEmpty()) this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country != null && !country.isEmpty()) this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!getStreet().equals(address.getStreet())) return false;
        if (!getCity().equals(address.getCity())) return false;
        if (!getPostalCode().equals(address.getPostalCode())) return false;
        return getCountry().equals(address.getCountry());
    }

    @Override
    public int hashCode() {
        int result = getStreet().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getPostalCode().hashCode();
        result = 31 * result + getCountry().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Address{");
        sb.append("street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
