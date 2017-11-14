package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "regions")
public class Region implements Serializable {

    protected Region() {
    }

    public Region(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    @Id
    @GeneratedValue
    @Column(name = "region_id", nullable = false)
    private Short regionId;

    @Column(name = "region_description", nullable = false, length = 10)
    private String regionDescription;


    public Short getRegionId() {
        return regionId;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (getRegionId() != null ? !getRegionId().equals(region.getRegionId()) : region.getRegionId() != null)
            return false;
        return getRegionDescription() != null ? getRegionDescription().equals(region.getRegionDescription()) : region.getRegionDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getRegionId() != null ? getRegionId().hashCode() : 0;
        result = 31 * result + (getRegionDescription() != null ? getRegionDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Region{");
        sb.append("regionId=").append(regionId);
        sb.append(", regionDescription='").append(regionDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
