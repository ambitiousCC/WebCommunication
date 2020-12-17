package web.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @description: 获取请求地址
 * @author: Wzq
 * @create: 2020-05-20 09:53
 */
public class AddressUtils {

    /**
     * 根据ip获取地址
     * @param ip
     * @return
     */
    public static String getAddress(String ip) {
        String url = "http://ip.ws.126.net/ipquery?ip=" + ip;
        String str = HttpUtil.get(url);
        if(!StrUtil.hasBlank(str)){
            String substring = str.substring(str.indexOf("{"), str.indexOf("}")+1);
            JSONObject jsonObject = JSONUtil.parseObj(substring);
            String province = jsonObject.getStr("province");
            String city = jsonObject.getStr("city");
            return province + " " + city;
        }
        return null;
    }



    public static void main(String[] args) {
        String ip = "192.168.5.1";
        String result = getAddress(ip);
        System.out.println(result);
    }
}