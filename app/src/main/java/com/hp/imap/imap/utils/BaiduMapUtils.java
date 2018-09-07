package com.hp.imap.imap.utils;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;

/**
 * Created by zhyangv on 2018/9/5.
 */

public class BaiduMapUtils {

    //GCJ-02——>百度地图
    public static LatLng toBaiduLocation(double longitude, double latitude){
        LogUtil.i("toBaiduLocation");
        // 将google地图、soso地图、aliyun地图、mapabc地图和amap地图// 所用坐标转换成百度坐标
        LatLng socLatLng=new LatLng(latitude,longitude);
        LogUtil.i("2");
        CoordinateConverter converter  = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.COMMON);
// sourceLatLng待转换坐标
        LogUtil.i("3");
        converter.coord(socLatLng);
        LatLng desLatLng = converter.convert();
        LogUtil.i(desLatLng.toString());
        return desLatLng;

    }
}
