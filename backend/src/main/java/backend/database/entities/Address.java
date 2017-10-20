package backend.database.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable{

    protected Address() {
    }

    public Address(String street, String city, String postalCode, String country) {
        if(street != null && city != null && postalCode != null && country != null) {
            this.street = street;
            this.city = city;
            this.postalCode = postalCode;
            this.country = country;
        } else throw new IllegalArgumentException("Parameters can not be null");
    }

    @NotNull
    protected String street;

    @NotNull
    protected String city;

    @NotNull
    protected String postalCode;

    @NotNull
    protected String country;


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(street != null && !street.isEmpty()) this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city != null && !city.isEmpty()) this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if(postalCode != null && !postalCode.isEmpty()) this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if(country != null && !country.isEmpty()) this.country = country;
    }
}
