package com.cfil360.chatlogger.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by connor on 7/11/2014.
 */
public class DateUtil {
    public static DateFormat formatDate() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }
}

