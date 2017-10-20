package backend.database.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="employees")
public class Employee implements Serializable {

    protected Employee() {
    }

    public Employee(String lastName, String firstName, String title, String titleOfCourtesy, LocalDate birthDate,
                    LocalDate hireDate, Address address, String phone, String extension, String notes, int reportsTo) {
        if(lastName == null || firstName == null) throw new IllegalArgumentException();
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.phone = phone;
        this.extension = extension;
        this.notes = notes;
        this.reportsTo = reportsTo;
    }

    @Id
    @GeneratedValue
    @Column(name="employee_id", nullable = false)
    protected Long employeeId;

    @NotNull
    @Column(name = "last_name", nullable = false)
    protected String lastName;

    @NotNull
    @Column(name = "first_name", nullable = false)
    protected String firstName;

    protected String title;

    @Column(name = "title_of_courtesy")
    protected String titleOfCourtesy;

    @Column(name = "birth_date")
    protected LocalDate birthDate;

    @Column(name = "hire_date")
    protected LocalDate hireDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "country")),
    })
    protected Address address;

    protected String phone;

    protected String extension;

    protected String notes;

    @Column(name = "reports_to")
    protected int reportsTo;







    public Long getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName != null && !lastName.isEmpty()) this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName != null && !firstName.isEmpty()) this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }
}
