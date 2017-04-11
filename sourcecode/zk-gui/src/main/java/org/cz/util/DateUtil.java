package org.cz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chenzhe8 on 2017/4/10.
 */
public class DateUtil {
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    private static final Collection DEFAULT_PATTERNS = Arrays.asList(new String[]{"EEE MMM d HH:mm:ss yyyy", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz"});
    private static final Date DEFAULT_TWO_DIGIT_YEAR_START;
    private static final TimeZone GMT;



    public static String formatDate(Date date) {
        return formatDate(date, "EEE, dd MMM yyyy HH:mm:ss zzz");
    }

    public static String formatDate(Date date, String pattern) {
        if(date == null) {
            throw new IllegalArgumentException("date is null");
        } else if(pattern == null) {
            throw new IllegalArgumentException("pattern is null");
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
            formatter.setTimeZone(GMT);
            return formatter.format(date);
        }
    }

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 0, 1, 0, 0);
        DEFAULT_TWO_DIGIT_YEAR_START = calendar.getTime();
        GMT = TimeZone.getTimeZone("GMT");
    }
}

