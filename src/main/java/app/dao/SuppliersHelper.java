package app.dao;

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

        List<Supplier> suppliers = em.createQuery("SELECT s FROM Supplier s ORDER BY s.supplierId", Supplier.class).getResultList();

        closeConnectionToDB();
        return suppliers;
    }

    @Override
    public Supplier selectById(Short id) {
        prepareConnectionToDB();

        Supplier supplier = em.find(Supplier.class, id);

        closeConnectionToDB();
        return supplier;
    }

}
