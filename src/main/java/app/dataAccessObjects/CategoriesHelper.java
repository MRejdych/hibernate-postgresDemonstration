package app.dataAccessObjects;

import app.entities.Category;
import app.utils.DatabaseUtils;

import java.util.List;

public class CategoriesHelper extends EntitiesHelper<Category> {

    public CategoriesHelper(DatabaseUtils dbutils) {
        super(dbutils);
    }

    @Override
    public List<Category> selectAll(){
        prepareConnectionToDB();

        List<Category> categories = em.createQuery("SELECT c FROM Category c ORDER BY c.categoryId", Category.class).getResultList();

        closeConnectionToDB();
        return categories;
    }

    @Override
    public Category selectById(Short id) {
        prepareConnectionToDB();

        Category category = em.find(Category.class, id);

        closeConnectionToDB();
        return category;
    }
}
