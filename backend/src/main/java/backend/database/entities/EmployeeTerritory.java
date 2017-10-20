package backend.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "employee_territories")
public class EmployeeTerritory implements Serializable {

    protected EmployeeTerritory() {
    }

    public EmployeeTerritory(Long employeeId, Long territoryId) {
        if(employeeId == null || territoryId == null) throw new IllegalArgumentException();
        this.employeeId = employeeId;
        this.territoryId = territoryId;
    }

    @Id
    @Column(name = "employee_id", nullable = false)
    protected Long employeeId;

    @Id
    @Column(name = "territory_id", nullable = false)
    protected Long territoryId;


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        if(employeeId != null) this.employeeId = employeeId;
    }

    public Long getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(Long territoryId) {
        if(territoryId != null) this.territoryId = territoryId;
    }
}
