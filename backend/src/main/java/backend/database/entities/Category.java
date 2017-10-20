package backend.database.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name="categories")
public class Category implements Serializable {

    protected Category() {
    }

    public Category(String categoryName, String description) {
        if(categoryName == null) throw new IllegalArgumentException();
        this.categoryName = categoryName;
        this.description = description;
    }

    @Id
    @GeneratedValue
    @Column(name="category_id", nullable = false)
    protected Long categoryId;

    @NotNull
    @Column(name="category_name", nullable = false)
    protected String categoryName;

    protected String description;


    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if(categoryName != null && !categoryName.isEmpty()) this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
