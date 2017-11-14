package app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "territories")
public class Territory implements Serializable {

    protected Territory() {
    }

    public Territory(String territoryDescription, Region region) {
        if (territoryDescription == null || region == null) throw new IllegalArgumentException();
        this.territoryDescription = territoryDescription;
        this.region = region;
    }

    @Id
    @Column(name = "territory_id", nullable = false, length = 20)
    private String territoryId;

    @Column(name = "territory_description", nullable = false, length = 40)
    private String territoryDescription;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id", nullable = false)
    private Region region;


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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region regionId) {
        if (regionId != null) this.region = regionId;
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
        return getRegion() != null ? getRegion().equals(territory.getRegion()) : territory.getRegion() == null;
    }

    @Override
    public int hashCode() {
        int result = getTerritoryId() != null ? getTerritoryId().hashCode() : 0;
        result = 31 * result + (getTerritoryDescription() != null ? getTerritoryDescription().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Territory{");
        sb.append("territoryId=").append(territoryId);
        sb.append(", territoryDescription='").append(territoryDescription).append('\'');
        sb.append(", regionId=").append(region);
        sb.append('}');
        return sb.toString();
    }
}
