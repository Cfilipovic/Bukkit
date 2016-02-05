package com.cfil360.AdvancedBan.Util;

import com.cfil360.AdvancedBan.Objects.Ban;
import com.cfil360.AdvancedBan.Objects.Mute;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by connor on 6/6/2014.
 */
public class DateUtil {
    public static DateFormat formatDate() { return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    public static String getBanLength(Ban ban) {
        //time for expiration
        Calendar expiration = new GregorianCalendar();
        expiration.setTime(ban.getExpiration());

        //current time
        Calendar current = Calendar.getInstance();

        long diff = expiration.getTimeInMillis() - current.getTimeInMillis();
        int x = (int) diff / 1000;
        int seconds = x % 60;
        x /= 60;
        int minutes = x % 60;
        x /= 60;
        int hours = x % 24;
        x /= 24;
        int days = x;

        return days + "d" + hours + "h" + minutes + "m" + seconds + "s";
    }

    public static String getMuteLength(Mute mute) {
        //time for expiration
        Calendar expiration = new GregorianCalendar();
        expiration.setTime(mute.getExpiration());

        //current time
        Calendar current = Calendar.getInstance();

        long diff = expiration.getTimeInMillis() - current.getTimeInMillis();
        int x = (int) diff / 1000;
        int seconds = x % 60;
        x /= 60;
        int minutes = x % 60;
        x /= 60;
        int hours = x % 24;
        x /= 24;
        int days = x;

        return days + "d" + hours + "h" + minutes + "m" + seconds + "s";
    }
}
