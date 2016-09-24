/**
 * Created by wunan on 16-9-21.
 */
package org.wnsoft.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wnsoft.entity.User;
import org.wnsoft.utils.HttpRespons;
import org.wnsoft.utils.HttpUtils;
import org.wnsoft.utils.WnException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Logger logger = LoggerFactory.getLogger(UserManager.class);

    public User getUserByCode(String code, String token) {
        String userId = doGetUserId(code, token);
        return doGetUser(userId, token);
    }

    public User getUserById(String userId, String token) {
        return doGetUser(userId, token);
    }

    protected String doGetUserId(String code, String token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token);
        params.put("code", code);

        try {
            HttpUtils httpUtils = new HttpUtils();
            HttpRespons respons = httpUtils.sendGet(url, params);
            logger.info("获取用户结果：{}", respons.content);
            JSONObject jsonObject = JSON.parseObject(respons.content);
            String userId = jsonObject.getString("UserId");

            if (userId == null) {
                throw new WnException(jsonObject.getIntValue("errcode")
                        , jsonObject.getString("errmsg"));
            }

            return userId;
        } catch (IOException e) {
            throw new WnException(WnException.ERROR_IO, e);
        }
    }

    protected User doGetUser(String userId, String token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token);
        params.put("userid", userId);

        try {
            HttpUtils httpUtils = new HttpUtils();
            HttpRespons respons = httpUtils.sendGet(url, params);
            logger.info("获取用户结果：{}", respons.content);
            JSONObject jsonObject = JSON.parseObject(respons.content);
            int errCode = jsonObject.getIntValue("errcode");

            if (errCode != 0) {
                throw new WnException(errCode, jsonObject.getString("errmsg"));
            }

            return jsonObject.toJavaObject(User.class);
        } catch (IOException e) {
            throw new WnException(WnException.ERROR_IO, e);
        }
    }
}
