package app.queries;

import app.entities.Supplier;
import app.utils.DatabaseUtils;

import java.util.List;

public class SuppliersHelper extends EntitiesHelper<Supplier> {

    public SuppliersHelper(DatabaseUtils dbutils) {
        super(dbutils);
    }


    @Override
    public List<Supplier> selectAll(){
        prepareConnectionToDB();

        List<Supplier> suppliers = em.createQuery("SELECT c FROM Supplier c", Supplier.class).getResultList();

        closeConnectionToDB();
        return suppliers;
    }

}
