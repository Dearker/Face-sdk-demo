package com.hanyi.aip;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @Description:
 * @Author: weiwenchang
 * @CreateDate: 2019/5/5 11:45
 */

public class Sample {

    //设置APPID/AK/SK
    public static final String APP_ID = "16136343";
    public static final String API_KEY = "qzZGGoi3DWaeH9iVc1LQXc1p";
    public static final String SECRET_KEY = "xFNRErwY2pUitPpSKD62IsAUrxjF3ei6";
    public static final AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

    public static void main(String[] args) {
        // 初始化一个AipFace
        //AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        //client.setConnectionTimeoutInMillis(2000);
        //client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "D:\\Learn\\SAAS项目\\3-11 刷脸登录\\资源\\照片\\001.png";
        JSONObject res = client.detect(path, "BASE64",new HashMap<String, String>());
        System.out.println(res.toString(2));

    }

    //人脸注册
    @Test
    public void addTest() throws IOException {

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        //options.put("user_info", "user's info");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        //String image = "D:\\Learn\\SAAS项目\\3-11 刷脸登录\\资源\\照片\\001.png";
        String image = "D:\\Learn\\SAAS项目\\3-11 刷脸登录\\资源\\照片\\002.png";
        byte[] bytes = Files.readAllBytes(Paths.get(image));
        String encode = Base64Util.encode(bytes);

        String imageUrl = "http://timingtest-imgservice.huiian.com/image/59048_1502450882245_ywqpbevznv3m1ksounpm";

        String imageType = "BASE64";
        //String imageType = "URL";
        String groupId = "group1";
        String userId = "001";

        // 人脸注册
        JSONObject res = client.addUser(encode, imageType, groupId, userId, options);
        //JSONObject res = client.addUser(imageUrl, imageType, groupId, userId, options);
        System.out.println(res.toString(2));

    }

    //更新人脸
    @Test
    public void updateTest() throws IOException {

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", "user's info");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        String image = "D:\\Learn\\SAAS项目\\3-11 刷脸登录\\资源\\照片\\003.png";
        byte[] bytes = Files.readAllBytes(Paths.get(image));
        String encode = Base64Util.encode(bytes);
        String imageType = "BASE64";
        String groupId = "group1";
        String userId = "001";

        // 人脸更新
        JSONObject res = client.updateUser(encode, imageType, groupId, userId, options);
        System.out.println(res.toString(2));

    }

    //人脸检测
    @Test
    public void detectTest() throws IOException {

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");

        String image = "D:\\Learn\\SAAS项目\\3-11 刷脸登录\\资源\\照片\\003.png";
        byte[] bytes = Files.readAllBytes(Paths.get(image));
        String encode = Base64Util.encode(bytes);


        String imageType = "BASE64";

        // 人脸检测
        JSONObject res = client.detect(encode, imageType, options);
        System.out.println(res.toString(2));

    }

    //人脸搜索
    @Test
    public void searhcTest() throws IOException {

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("user_id", "1001");
        options.put("max_user_num", "3");

        String image = "D:\\Learn\\SAAS项目\\3-11 刷脸登录\\资源\\照片\\003.png";
        byte[] bytes = Files.readAllBytes(Paths.get(image));
        String encode = Base64Util.encode(bytes);
        String imageType = "BASE64";
        String groupIdList = "hanyi";

        // 人脸搜索
        JSONObject res = client.search(encode, imageType, groupIdList, options);
        System.out.println(res.toString(2));

    }


}
