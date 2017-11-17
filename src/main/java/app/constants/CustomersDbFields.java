package app.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CustomersDbFields {
    public static final String CUSTOMER_ID = "customer_id";
    public static final String COMPANY_NAME = "company_name";
    public static final String CONTACT_NAME = "contact_name";
    public static final String CONTACT_TITLE = "contact_title";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String POSTAL_CODE = "postal_code";
    public static final String COUNTRY = "country";
    public static final String FAX = "fax";
    public static final String PHONE = "phone";
    public static final String REGION = "region";

    public static Set<String> asSet(){
        String[] fields = {CUSTOMER_ID, COMPANY_NAME, CONTACT_NAME, CONTACT_TITLE, ADDRESS, CITY, POSTAL_CODE, COUNTRY, FAX, PHONE, REGION};
        return new HashSet<>(Arrays.asList(fields));
    }
}
