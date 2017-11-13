package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    protected Category() {
    }

    public Category(String categoryName, String description) {
        if (categoryName == null) throw new IllegalArgumentException();
        this.categoryName = categoryName;
        this.description = description;
    }

    @Id
    @GeneratedValue
    @Column(name = "category_id", nullable = false)
    protected short categoryId;

    @NotNull
    @Column(name = "category_name", nullable = false, length = 15)
    protected String categoryName;

    protected String description;


    public short getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if (categoryName != null && !categoryName.isEmpty()) this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (getCategoryId() != category.getCategoryId()) return false;
        if (getCategoryName() != null ? !getCategoryName().equals(category.getCategoryName()) : category.getCategoryName() != null)
            return false;
        return getDescription() != null ? getDescription().equals(category.getDescription()) : category.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) getCategoryId();
        result = 31 * result + (getCategoryName() != null ? getCategoryName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Category{");
        sb.append("categoryId=").append(categoryId);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
