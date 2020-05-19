package com.example.busanapp.Common;
import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Common {

    public static final  String APP_ID="d1ce79865abbaf66dc12b6ad6368826a";
    public static Location current_location = null;


    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf =new SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale.KOREA);
        String formatted = sdf.format(date);

        return formatted;
    }

    public static String convertUnixToHour(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm",Locale.KOREA);
        String formatted = sdf.format(date);
        return formatted;
    }
}
