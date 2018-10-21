package com.ivo.activiticlient.common.util;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class TagUtils {

    public static Integer getLength(Object object) {
        if (object == null)
            return 0;
        if (object instanceof String)
            return ((String) object).length();
        else if (object instanceof Object[])
            return ((Object[]) object).length;
        else if (object instanceof Collection<?>)
            return ((Collection<?>) object).size();
        return 0;
    }

    public static String formatCurrentDate(String pattern) {
        return formatDate(new Date(), pattern);
    }

    public static String formatDate(Date date, String pattern) {
        return DateUtil.format(date, pattern);
    }

    public static String formatNumberStr(String str){
        double douStr = Double.parseDouble(str);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(2);
        String result = nf.format(douStr);
        if(result.equals("0.0")) return "";
        else return result;
    }


}
