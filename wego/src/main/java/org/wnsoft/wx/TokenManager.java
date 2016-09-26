/**
 * Created by wunan on 16-9-21.
 */
package org.wnsoft.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wnsoft.utils.HttpRespons;
import org.wnsoft.utils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {
    private static final String CORP_ID = "wx3acffe302f7bce92";
    private static final String CORP_SECRET
            = "AO1_64BjZE4-PTJALRsVUEVRtn79Vg_KTXUM0m8lf1DjApn3avToDgWgxjCAZPf4";
    private static Logger logger = LoggerFactory.getLogger(TokenManager.class);
    private String token;
    private long allocTime;
    private int expireTime;

    public String getToken() {
        if (token == null) {
            return doGetToken();
        } else if ((System.currentTimeMillis() - allocTime) / 1000 > expireTime) {
            return doGetToken();
        } else {
            return token;
        }
    }

    private String doGetToken() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        HttpUtils httpUtils = new HttpUtils();
        Map<String, String> params = new HashMap<>(2);
        params.put("corpid", CORP_ID);
        params.put("corpsecret", CORP_SECRET);
        allocTime = System.currentTimeMillis();

        try {
            HttpRespons respons = httpUtils.sendGet(url, params);
            logger.info("获取TOKEN结果：{}", respons.content);
            JSONObject jsonObject = JSON.parseObject(respons.content);

            if (jsonObject != null) {
                expireTime = jsonObject.getInteger("expires_in");
                token = jsonObject.getString("access_token");
                return token;
            } else {
                throw new RuntimeException("Json parse error");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
