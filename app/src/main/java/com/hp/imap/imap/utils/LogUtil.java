package com.hp.imap.imap.utils;

import android.util.Log;

/**
 * Created by zhyangv on 2017/9/6.
 */

public class LogUtil {
    private final static String TAG="mIPSA";
    /*private static boolean DEBUG_V = WorkConstants.isTest;
    private static boolean DEBUG_D = WorkConstants.isTest;
    private static boolean DEBUG_I = WorkConstants.isTest;
    private static boolean DEBUG_W = WorkConstants.isTest;
    private static boolean DEBUG_E = WorkConstants.isTest;*/
    /*private static boolean DEBUG_V = false;
    private static boolean DEBUG_D = false;
    private static boolean DEBUG_I = false;
    private static boolean DEBUG_W = false;
    private static boolean DEBUG_E = false;*/

    private static boolean DEBUG_V = true;
    private static boolean DEBUG_D = true;
    private static boolean DEBUG_I = true;
    private static boolean DEBUG_W = true;
    private static boolean DEBUG_E = true;

    public static void v(String msg) {
        if (DEBUG_V) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG_D) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG_I) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG_W) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG_E) {
            Log.e(TAG, msg);
        }
    }
}
