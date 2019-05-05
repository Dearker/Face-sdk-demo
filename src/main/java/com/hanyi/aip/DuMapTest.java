package com.hanyi.aip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dumap.DuMapClient;
import com.baidubce.services.dumap.model.*;
import com.baidubce.util.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @Description:
 * @Author: weiwenchang
 * @CreateDate: 2019/5/5 19:57
 */
public class DuMapTest {

    public static final String ACCESS_KEY_ID = "3c8d04991b8040dfae160d0668ee2f53";
    public static final String SECRET_ACCESS_KEY  = "e68328a4b20f497cbbd0f1de710756ca";

    public static final BceClientConfiguration config = new BceClientConfiguration()
            .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));

    public static final DuMapClient client = new DuMapClient(config);

    public static final String appId = "e3e0498f-3e14-4516-8727-db176b1cbd24";

    //经纬度转地址
    @Test
    public void mapTest(){
        //location左边的是纬度,右边的是经度
        ReverseGeocoderParam param = ReverseGeocoderParam.builder()
                .location("39.929986f,116.43244f")       // 根据经纬度坐标获取地址
                .output("json")                         // 输出格式为json字符串或者xml字符串
                .build();

        String response = client.reverseGeocoder(appId, param);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        System.out.println("获取的数据-->"+map);
    }

    //地址转经纬度,lat<纬度>,lng<经度>
    @Test
    public void addressTest(){

        GeocoderParam param = GeocoderParam.builder()
                .address("北京市海淀区上地十街10号")         // 待解析的地址
                .output("json")                          // 输出格式为json字符串或者xml字符串
                .build();
        String response = client.geocoder(appId, param);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        System.out.println("获取的经纬度为-->"+map);
    }

    //坐标转换
    @Test
    public void coordsTest(){

        GeoconvParam param = GeoconvParam.builder()
                .coords("114.21892734521,29.575429778924")       // 需要转换的源坐标
                .from(1)                                         // 源坐标类型,1：GPS设备获取的角度坐标，WGS84坐标;
                .to(5)                                           // 目标坐标类型,5：百度地图采用的经纬度坐标;
                .build();
        String response = client.geoconv(appId, param);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        System.out.println("获取的坐标转换-->"+map);
    }

    //公交路线规划
    @Test
    public void busTest(){

        DirectionTransitParam param = DirectionTransitParam.builder()
                .origin("40.056878,116.30815")             // 起点坐标
                .destination("31.222965,121.505821")       // 终点坐标
                .output("json")                            // 输出格式为json字符串或者xml字符串
                .pageIndex(1)                              // 返回第几页
                .pageSize(1)                               // 返回每页几条路线
                .build();
        String response = client.direction(appId, param);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        System.out.println("公交路线规划-->"+map);
    }

    //驾车路线规划
    @Test
    public void carTest(){

        DirectionDrivingParam param = DirectionDrivingParam.builder()
                .origin("40.056878,116.30815")              // 起点坐标
                .destination("31.222965,121.505821")        // 终点坐标
                .output("json")                             // 输出格式为json字符串或者xml字符串
                .build();
        String response = client.direction(appId, param);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        System.out.println("驾车路线规划-->"+map);
    }

    //骑行路线规划
    @Test
    public void RidingTest(){

        DirectionRidingParam param = DirectionRidingParam.builder()
                .origin("40.056878,116.30815")              // 起点坐标
                .destination("31.222965,121.505821")        // 终点坐标
                .output("json")                             // 输出格式为json字符串或者xml字符串
                .build();
        String response = client.direction(appId, param);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        System.out.println("骑行路线规划-->"+map);
    }

    //普通IP定位
    @Test
    public void IPTest(){

        IPLocationParam param = IPLocationParam.builder()
                .ip("171.83.87.169")
                // 需要定位的IP
                .build();
        String response = client.locate(appId, param);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        System.out.println("普通IP定位-->"+map);
    }

}
