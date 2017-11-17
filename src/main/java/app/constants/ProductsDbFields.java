package app.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProductsDbFields {
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String SUPPLIER_ID = "supplier_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String QUANTITY_PER_UNIT = "quantity_per_unit";
    public static final String UNIT_PRICE = "unit_price";
    public static final String UNITS_IN_STOCK = "units_in_stock";
    public static final String UNITS_ON_ORDER = "units_on_order";
    public static final String REORDER_LEVEL = "reorder_level";
    public static final String DISCONTINUED = "discontinued";

    public static final String SUPPLIER = "supplier";
    public static final String CATEGORY = "category";
    
    public static Set<String> asSet(){
        String[] fields = {PRODUCT_ID, PRODUCT_NAME, SUPPLIER_ID, CATEGORY_ID, QUANTITY_PER_UNIT, UNIT_PRICE, UNITS_IN_STOCK, UNITS_ON_ORDER, REORDER_LEVEL, DISCONTINUED};
        return new HashSet<>(Arrays.asList(fields));
        
    }
}
