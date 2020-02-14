package com.writetoexcel.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getLocalDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_YYYY");
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        LocalDateTime zoneDateTime = LocalDateTime.now(zoneId);
        return formatter.format(zoneDateTime);
    }

    public static String getLocalDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_YYYY hh:mm:ss");
        ZoneId zoneid = ZoneId.of("Asia/Kolkata");
        LocalDateTime zoneDateTime = LocalDateTime.now(zoneid);
        return formatter.format(zoneDateTime);
    }
}
