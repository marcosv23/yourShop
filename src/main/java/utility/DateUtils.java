package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class DateUtils {
    public DateUtils() {}

    public static Instant getInstantFromString(String strDate) {
        return LocalDateTime.parse(strDate).toInstant(ZoneOffset.UTC);
    }

    public static Instant getInstantFromStringImpl2(String utcString) throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final java.util.Date dateObj = sdf.parse(utcString);
        return dateObj.toInstant();
    }

}
