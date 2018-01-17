package app.utils;

import java.time.LocalDate;

public class Dates {

    public static LocalDate parseDate(String date){
        if(date == null || date == ""){
            return null;
        }

        String[] parts = date.split("\\.");

        if (parts.length == 3) {
            Integer day = Integer.valueOf(parts[0]);
            Integer month = Integer.valueOf(parts[1]);
            Integer year = Integer.valueOf(parts[2]);
            return LocalDate.of(year, month, day);
        } else {
            throw new IllegalArgumentException("Wrong date format!");
        }
    }
}
