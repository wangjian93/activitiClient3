package com.ivo.activiticlient.biz;

import com.ivo.activiticlient.common.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/13
 */
public class Extractor {

    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Extractor(HttpServletRequest request) {
        if (request == null)
            throw new IllegalArgumentException();
        this.request = request;
    }

    public  Object getObject(String param) {
        try {
            return request.getAttribute(param);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getInt(HttpServletRequest request, String param) {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getNotNullInt(HttpServletRequest request, String param) {
        return getNotNullInt(request, param, 0);
    }

    public static Integer getNotNullInt(HttpServletRequest request,
                                        String param, int defaultValue) {
        Integer i = getInt(request, param);
        return i == null ? defaultValue : i;
    }

    public Integer getInt(String param) {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getNotNullInt(String param) {
        return getNotNullInt(param, 0);
    }

    public Integer getNotNullInt(String param, int defaultValue) {
        Integer i = getInt(param);
        return i == null ? defaultValue : i;
    }

    public static Long getLong(HttpServletRequest request, String param) {
        try {
            return Long.parseLong(request.getParameter(param));
        } catch (Exception e) {
            return null;
        }
    }

    public static Long getNotNullLong(HttpServletRequest request, String param) {
        return getNotNullLong(request, param, 0);
    }

    public static Long getNotNullLong(HttpServletRequest request, String param,
                                      long defaultValue) {
        Long l = getLong(request, param);
        return l == null ? defaultValue : l;
    }

    public Long getLong(String param) {
        try {
            return Long.parseLong(request.getParameter(param));
        } catch (Exception e) {
            return null;
        }
    }

    public Long getNotNullLong(String param) {
        return getNotNullLong(param, 0);
    }

    public Long getNotNullLong(String param, long defaultValue) {
        Long l = getLong(param);
        return l == null ? defaultValue : l;
    }

    public static Short getShort(HttpServletRequest request, String param) {
        try {
            return Short.parseShort(request.getParameter(param));
        } catch (Exception e) {
            return null;
        }
    }

    public static Short getNotNullShort(HttpServletRequest request, String param) {
        return getNotNullShort(request, param, (short) 0);
    }

    public static Short getNotNullShort(HttpServletRequest request,
                                        String param, short defaultValue) {
        Short s = getShort(request, param);
        return s == null ? defaultValue : s;
    }

    public Short getShort(String param) {
        try {
            return Short.parseShort(request.getParameter(param));
        } catch (Exception e) {
            return null;
        }
    }

    public Short getNotNullShort(String param) {
        return getNotNullShort(param, (short) 0);
    }

    public Short getNotNullShort(String param, short defaultValue) {
        Short s = getShort(param);
        return s == null ? defaultValue : s;
    }

    public static Float getFloat(HttpServletRequest request, String param) {
        try {
            return Float.parseFloat(request.getParameter(param));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Float getNotNullFloat(HttpServletRequest request, String param) {
        return getNotNullFloat(request, param, 0f);
    }

    public static Float getNotNullFloat(HttpServletRequest request,
                                        String param, float defaultValue) {
        Float f = getFloat(request, param);
        return f == null ? defaultValue : f;
    }

    public Float getFloat(String param) {
        try {
            return Float.parseFloat(request.getParameter(param));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Float getNotNullFloat(String param) {
        return getNotNullFloat(param, 0f);
    }

    public Float getNotNullFloat(String param, float defaultValue) {
        Float f = getFloat(param);
        return f == null ? defaultValue : f;
    }

    public static Double getDouble(HttpServletRequest request, String param) {
        try {
            return Double.parseDouble(request.getParameter(param));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double getNotNullDouble(HttpServletRequest request,
                                          String param) {
        return getNotNullDouble(request, param, 0d);
    }

    public static Double getNotNullDouble(HttpServletRequest request,
                                          String param, double defaultValue) {
        Double d = getDouble(request, param);
        return d == null ? defaultValue : d;
    }

    public Double getDouble(String param) {
        try {
            return Double.parseDouble(request.getParameter(param));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Double getNotNullDouble(String param) {
        return getNotNullDouble(param, 0d);
    }

    public Double getNotNullDouble(String param, double defaultValue) {
        Double d = getDouble(param);
        return d == null ? defaultValue : d;
    }

    public static String getString(HttpServletRequest request, String param) {
        return request.getParameter(param);
    }

    public static String getNotNullString(HttpServletRequest request,
                                          String param) {
        return getNotNullString(request, param, "");
    }

    public static String getNotNullString(HttpServletRequest request,
                                          String param, String defaultValue) {
        String s = getString(request, param);
        return s == null ? defaultValue : s;
    }

    public String getString(String param) {
        return request.getParameter(param);
    }

    public String getNotNullString(String param) {
        return getNotNullString(param, "");
    }

    public String getNotNullString(String param, String defaultValue) {
        String s = getString(param);
        return s == null ? defaultValue : s;
    }

    public static Date getDate(HttpServletRequest request, String param) {
        return getDate(request, param, DateUtil.DatePattern);
    }

    public static Date getDateTime(HttpServletRequest request, String param) {
        return getDate(request, param, DateUtil.DateTimePattern);
    }

    public static Date getDate(HttpServletRequest request, String param,
                               String patten) {
        return DateUtil.parseDate(request.getParameter(param));
    }

    public static Date getNotNullDate(HttpServletRequest request, String param) {
        return getNotNullDate(request, param, new Date());
    }

    public static Date getNotNullDateTime(HttpServletRequest request,
                                          String param) {
        return getNotNullDateTime(request, param, new Date());
    }

    public static Date getNotNullDate(HttpServletRequest request, String param,
                                      String patten) {
        return getNotNullDate(request, param, patten, new Date());
    }

    public static Date getNotNullDate(HttpServletRequest request, String param,
                                      Date defaultValue) {
        Date date = getDate(request, param);
        return date == null ? (defaultValue == null ? new Date() : defaultValue)
                : date;
    }

    public static Date getNotNullDateTime(HttpServletRequest request,
                                          String param, Date defaultValue) {
        Date date = getDateTime(request, param);
        return date == null ? (defaultValue == null ? new Date() : defaultValue)
                : date;
    }

    public static Date getNotNullDate(HttpServletRequest request, String param,
                                      String patten, Date defaultValue) {
        Date date = getDate(request, param, patten);
        return date == null ? (defaultValue == null ? new Date() : defaultValue)
                : date;
    }

    public Date getDate(String param) {
        return getDate(param, DateUtil.DatePattern);
    }

    public Date getDateTime(String param) {
        return getDate(param, DateUtil.DateTimePattern);
    }

    public Date getDate(String param, String patten) {
        return DateUtil.parseDate(request.getParameter(param));
    }

    public Date getNotNullDate(String param) {
        return getNotNullDate(param, new Date());
    }

    public Date getNotNullDateTime(String param) {
        return getNotNullDateTime(param, new Date());
    }

    public Date getNotNullDate(String param, String patten) {
        return getNotNullDate(param, patten, new Date());
    }

    public Date getNotNullDate(String param, Date defaultValue) {
        Date date = getDate(param);
        return date == null ? (defaultValue == null ? new Date() : defaultValue)
                : date;
    }

    public Date getNotNullDateTime(String param, Date defaultValue) {
        Date date = getDateTime(param);
        return date == null ? (defaultValue == null ? new Date() : defaultValue)
                : date;
    }

    public Date getNotNullDate(String param, String patten, Date defaultValue) {
        Date date = getDate(param, patten);
        return date == null ? (defaultValue == null ? new Date() : defaultValue)
                : date;
    }

}
