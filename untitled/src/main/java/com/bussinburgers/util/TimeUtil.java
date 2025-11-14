package com.bussinburgers.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    // Returns timestamps like "20250316-184455"
    public static String getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return now.format(format);
    }
}
