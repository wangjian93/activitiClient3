package com.ivo.activiticlient.system;

import com.ivo.activiticlient.common.util.HttpRequestDeviceUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjian
 * @date 2018/10/13
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler)
            return true;
        boolean ismobile = HttpRequestDeviceUtil.isMobileDevice(request);
        if (ismobile) return true;

        try {
            Class<?> clazz;
            if (handler instanceof HandlerMethod) {
                clazz = ((HandlerMethod) handler).getBeanType();
            } else
                clazz = handler.getClass();
            clazz.getField("withoutLoginCheck");
            return true;
        } catch (NoSuchFieldException e) {
            String url = request.getRequestURL().toString();
            if(url.indexOf("fhrCreate.do") > 0){
                return true;
            }else{
                if (WebUtils.getSessionAttribute(request, "user_ID") == null) {
                    if (request.getHeader("x-requested-with") == null)
                        response.sendRedirect("login.do?url="
                                + getCurrentUrl(request, true));
                    else
                        response.getWriter().write("");
                    return false;
                } else
                    return true;
            }
        }
    }

    public String getCurrentUrl(HttpServletRequest request) {
        return getCurrentUrl(request, false);
    }

    public String getCurrentUrl(HttpServletRequest request,
                                boolean enableBase64Encode) {
        if (request == null)
            return null;
        String url = request.getRequestURL().toString();
        String query = request.getQueryString();
        if (query != null)
            url = url + "?" + query;
        if (enableBase64Encode)
            return Base64.encodeBase64URLSafeString(url.getBytes());
        return url;
    }

}

