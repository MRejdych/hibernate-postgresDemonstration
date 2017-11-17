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

        List<Category> categories = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();

        closeConnectionToDB();
        return categories;
    }
}
