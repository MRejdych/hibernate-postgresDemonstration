package app.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    protected Address() {
    }

    public Address(String address, String city, String postalCode, String country) {
        if (address != null && city != null && postalCode != null && country != null) {
            this.address = address;
            this.city = city;
            this.postalCode = postalCode;
            this.country = country;
        } else throw new IllegalArgumentException("Parameters can not be null");
    }

    @Length(max = 40)
    private String address;

    @Length(max = 15)
    private String city;

    @Length(max = 10)
    private String postalCode;

    @Length(max = 15)
    private String country;


    public String getAddress() {
        return address;
    }

    public void setAddress(String street) {
        if (street != null && !street.isEmpty()) this.address = street;
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

        if (!getAddress().equals(address.getAddress())) return false;
        if (!getCity().equals(address.getCity())) return false;
        if (!getPostalCode().equals(address.getPostalCode())) return false;
        return getCountry().equals(address.getCountry());
    }

    @Override
    public int hashCode() {
        int result = getAddress().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getPostalCode().hashCode();
        result = 31 * result + getCountry().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Address{");
        sb.append("street='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
