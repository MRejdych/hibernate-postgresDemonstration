package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "territories")
public class Territory  implements Serializable {

    protected Territory() {
    }

    public Territory(String territoryDescription, Long regionId) {
        if(territoryDescription == null || regionId == null) throw new IllegalArgumentException();
        this.territoryDescription = territoryDescription;
        this.regionId = regionId;
    }

    @Id
    @GeneratedValue
    @Column(name = "territory_id", nullable = false)
    protected Long territoryId;

    @Column(name = "territory_description", nullable = false)
    protected String territoryDescription;

    @Column(name = "region_id", nullable = false)
    protected Long regionId;

    @OneToMany(mappedBy = "territoryByTerritoryId")
    protected Collection<EmployeeTerritory> employeeTerritoriesByTerritoryId;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id", nullable = false, insertable = false, updatable = false)
    protected Region regionByRegionId;

    public Long getTerritoryId() {
        return territoryId;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        if(territoryDescription != null && !territoryDescription.isEmpty())
            this.territoryDescription = territoryDescription;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        if(regionId != null) this.regionId = regionId;
    }

    public Collection<EmployeeTerritory> getEmployeeTerritoriesByTerritoryId() {
        return employeeTerritoriesByTerritoryId;
    }

    public void setEmployeeTerritoriesByTerritoryId(Collection<EmployeeTerritory> employeeTerritoriesByTerritoryId) {
        this.employeeTerritoriesByTerritoryId = employeeTerritoriesByTerritoryId;
    }

    public Region getRegionByRegionId() {
        return regionByRegionId;
    }

    public void setRegionByRegionId(Region regionByRegionId) {
        this.regionByRegionId = regionByRegionId;
    }
}
