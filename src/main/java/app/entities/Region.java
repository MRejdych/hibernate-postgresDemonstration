package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "regions")
public class Region  implements Serializable {

    protected Region() {
    }

    public Region(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    @Id
    @GeneratedValue
    @Column(name = "region_id", nullable = false)
    protected Long regionId;

    @Column(name = "region_description", nullable = false)
    protected String regionDescription;

    @OneToMany(mappedBy = "regionByRegionId")
    protected Collection<Territory> territoryByRegionId;


    public Long getRegionId() {
        return regionId;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    public Collection<Territory> getTerritoryByRegionId() {
        return territoryByRegionId;
    }

    public void setTerritoryByRegionId(Collection<Territory> territoryByRegionId) {
        this.territoryByRegionId = territoryByRegionId;
    }
}
