package app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_territories")
public class EmployeeTerritory implements Serializable {

    protected EmployeeTerritory() {
    }

    public EmployeeTerritory(Short employeeId, String territoryId) {
        if (employeeId == null || territoryId == null) throw new IllegalArgumentException();
        this.employeeId = employeeId;
        this.territoryId = territoryId;
    }

    @Id
    @Column(name = "employee_id", nullable = false)
    protected Short employeeId;

    @Id
    @Column(name = "territory_id", nullable = false, length = 20)
    protected String territoryId;


    public Short getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Short employeeId) {
        if (employeeId != null) this.employeeId = employeeId;
    }

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        if (territoryId != null) this.territoryId = territoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeTerritory that = (EmployeeTerritory) o;

        if (getEmployeeId() != null ? !getEmployeeId().equals(that.getEmployeeId()) : that.getEmployeeId() != null)
            return false;
        return getTerritoryId() != null ? getTerritoryId().equals(that.getTerritoryId()) : that.getTerritoryId() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmployeeId() != null ? getEmployeeId().hashCode() : 0;
        result = 31 * result + (getTerritoryId() != null ? getTerritoryId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmployeeTerritory{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", territoryId='").append(territoryId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
