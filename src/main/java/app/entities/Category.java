package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.*;
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
    @SequenceGenerator(name="pk_sequence",sequenceName="category_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "category_id", nullable = false)
    private short categoryId;

    @NotNull
    @Column(name = "category_name", nullable = false, length = 15)
    private String categoryName;

    private String description;


    @OneToMany(mappedBy = "category", fetch = LAZY, cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Product> products = new ArrayList<>();

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

    public void addProduct(Product product){
        products.add(product);

        if(!product.getCategory().equals(this)) product.setCategory(this);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
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
