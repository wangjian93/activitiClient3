package com.ivo.activiticlient.common.util;

import java.text.DecimalFormat;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class FormatUtil {

    private FormatUtil() {
    }

    /**
     *
     * @param pattern
     *            ###.00 (11.1 --> 11.10) (111.111 --> 111.11) #.00% (0.111 -->
     *            11.1%)
     * @param value
     * @return
     */
    public static String formatNumber( String pattern, Double value ) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static String formatNumber( String pattern, Float value ) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static String formatNumber( String pattern, Integer value ) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static String formatDotThound( Double value ) {
        return FormatUtil.formatNumber("###,##0.00", value);
    }

    public static String formatDotThound( Float value ) {
        return FormatUtil.formatNumber("###,##0.00", value);
    }

    static public String numerize( String str ) {
        String rtn = "";
        boolean dotted = false;
        str = str.trim();
        if ( str == "" )
            return 0 + "";
        for ( int i = 0; i < str.length(); i++ ) {
            char c = str.toCharArray()[i];
            if ( c >= '0' && c <= '9' ) {
                rtn += c;
            } else if ( (c == '.') ) {
                if ( dotted )
                    break;
                rtn += c;
                dotted = true;
            }
        }// for i
        if ( rtn.toCharArray()[0] == '.' )
            rtn = "0" + rtn;
        if ( rtn == "" )
            rtn = 0 + "";
        return rtn;
    }

    static public boolean isNumerize( String str ) {
        boolean dotted = false;
        str = str.replace(",", "");
        if ( str == "" )
            return false;
        for ( int i = 0; i < str.length(); i++ ) {
            char c = str.toCharArray()[i];
            if ( c >= '0' && c <= '9' ) {

            } else if ( (c == '.') ) {
                if ( dotted )
                    return false;
                dotted = true;
            } else {
                return false;
            }
        } // for i
        return true;
    }

    public static String dotThousand( String str ) {
        String dec = "";
        str = FormatUtil.numerize(str);

        int i = str.indexOf(".");
        if ( i >= 0 ) {
            dec = str.substring(i);
            str = str.substring(0, i);
        }
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(Double.parseDouble(str)) + dec;
    }

    public static String dotThousand( String str, int dotCnt ) {
        String dec = "";
        str = FormatUtil.numerize(str);

        int i = str.indexOf(".");
        if ( i >= 0 ) {
            dec = str.substring(i);
            str = str.substring(0, i);
        }
        String pattern = ".";
        for(int j=0;j<dotCnt;j++){
            pattern +="0";
        }
        DecimalFormat df = new DecimalFormat("###,###"+pattern);
        return df.format(Double.parseDouble(str)) + dec;
    }
    static public String space(int value, char spacer, int slotcnt)
    {
        return space(value+"", spacer, slotcnt);
    }
    static public String space(String value, char spacer, int slotcnt)
    {
        String str = "";

        if( value.length() >= slotcnt ) return value;
        slotcnt = slotcnt - value.length();
        str = value;
        for( int i=0; i < slotcnt; i++ ) {
            str = spacer + str;
        } // for i

        return str;
    }
}
