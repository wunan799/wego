/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wnsoft.entity.MpNewsMsg;
import org.wnsoft.utils.HttpRespons;
import org.wnsoft.utils.HttpUtils;
import org.wnsoft.utils.WnException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageManager {
    private static Logger logger = LoggerFactory.getLogger(MessageManager.class);

    public void publish(MpNewsMsg mpNewsMsg, String token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token);

        try {
            HttpUtils httpUtils = new HttpUtils();
            HttpRespons respons = httpUtils.postJson(url, params, JSON.toJSONString(mpNewsMsg));
            logger.info("获取用户结果：{}", respons.content);
            JSONObject jsonObject = JSON.parseObject(respons.content);
            int errCode = jsonObject.getIntValue("errcode");

            if (errCode != 0) {
                throw new WnException(errCode, jsonObject.getString("errmsg"));
            }

        } catch (IOException e) {
            throw new WnException(WnException.ERROR_IO, e);
        }
    }
}
