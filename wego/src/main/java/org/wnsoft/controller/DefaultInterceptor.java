/**
 * Created by wunan on 16/9/23.
 * 缺省拦截器
 */
package org.wnsoft.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.wnsoft.utils.WnException;
import org.wnsoft.utils.WnResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler
            , ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request
            , HttpServletResponse response, Object handler
            , Exception ex) throws Exception {
        if (ex == null) {
            return;
        }

        response.setContentType("application/json; charset=UTF-8");
        WnResult wnResult;

        if (ex instanceof WnException) {
            WnException wnException = (WnException) ex;
            wnResult = new WnResult(wnException.getErrorCode()
                    , wnException.getMessage(), null);
        } else if (ex instanceof NullPointerException) {
            wnResult = new WnResult(WnException.ERROR_NULLPOINT, "空指针错误");
        } else{
            wnResult = new WnResult(WnException.ERROR_GENERIC, ex.getMessage());
        }

        response.getWriter().print(JSON.toJSONString(wnResult));
    }
}
