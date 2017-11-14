package app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_territories")
public class EmployeeTerritory implements Serializable {

    protected EmployeeTerritory() {
    }

    public EmployeeTerritory(Employee employee, Territory employeeTerritory) {
        if (employee == null || employeeTerritory == null) throw new IllegalArgumentException();
        this.employee = employee;
        this.territory = employeeTerritory;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "territory_id")
    private Territory territory;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeId) {
        if (employeeId != null) this.employee = employeeId;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territoryId) {
        if (territoryId != null) this.territory = territoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeTerritory that = (EmployeeTerritory) o;

        if (getEmployee() != null ? !getEmployee().equals(that.getEmployee()) : that.getEmployee() != null)
            return false;
        return getTerritory() != null ? getTerritory().equals(that.getTerritory()) : that.getTerritory() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmployee() != null ? getEmployee().hashCode() : 0;
        result = 31 * result + (getTerritory() != null ? getTerritory().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmployeeTerritory{");
        sb.append("employeeId=").append(employee);
        sb.append(", territoryId='").append(territory).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
