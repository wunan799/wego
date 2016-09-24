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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserManager {
    private static Logger logger = LoggerFactory.getLogger(UserManager.class);
    private static final String CALLBACK_URL =
            "http://wego.au-syd.mybluemix.net/api/user/callback.do";
    private final Map<String, User> userMap = new ConcurrentHashMap<>();
    private final Map<String, String> codeMap = new HashMap<>();
    private AtomicLong oauthSeq = new AtomicLong(1L);

    public User getOauthUser(String token) {
        String seq = Long.toString(oauthSeq.getAndIncrement());
        String code;

        synchronized (codeMap) {
            codeMap.put(seq, "0");
            doGetOauthCode(seq);

            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new WnException(e);
            }

            code = codeMap.get(seq);
            codeMap.remove(seq);

            if ((code == null) || code.equals("0")) {
                throw new WnException("没有获取到认证码");
            }
        }

        User user = doGetUser(doGetUserId(code, token), token);
        userMap.put(user.getUserid(), user);
        return user;
    }

    public void setOauthCode(String seq, String code) {
        synchronized (codeMap) {
            String old = codeMap.get(seq);

            if ("0".equals(old)) {
                codeMap.put(seq, code);
                notify();
            }
        }
    }

    public User getUserById(String userId, String token) {
        User user = userMap.get(userId);

        if (user != null) {
            return user;
        }

        return doGetUser(userId, token);
    }

    protected void doGetOauthCode(String state) {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=wx3acffe302f7bce92&redirect_uri=" + CALLBACK_URL +
                "&response_type=code&scope=snsapi_base&state=" + state +
                "#wechat_redirect";

        try {
            HttpUtils httpUtils = new HttpUtils();
            HttpRespons respons = httpUtils.sendGet(url);
            logger.info("获取授权CODE结果：{}", respons.code);
        } catch (IOException e) {
            throw new WnException(WnException.ERROR_IO, e);
        }
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
