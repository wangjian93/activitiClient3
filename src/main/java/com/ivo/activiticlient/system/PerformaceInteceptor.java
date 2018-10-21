package com.ivo.activiticlient.system;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjian
 * @date 2018/10/13
 */
public class PerformaceInteceptor extends HandlerInterceptorAdapter {

    private ThreadLocal<Long> local = new ThreadLocal<Long>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long begin = System.currentTimeMillis();
        local.set(begin);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long end = System.currentTimeMillis();
        long begin = local.get();
        long time = end - begin;
        Log logger = LogFactory.getLog("Performance");
        String path = request.getRequestURI();
        String query = request.getQueryString();
        query = query == null ? "" : "?" + query;
        String message = path + query + " | " + time + "ms.";
        if (time >= 5000) {
            logger.error(message);
        } else if (time >= 1000) {
            logger.info(message);
        } else
            logger.debug(message);
        local.remove();
    }

}
