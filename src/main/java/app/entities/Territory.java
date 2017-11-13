package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "territories")
public class Territory implements Serializable {

    protected Territory() {
    }

    public Territory(String territoryDescription, Short regionId) {
        if (territoryDescription == null || regionId == null) throw new IllegalArgumentException();
        this.territoryDescription = territoryDescription;
        this.regionId = regionId;
    }

    @Id
    @Column(name = "territory_id", nullable = false, length = 20)
    protected String territoryId;

    @Column(name = "territory_description", nullable = false, length = 40)
    protected String territoryDescription;

    @Column(name = "region_id", nullable = false)
    protected Short regionId;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id", nullable = false, insertable = false, updatable = false)
    protected Region regionByRegionId;

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        this.territoryId = territoryId;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        if (territoryDescription != null && !territoryDescription.isEmpty())
            this.territoryDescription = territoryDescription;
    }

    public Short getRegionId() {
        return regionId;
    }

    public void setRegionId(Short regionId) {
        if (regionId != null) this.regionId = regionId;
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
        return getRegionByRegionId() != null ? getRegionByRegionId().equals(territory.getRegionByRegionId()) : territory.getRegionByRegionId() == null;
    }

    @Override
    public int hashCode() {
        int result = getTerritoryId() != null ? getTerritoryId().hashCode() : 0;
        result = 31 * result + (getTerritoryDescription() != null ? getTerritoryDescription().hashCode() : 0);
        result = 31 * result + (getRegionId() != null ? getRegionId().hashCode() : 0);
        result = 31 * result + (getRegionByRegionId() != null ? getRegionByRegionId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Territory{");
        sb.append("territoryId=").append(territoryId);
        sb.append(", territoryDescription='").append(territoryDescription).append('\'');
        sb.append(", regionId=").append(regionId);
        sb.append('}');
        return sb.toString();
    }
}
