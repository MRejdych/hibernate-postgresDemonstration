package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "territories")
public class Territory implements Serializable {

    protected Territory() {
    }

    public Territory(String territoryDescription, Long regionId) {
        if (territoryDescription == null || regionId == null) throw new IllegalArgumentException();
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
        if (territoryDescription != null && !territoryDescription.isEmpty())
            this.territoryDescription = territoryDescription;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        if (regionId != null) this.regionId = regionId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Territory territory = (Territory) o;

        if (getTerritoryId() != null ? !getTerritoryId().equals(territory.getTerritoryId()) : territory.getTerritoryId() != null)
            return false;
        if (getTerritoryDescription() != null ? !getTerritoryDescription().equals(territory.getTerritoryDescription()) : territory.getTerritoryDescription() != null)
            return false;
        if (getRegionId() != null ? !getRegionId().equals(territory.getRegionId()) : territory.getRegionId() != null)
            return false;
        if (getEmployeeTerritoriesByTerritoryId() != null ? !getEmployeeTerritoriesByTerritoryId().equals(territory.getEmployeeTerritoriesByTerritoryId()) : territory.getEmployeeTerritoriesByTerritoryId() != null)
            return false;
        return getRegionByRegionId() != null ? getRegionByRegionId().equals(territory.getRegionByRegionId()) : territory.getRegionByRegionId() == null;
    }

    @Override
    public int hashCode() {
        int result = getTerritoryId() != null ? getTerritoryId().hashCode() : 0;
        result = 31 * result + (getTerritoryDescription() != null ? getTerritoryDescription().hashCode() : 0);
        result = 31 * result + (getRegionId() != null ? getRegionId().hashCode() : 0);
        result = 31 * result + (getEmployeeTerritoriesByTerritoryId() != null ? getEmployeeTerritoriesByTerritoryId().hashCode() : 0);
        result = 31 * result + (getRegionByRegionId() != null ? getRegionByRegionId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Territory{");
        sb.append("territoryId=").append(territoryId);
        sb.append(", territoryDescription='").append(territoryDescription).append('\'');
        sb.append(", regionId=").append(regionId);
        sb.append(", employeeTerritoriesByTerritoryId=").append(employeeTerritoriesByTerritoryId);
        sb.append(", regionByRegionId=").append(regionByRegionId);
        sb.append('}');
        return sb.toString();
    }
}
