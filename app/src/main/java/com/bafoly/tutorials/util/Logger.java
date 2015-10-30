package com.bafoly.tutorials.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Logger {

    static String TAG = "basar";
    static String rootPackage = "com.bafoly.tutorials.";

    public static void write(String text) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String callerPath = stackTraceElements[3].toString();

        String lastObject = callerPath.substring(rootPackage.length());
        String methodPart = lastObject.substring(0, lastObject.indexOf("("));
        String lineNumber = lastObject.substring(lastObject.indexOf(":")+1,lastObject.indexOf(")"));
        String wholePath = methodPart+":"+lineNumber;

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.US);
        Calendar c = Calendar.getInstance();
        String now = sdf.format(c.getTime());
        lineNumber = now+" ["+wholePath+"] "+text;
        Log.d(TAG, lineNumber);
    }
}