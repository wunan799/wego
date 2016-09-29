/**
 * Created by wunan on 16-9-27.
 */
package org.wnsoft.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wnsoft.entity.Department;
import org.wnsoft.entity.User;
import org.wnsoft.utils.HttpRespons;
import org.wnsoft.utils.HttpUtils;
import org.wnsoft.utils.WnException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentManager {
    private static Logger logger = LoggerFactory.getLogger(DepartmentManager.class);
    private Map<Integer, Department> departMap = new HashMap<>();

    public Department getDepartment(String token, int id) {
        Department department = departMap.get(id);

        if (department != null) {
            return department;
        }

        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token);
        params.put("id", Integer.toString(id));

        try {
            HttpUtils httpUtils = new HttpUtils();
            HttpRespons respons = httpUtils.sendGet(url, params);
            logger.info("获取部门结果：{}", respons.content);
            JSONObject jsonObject = JSON.parseObject(respons.content);
            int error = jsonObject.getIntValue("errcode");

            if (error != 0) {
                throw new WnException(error, jsonObject.getString("errmsg"));
            }

            department = jsonObject.toJavaObject(Department.class);
            ///TODO:暂时不做缓存，因为尚未完成更新消息获取机制
//            departMap.put(id, department);
//            List<Department> subList = department.getDepartment();
//
//            for (Department sub : subList) {
//                departMap.put(sub.getId(), sub);
//            }

            return department;
        } catch (IOException e) {
            throw new WnException(WnException.ERROR_IO, e);
        }
    }

    public List<User> getDepartmentUsers(String token, int id) {
        Department department = getDepartment(token, id);
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/list";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token);
        params.put("department_id", Integer.toString(id));
        params.put("fetch_child", Integer.toString(0));
        params.put("status", Integer.toString(1));

        try {
            HttpUtils httpUtils = new HttpUtils();
            HttpRespons respons = httpUtils.sendGet(url, params);
            logger.info("获取部门用户结果：{}", respons.content);
            JSONObject jsonObject = JSON.parseObject(respons.content);
            int error = jsonObject.getIntValue("errcode");

            if (error != 0) {
                throw new WnException(error, jsonObject.getString("errmsg"));
            }

            Department tmp = jsonObject.toJavaObject(Department.class);
            department.setUserlist(tmp.getUserlist());
            return department.getUserlist();
        } catch (IOException e) {
            throw new WnException(WnException.ERROR_IO, e);
        }
    }
}
