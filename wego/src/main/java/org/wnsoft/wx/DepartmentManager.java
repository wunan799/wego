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
    private Map<Integer, List<Department>> departMap = new HashMap<>();

    public List<Department> getDepartmentList(String token, int id) {
        List<Department> departmentList = departMap.get(id);

        if ((departmentList != null) && !departmentList.isEmpty()) {
            return departmentList;
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

            JSONArray array = jsonObject.getJSONArray("department");
            departmentList = new ArrayList<>(array.size());

            for (int i = 0; i < array.size(); ++i) {
                Department department = array.getJSONObject(i).toJavaObject(Department.class);
                departmentList.add(department);
            }

            departMap.put(id, departmentList);
            return departmentList;
        } catch (IOException e) {
            throw new WnException(WnException.ERROR_IO, e);
        }
    }
}
