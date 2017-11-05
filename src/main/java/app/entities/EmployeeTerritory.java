package app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_territories")
public class EmployeeTerritory implements Serializable {

    protected EmployeeTerritory() {
    }

    public EmployeeTerritory(Long employeeId, String territoryId) {
        if (employeeId == null || territoryId == null) throw new IllegalArgumentException();
        this.employeeId = employeeId;
        this.territoryId = territoryId;
    }

    @Id
    @Column(name = "employee_id", nullable = false)
    protected Long employeeId;

    @Id
    @Column(name = "territory_id", nullable = false, length = 20)
    protected String territoryId;

    @ManyToOne
    @JoinColumn(name = "territory_id", referencedColumnName = "territory_id", nullable = false, insertable = false, updatable = false)
    protected Territory territoryByTerritoryId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false, insertable = false, updatable = false)
    protected Employee employeeByEmployeeId;


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        if (employeeId != null) this.employeeId = employeeId;
    }

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        if (territoryId != null) this.territoryId = territoryId;
    }

    public Territory getTerritoryByTerritoryId() {
        return territoryByTerritoryId;
    }

    public void setTerritoryByTerritoryId(Territory territoryByTerritoryId) {
        this.territoryByTerritoryId = territoryByTerritoryId;
    }

    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeTerritory that = (EmployeeTerritory) o;

        if (getEmployeeId() != null ? !getEmployeeId().equals(that.getEmployeeId()) : that.getEmployeeId() != null)
            return false;
        if (getTerritoryId() != null ? !getTerritoryId().equals(that.getTerritoryId()) : that.getTerritoryId() != null)
            return false;
        if (getTerritoryByTerritoryId() != null ? !getTerritoryByTerritoryId().equals(that.getTerritoryByTerritoryId()) : that.getTerritoryByTerritoryId() != null)
            return false;
        return getEmployeeByEmployeeId() != null ? getEmployeeByEmployeeId().equals(that.getEmployeeByEmployeeId()) : that.getEmployeeByEmployeeId() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmployeeId() != null ? getEmployeeId().hashCode() : 0;
        result = 31 * result + (getTerritoryId() != null ? getTerritoryId().hashCode() : 0);
        result = 31 * result + (getTerritoryByTerritoryId() != null ? getTerritoryByTerritoryId().hashCode() : 0);
        result = 31 * result + (getEmployeeByEmployeeId() != null ? getEmployeeByEmployeeId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmployeeTerritory{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", territoryId='").append(territoryId).append('\'');
        sb.append(", territoryByTerritoryId=").append(territoryByTerritoryId);
        sb.append(", employeeByEmployeeId=").append(employeeByEmployeeId);
        sb.append('}');
        return sb.toString();
    }
}
