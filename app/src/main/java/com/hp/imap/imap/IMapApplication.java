package com.hp.imap.imap;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by zhyangv on 2018/9/6.
 */

public class IMapApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext()); // 不能传递Activity，必须是全局Context

    }
}
