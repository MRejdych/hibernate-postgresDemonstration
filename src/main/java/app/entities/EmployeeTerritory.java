package app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_territories")
public class EmployeeTerritory implements Serializable {

    protected EmployeeTerritory() {
    }

    public EmployeeTerritory(Long employeeId, String territoryId) {
        if(employeeId == null || territoryId == null) throw new IllegalArgumentException();
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
        if(employeeId != null) this.employeeId = employeeId;
    }

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        if(territoryId != null) this.territoryId = territoryId;
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
}
