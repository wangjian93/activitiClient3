package com.ivo.activiticlient.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author wangjian
 * @date 2018/10/13
 */
public class BizUtil {

    public static String getSource(String order) {
        return order.substring(0, order.length() - 9);
    }

    public static boolean isOrderValidate(String order) {
        if (order == null || order.length() <= 9)
            return false;
        try {
            int i = Integer.parseInt(order.substring(order.length() - 9));
            Calendar c = Calendar.getInstance();
            int mouth = i / 100000 % 100;
            if (mouth == 0 || mouth > 12)
                return false;
            if ((i / 10000000 > c.get(Calendar.YEAR) % 100)
                    || (i / 10000000 == c.get(Calendar.YEAR) % 100 && mouth > c
                    .get(Calendar.MONTH) + 1))
                return false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static void alert(HttpServletResponse response, String message)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response
                .getWriter()
                .print(
                        "<script type=\"text/javascript\">alert(\""
                                + message
                                + "\");window.open('','_self');window.close();</script>");
    }

}
