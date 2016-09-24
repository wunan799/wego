/**
 * Created by wunan on 16/9/23.
 * 缺省拦截器
 */
package org.wnsoft.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
    }
}
