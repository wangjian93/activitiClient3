package com.ivo.activiticlient.system.controller;

import com.ivo.activiticlient.common.util.BizUtil;
import com.ivo.activiticlient.restful.RestfulImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangjian
 * @date 2018/10/13
 */
@Controller
public class FilterController {


    private RestfulImpl restfulImpl = new RestfulImpl();

    public int needLoginCheck;

    @RequestMapping("/business.do")
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String sid = request.getParameter("sid");
        String orderNumber = request.getParameter("order");
        String orders = request.getParameter("orders");
        if(orders != null && !orders.equals("")){
            response.sendRedirect("batchShow.do?orders="+orders);
        }else{
            if ((sid == null && orderNumber == null)
                    || (orderNumber != null && !BizUtil.isOrderValidate(orderNumber))) {
                BizUtil.alert(response, "你请求的URL不合法！");
                return;
            }
            if (orderNumber == null) {
                orderNumber = restfulImpl.getTrackingNumber(sid);
            }
            response.sendRedirect("show.do?orderNumber="+ orderNumber);
        }
    }

//	public static String getCurrentUrl(HttpServletRequest request) {
//		return getCurrentUrl(request, false);
//	}
//
//	public static String getCurrentUrl(HttpServletRequest request,
//			boolean enableBase64Encode) {
//		if (request == null)
//			return null;
//		String url = request.getRequestURL().toString();
//		String query = request.getQueryString();
//		if (query != null)
//			url = url + "?" + query;
//		if (enableBase64Encode)
//			return Base64.encodeBase64URLSafeString(url.getBytes());
//		return url;
//	}
}
