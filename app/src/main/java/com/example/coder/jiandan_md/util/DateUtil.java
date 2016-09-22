package com.example.coder.jiandan_md.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by coder on 16/9/19.
 */
public class DateUtil {

    public static String getDateDistance(String dateString) {

        String distance = "";

        try {

            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);

            long time = date.getTime() - System.currentTimeMillis();

            if (time <= 300) {

                distance = "五分钟前";

            } else if (time > 300 && time <= 1800) {

                distance = "半小时前";

            } else if (time > 1800 && time <= 3600) {

                distance = "1小时前";

            } else if (time > 3600 && time <= 7200) {

                distance = "2小时前";

            } else if (time > 1800 && time <= 3600) {

                distance = "1小时前";

            } else if (time > 3600 && time <= 7200) {

                distance = "2小时前";

            } else if (time > 7200 && time <= 10800) {

                distance = "3小时前";

            } else if (time > 10800 && time <= 14400) {

                distance = "4小时前";

            } else if (time > 14400 && time <= 18000) {

                distance = "5小时前";

            } else if (time > 18000 && time <= 21600) {

                distance = "6小时前";

            } else if (time > 21600 && time <= 25200) {

                distance = "7小时前";

            } else if (time > 25200 && time <= 28800) {

                distance = "8小时前";

            } else if (time > 28800 && time <= 32400) {

                distance = "9小时前";

            } else if (time > 32400 && time <= 36000) {

                distance = "10小时前";

            } else if (time > 36000 && time <= 39600) {

                distance = "11小时前";

            } else if (time > 39600 && time <= 43200) {

                distance = "12小时前";

            } else if (time > 43200 && time <= 46800) {

                distance = "13小时前";

            } else if (time > 46800 && time <= 50400) {

                distance = "14小时前";

            } else if (time > 50400 && time <= 54000) {

                distance = "15小时前";

            } else if (time > 54000 && time <= 57600) {

                distance = "16小时前";

            } else if (time > 57600 && time <= 61200) {

                distance = "17小时前";

            } else if (time > 61200 && time <= 64800) {

                distance = "18小时前";

            } else if (time > 64800 && time <= 68400) {

                distance = "19小时前";

            } else if (time > 68400 && time <= 72000) {

                distance = "20小时前";

            } else if (time > 72000 && time <= 75600) {

                distance = "21小时前";

            } else if (time > 75600 && time <= 79200) {

                distance = "22小时前";

            } else if (time > 79200 && time <= 82800) {

                distance = "23小时前";

            } else if (time > 82800 && time <= 86400) {

                distance = "24小时前";

            } else if (time > 86400 && time <= 172800) {

                distance = "昨天";

            } else if (time > 172800 && time <= 259200) {

                distance = "前天";

            } else {

                Calendar calendar = Calendar.getInstance();

                calendar.setTime(date);

                distance = calendar.get(Calendar.YEAR) + "年" + calendar.get(Calendar.MONTH)
                           + "月" + calendar.get(Calendar.DAY_OF_WEEK) + "日";
            }



        } catch (ParseException e) {

            e.printStackTrace();

        }

        return distance;
    }
}
