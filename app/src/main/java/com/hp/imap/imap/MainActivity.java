package com.hp.imap.imap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baidu.mapapi.model.LatLng;
import com.hp.imap.imap.utils.BaiduMapUtils;
import com.hp.imap.imap.utils.LogUtil;

public class MainActivity extends AppCompatActivity {

    private AMapLocationClient aMapLocationClient;
    private double mLatitude;
    private double mLongitude;
    private final int LOCATION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAMap();
    }


    private void starLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
        /*        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "无法获取您的打卡ID，请在权限管理中打开本机识别码权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "没有权限,请手动开启定权位限", Toast.LENGTH_SHORT).show();
                }*/
                // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, LOCATION_CODE);
            } else {
                //启动定位
                aMapLocationClient.startLocation();
            }
        } else {
            //启动定位
            aMapLocationClient.startLocation();
        }
    }

    private void initAMap() {
        LogUtil.i("初始化高德地图");
        //初始化定位
        aMapLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        aMapLocationClient.setLocationListener(mLocationListener);
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
/*        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);*/
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(5000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
/*        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);*/
        //给定位客户端对象设置定位参数
        aMapLocationClient.setLocationOption(mLocationOption);
        starLocation();

    }

    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        //定位回调函数
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            LogUtil.i("onLocationChanged amapLocation.getErrorCode():"+amapLocation.getErrorCode());
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                    double latitude = amapLocation.getLatitude();//获取纬度
                    double longitude = amapLocation.getLongitude();//获取经度
                    LogUtil.i("定位成功 经度:" + amapLocation.getLongitude() + "\t纬度:" + amapLocation.getLatitude());
                    // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                    LatLng desLatLng= BaiduMapUtils.toBaiduLocation(longitude,latitude);
                    LogUtil.i("1");
                    mLatitude=desLatLng.latitude;
                    mLongitude=desLatLng.longitude;
                    LogUtil.i("转换之后经度:"+mLongitude+"\t纬度:"+mLatitude);
                }
            }

        }

        ;
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LogUtil.i("onRequestPermissionsResult");
        switch (requestCode) {
            case LOCATION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LogUtil.i("授权请求被允许");
                    aMapLocationClient.startLocation();
                } else {
                    LogUtil.e("授权请求被允许");
                }

                return;
            }
        }
    }

}
