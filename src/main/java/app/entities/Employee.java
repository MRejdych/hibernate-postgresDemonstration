package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name="employees")
public class Employee implements Serializable {

    protected Employee() {
    }

    public Employee(String lastName, String firstName, String title, String titleOfCourtesy, LocalDate birthDate,
                    LocalDate hireDate, Address address, String phone, String extension, String notes, short reportsTo) {
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
    @Column(name = "last_name", nullable = false, length = 20)
    protected String lastName;

    @NotNull
    @Column(name = "first_name", nullable = false, length = 10)
    protected String firstName;

    @Column(length = 30)
    protected String title;

    @Column(name = "title_of_courtesy", length = 25)
    protected String titleOfCourtesy;

    @Column(name = "birth_date")
    protected LocalDate birthDate;

    @Column(name = "hire_date")
    protected LocalDate hireDate;

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

    @Column(length = 4)
    protected String extension;

    protected String notes;

    @Column(name = "reports_to")
    protected short reportsTo;


    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "reports_to", insertable = false, updatable = false)
    protected Employee reporterByReporterId;

    @OneToMany(mappedBy = "reporterByReporterId")
    protected Collection<Employee> subordinatesByReporterId;

    @OneToMany(mappedBy = "employeeByEmployeeId")
    protected Collection<EmployeeTerritory> employeeTerritoriesByTerritoryId;

    @OneToMany(mappedBy = "employeeByEmployeeId")
    protected Collection<Order> ordersByEmployeeId;




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

    public short getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(short reportsTo) {
        this.reportsTo = reportsTo;
    }

    public Collection<EmployeeTerritory> getEmployeeTerritoriesByTerritoryId() {
        return employeeTerritoriesByTerritoryId;
    }

    public void setEmployeeTerritoriesByTerritoryId(Collection<EmployeeTerritory> employeeTerritoriesByTerritoryId) {
        this.employeeTerritoriesByTerritoryId = employeeTerritoriesByTerritoryId;
    }

    public Collection<Order> getOrdersByEmployeeId() {
        return ordersByEmployeeId;
    }

    public void setOrdersByEmployeeId(Collection<Order> ordersByEmployeeId) {
        this.ordersByEmployeeId = ordersByEmployeeId;
    }

    public Employee getReporterByReporterId() {
        return reporterByReporterId;
    }

    public void setReporterByReporterId(Employee reporterByReporterId) {
        this.reporterByReporterId = reporterByReporterId;
    }

    public Collection<Employee> getSubordinatesByReporterId() {
        return subordinatesByReporterId;
    }

    public void setSubordinatesByReporterId(Collection<Employee> subordinatesByReporterId) {
        this.subordinatesByReporterId = subordinatesByReporterId;
    }
}
