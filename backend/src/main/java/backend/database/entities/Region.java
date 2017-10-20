package backend.database.entities;

import javax.persistence.*;
import java.io.Serializable;

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


    public Long getRegionId() {
        return regionId;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }
}
