package org.fsn.framework.common.utils;

import static java.lang.Math.PI;

/**
 * @ClassName MapsUtil
 * @Description 腾讯地图经纬度 半径计算
 * @Author 熊俊堡
 * @Date 2018/12/7 0007 下午 3:48
 */
public class MapUtil {

    /**
     * 根据经纬度和半径计算经纬度范围
     *
     * @param raidus 单位米
     * @return minLat, minLng, maxLat, maxLng
     */
    public static double[] getAround(double lat, double lon, int raidus) {
        Double latitude = lat;
        Double longitude = lon;

        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        return new double[]{minLat, minLng, maxLat, maxLng};
    }

}

