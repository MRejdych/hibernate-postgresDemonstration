package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    protected Employee() {
    }

    public Employee(String lastName, String firstName, String title, String titleOfCourtesy, LocalDate birthDate,
                    LocalDate hireDate, Address address, String phone, String extension, String notes, short reportsTo) {
        if (lastName == null || firstName == null) throw new IllegalArgumentException();
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
    @Column(name = "employee_id", nullable = false)
    protected Short employeeId;

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
            @AttributeOverride(name = "address", column = @Column(name = "address", length = 60)),
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


    public Short getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.isEmpty()) this.firstName = firstName;
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

    public Employee getReporterByReporterId() {
        return reporterByReporterId;
    }

    public void setReporterByReporterId(Employee reporterByReporterId) {
        this.reporterByReporterId = reporterByReporterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (getReportsTo() != employee.getReportsTo()) return false;
        if (getEmployeeId() != null ? !getEmployeeId().equals(employee.getEmployeeId()) : employee.getEmployeeId() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(employee.getLastName()) : employee.getLastName() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(employee.getFirstName()) : employee.getFirstName() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(employee.getTitle()) : employee.getTitle() != null) return false;
        if (getTitleOfCourtesy() != null ? !getTitleOfCourtesy().equals(employee.getTitleOfCourtesy()) : employee.getTitleOfCourtesy() != null)
            return false;
        if (getBirthDate() != null ? !getBirthDate().equals(employee.getBirthDate()) : employee.getBirthDate() != null)
            return false;
        if (getHireDate() != null ? !getHireDate().equals(employee.getHireDate()) : employee.getHireDate() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(employee.getAddress()) : employee.getAddress() != null)
            return false;
        if (getRegion() != null ? !getRegion().equals(employee.getRegion()) : employee.getRegion() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(employee.getPhone()) : employee.getPhone() != null) return false;
        if (getExtension() != null ? !getExtension().equals(employee.getExtension()) : employee.getExtension() != null)
            return false;
        if (getNotes() != null ? !getNotes().equals(employee.getNotes()) : employee.getNotes() != null) return false;
        return getReporterByReporterId() != null ? getReporterByReporterId().equals(employee.getReporterByReporterId()) : employee.getReporterByReporterId() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmployeeId() != null ? getEmployeeId().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getTitleOfCourtesy() != null ? getTitleOfCourtesy().hashCode() : 0);
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + (getHireDate() != null ? getHireDate().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getExtension() != null ? getExtension().hashCode() : 0);
        result = 31 * result + (getNotes() != null ? getNotes().hashCode() : 0);
        result = 31 * result + (int) getReportsTo();
        result = 31 * result + (getReporterByReporterId() != null ? getReporterByReporterId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", titleOfCourtesy='").append(titleOfCourtesy).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", hireDate=").append(hireDate);
        sb.append(", address=").append(address);
        sb.append(", region='").append(region).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", reportsTo=").append(reportsTo);
        sb.append(", reporterByReporterId=").append(reporterByReporterId);
        sb.append('}');
        return sb.toString();
    }
}
