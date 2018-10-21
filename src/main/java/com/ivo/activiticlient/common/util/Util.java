package com.ivo.activiticlient.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjian
 * @date 2018/10/11
 */
public class Util
{
    static public String isNull(String str, Object obj)
    {
        return ( str != null ) ? str : obj.toString();
    }
    static public Calendar toCalendar(String strDateTime)
    {
        if( strDateTime == null || strDateTime.equals("")  ) return null;

        Calendar objCalendar = Calendar.getInstance();

        objCalendar.set( Calendar.YEAR, Integer.parseInt(strDateTime.substring(0, 4)) );
        objCalendar.set( Calendar.MONTH, Integer.parseInt(strDateTime.substring(5, 7))-1 );
        objCalendar.set( Calendar.DAY_OF_MONTH, Integer.parseInt(strDateTime.substring(8, 10)) );
        if( strDateTime.length() <= 10 ) {
            objCalendar.set( Calendar.HOUR_OF_DAY, 0 );
            objCalendar.set( Calendar.MINUTE, 0 );
        } else {
            int nHourBeg = strDateTime.indexOf( " " );
            int nHourEnd = strDateTime.indexOf( ":" );
            String strTimeTemp = strDateTime.substring( nHourBeg+1, nHourEnd );
            if( strTimeTemp.length() < 2 ) {
                objCalendar.set( Calendar.HOUR_OF_DAY, Integer.parseInt(strDateTime.substring(11, 12)) );
                objCalendar.set( Calendar.MINUTE, Integer.parseInt(strDateTime.substring(13, 15)) );
                if(strDateTime.length()==18){
                    objCalendar.set( Calendar.SECOND, Integer.parseInt(strDateTime.substring(16, 18)) );
                }
            }else{
                objCalendar.set( Calendar.HOUR_OF_DAY, Integer.parseInt(strDateTime.substring(11, 13)) );
                objCalendar.set( Calendar.MINUTE, Integer.parseInt(strDateTime.substring(14, 16)) );
                if(strDateTime.length()==19){
                    objCalendar.set( Calendar.SECOND, Integer.parseInt(strDateTime.substring(17, 19)) );
                }
            }
        }

        return objCalendar;
    }
    static public String getNow()
    {
        Calendar now = Calendar.getInstance();
        return toShortDateTimeStr(now);
    }
    static public String getNowMill()
    {
        Calendar now = Calendar.getInstance();
        return toDateTimeMillStr(now);
    }
    static public String toShortDateStr(String strDateTime)
    {
        return toShortDateStr( toCalendar(strDateTime) );
    }
    static public String toShortDateStr(Calendar objDateTime)
    {
        if( objDateTime == null ) return "";

        String str = "";

        str += objDateTime.get( Calendar.YEAR );
        str += "-";
        str += space((objDateTime.get(Calendar.MONTH)+1)+"", '0', 2);
        str += "-";
        str += space(objDateTime.get(Calendar.DAY_OF_MONTH)+"", '0', 2);
        return str;
    }

    static public String toShortMonthStr(String strMonthTime)
    {
        return toShortMonthStr( toCalendar(strMonthTime) );
    }
    static public String toShortMonthStr(Calendar objDateTime)
    {
        if( objDateTime == null ) return "";

        String str = "";

        str += objDateTime.get( Calendar.YEAR );
        str += "-";
        str += toMonthStr(objDateTime);

        return str;
    }


    static public String toMonthStr(Calendar objDateTime)
    {
        if( objDateTime == null ) return "";

        String str = "";
        int i = objDateTime.get( Calendar.MONTH );
        switch ( i ) {
            case 0:
                str = "JAN"; break;
            case 1:
                str = "FEB"; break;
            case 2:
                str = "MAR"; break;
            case 3:
                str = "APR"; break;
            case 4:
                str = "MAY"; break;
            case 5:
                str = "JUN"; break;
            case 6:
                str = "JUL"; break;
            case 7:
                str = "AUG"; break;
            case 8:
                str = "SEP"; break;
            case 9:
                str = "OCT"; break;
            case 10:
                str = "NOV"; break;
            case 11:
                str = "DEC"; break;
            default:
                str = "---";
        } // switch ( i

        return str;
    }
    static public String toWeekStr(Calendar objDateTime)
    {
        if( objDateTime == null ) return "";

        String str = "";
        int i = objDateTime.get( Calendar.DAY_OF_WEEK );
        switch ( i ) {
            case 1:
                str = "Sun"; break;
            case 2:
                str = "Mon"; break;
            case 3:
                str = "Tue"; break;
            case 4:
                str = "Wed"; break;
            case 5:
                str = "Thu"; break;
            case 6:
                str = "Fri"; break;
            case 7:
                str = "Sat"; break;
            default:
                str = "Undefined";
        } // switch ( i

        return str;
    }
    static public String toShortDateTimeStr(String strDateTime)
    {
        return toShortDateTimeStr( toCalendar(strDateTime) );
    }
    static public String toShortDateTimeStr(Calendar objDateTime)
    {
        if( objDateTime == null ) return "";

        String str = "";

        str += objDateTime.get( Calendar.YEAR );
        str += "-";
        str += space((objDateTime.get(Calendar.MONTH)+1)+"", '0', 2);
        str += "-";
        str += space(objDateTime.get(Calendar.DAY_OF_MONTH)+"", '0', 2);
        str += " ";
        str += space(objDateTime.get(Calendar.HOUR_OF_DAY)+"", '0', 2);
        str += ":";
        str += space(objDateTime.get(Calendar.MINUTE)+"", '0', 2);

        return str;
    }

    static public String toDateTimeStr(Calendar objDateTime)
    {
        if( objDateTime == null ) return "";

        String str = "";

        str += objDateTime.get( Calendar.YEAR );
        str += "-";
        str += space((objDateTime.get(Calendar.MONTH)+1)+"", '0', 2);
        str += "-";
        str += space(objDateTime.get(Calendar.DAY_OF_MONTH)+"", '0', 2);
        str += " ";
        str += space(objDateTime.get(Calendar.HOUR_OF_DAY)+"", '0', 2);
        str += ":";
        str += space(objDateTime.get(Calendar.MINUTE)+"", '0', 2);
        str += ":";
        str += space(objDateTime.get(Calendar.SECOND)+"", '0', 2);

        return str;
    }
    static public String toDateTimeMillStr(Calendar objDateTime)
    {
        if( objDateTime == null ) return "";

        String str = "";

        str += objDateTime.get( Calendar.YEAR );
        str += "-";
        str += space((objDateTime.get(Calendar.MONTH)+1)+"", '0', 2);
        str += "-";
        str += space(objDateTime.get(Calendar.DAY_OF_MONTH)+"", '0', 2);
        str += " ";
        str += space(objDateTime.get(Calendar.HOUR_OF_DAY)+"", '0', 2);
        str += ":";
        str += space(objDateTime.get(Calendar.MINUTE)+"", '0', 2);
        str += ":";
        str += space(objDateTime.get(Calendar.SECOND)+"", '0', 2);
        str += ":";
        str += space(objDateTime.get(Calendar.MILLISECOND)+"", '0', 2);

        return str;
    }
    static public String toShortTimeStr(String strDateTime)
    {
        return toShortTimeStr( toCalendar(strDateTime) );
    }
    static public String toShortTimeStr(Calendar objDateTime)
    {
        if( objDateTime == null ) return "";

        String str = "";

        str += space(objDateTime.get(Calendar.HOUR_OF_DAY)+"", '0', 2);
        str += ":";
        str += space(objDateTime.get(Calendar.MINUTE)+"", '0', 2);

        return str;
    }

    static public String saveStr(String str)
    {
        if( str == null ) return "";
        str = str.replace( "\r", "" );
        str = str.replace( "\n", "@13;" );
        str = str.replace( "\"", "@34;" );
        str = str.replace( "'", "@39;" );
        str = str.replace( "\\", "\\\\" );
        return str;
    }
    static public String restoreStr(String str)
    {
        if( str == null ) return "";
        str = str.replace( "@13;", "\n" );
        str = str.replace( "@34;", "\"" );
        str = str.replace( "@39;", "'" );
        return str;
    }
    static public String saveXML(String str)
    {
        if( str == null ) return "";
        str = str.replace( "\r", "" );
        str = str.replace( "\n", "@13;" );
        str = str.replace( "&", "@38;" );
        str = str.replace( "/", "@47;" );
        str = str.replace( "<", "@60;" );
        str = str.replace( ">", "@62;" );

        return str;
    }
    static public String restoreXML(String str)
    {
        if( str == null ) return "";
        str = str.replace( "@10;", "" );
        str = str.replace( "@13;", "\n" );
        str = str.replace( "@38;", "&" );
        str = str.replace( "@47;", "/" );
        str = str.replace( "@60;", "<" );
        str = str.replace( "@62;", ">" );

        return str;
    }
    static public String saveURL(String str)
    {
        if( str == null ) return "";
        str = str.replace( " ", "@32");
        str = str.replace( "#", "@35");
        str = str.replace( "$", "@36");
        str = str.replace( "%", "@37");
        str = str.replace( "=", "@61");
        str = str.replace( "+", "@43");
        str = str.replace( "-", "@45");
        str = str.replace( "*", "@42");
        str = str.replace( "/", "@47");
        str = str.replace( "\\", "@92");
        str = str.replace( "&", "@38");

        return str;
    }
    static public String restoreURL(String str)
    {
        if( str == null ) return "";
        str = str.replace( "@32", " " );
        str = str.replace( "@35", "#" );
        str = str.replace( "@36", "$" );
        str = str.replace( "@37", "%" );
        str = str.replace( "@61", "=" );
        str = str.replace( "@43", "+" );
        str = str.replace( "@45", "-" );
        str = str.replace( "@42", "*" );
        str = str.replace( "@47", "/" );
        str = str.replace( "@92", "\\");
        str = str.replace( "@38", "&" );

        return str;
    }
    static public String saveSQL(String str)
    {
        if( str == null ) return "";
        str = str.replace( "'", "''" );

        return str;
    }
    static public String saveHTML(String str)
    {
        if( str == null ) return "";
        str = str.replace( "\"", "&#34;");
        str = str.replace( "\r", "" );
        str = str.replace( "\n", "<br/>" );
        str = str.replace( "\\", "\\\\" );
        str = str.replace( "'", "&#39;" );
//		str = str.replace( "&", "@38;" );
//		str = str.replace( "/", "@47;" );

        return str;
    }
    static public String saveHTML1(String str)
    {
        if( str == null ) return "";
        str = str.replace( "\"", "&#34;");
        str = str.replace( "\r", "" );
//		str = str.replace( "\n", "<br/>" );
        str = str.replace( "\\", "\\\\" );
        str = str.replace( "'", "&#39;" );
//		str = str.replace( "&", "@38;" );
//		str = str.replace( "/", "@47;" );

        return str;
    }
    static public String tag(String strTagName, String strValue)
    {
        return "<"+strTagName+">"+Util.saveXML(strValue)+"</"+strTagName+">";
    }
    static public String tag(String strTagName)
    {
        return Util.tag( strTagName, "" );
    }
    static public String space(int value, char spacer, int slotcnt)
    {
        return space(value+"", spacer, slotcnt);
    }

    static public String space(String value, char spacer, int slotcnt)
    {
        String str = "";
        if (value.length() >= slotcnt) return value;
        slotcnt = slotcnt - value.length();
        str = value;
        for (int i = 0; i < slotcnt; i++ )
        {
            str = spacer + str;
        }
        return str;
    }

    static public String rspace(int value, char spacer, int slotcnt)
    {
        return rspace(value+"", spacer, slotcnt);
    }
    static public String rspace(String value, char spacer, int slotcnt)
    {
        String str = "";

        if( value.length() >= slotcnt ) return value;
        slotcnt = slotcnt - value.length();
        str = value;
        for( int i=0; i < slotcnt; i++ ) {
            str = str + spacer;
        } // for i

        return str;
    }
    static public String round(double fNum, int nScale)
    {
        double fStep = Math.pow( 10, nScale );

        fNum *= fStep;
        fNum = Math.round( fNum );
        fNum /= fStep;

        return ( fNum == 0 ) ? "" : fNum+"";
    }
    static public ArrayList<String> parseArrayList(String str, String cut)
    {
        ArrayList<String> objArrayList = new ArrayList<String>();

        str += cut;
        int i = str.indexOf( cut );
        while( i > -1 ) {
            String strItem = str.substring( 0, i ).trim();
            if( strItem.length() > 0 ) {
                objArrayList.add( strItem );
            } // if strItem

            str = str.substring( i+cut.length() );
            i = str.indexOf( cut );
        } // while i

        return objArrayList;
    }

    static public HashMap<String, String> parseHashMap(String str, String gap, String cut)
    {
        HashMap<String, String> objHashMap = new HashMap<String, String>();
        String strKey = "";
        String strValue = "";

        str += cut;
        int i = str.indexOf( cut );
        while( i > -1 ) {
            String strItem = str.substring( 0, i ).trim();
            int j = strItem.indexOf( gap );
            if( j > -1 ) {
                strKey = strItem.substring( 0, j ).trim();
                strValue = strItem.substring( j+gap.length() ).trim();
                objHashMap.put( strKey, strValue );
            } // if strItem

            str = str.substring( i+cut.length() );
            i = str.indexOf( cut );
        } // while i

        return objHashMap;
    }
    static public String identify(int nSize)
    {
        String objLetterList = "0123456789ABCDEF";
        String strIdentifier = "";
        Random objRandom = new Random( System.currentTimeMillis() );

        for( int i=0; i < nSize; i++ ) {
            float fFactor = objRandom.nextFloat();
            int nPos = (int)(objLetterList.length()*fFactor);

            strIdentifier += objLetterList.charAt( nPos );
        } // for i

        return strIdentifier;
    }
    static public boolean isHere(String[] strs, String str)
    {
        for( int i=0; i < strs.length; i++ ) {
            if( strs[i].equals(str) ) return true;
        } // for i
        return false;
    }
    static public boolean lessThan(String s1, String s2)
    {
        if( s1.length() != s2.length() ) return false;

        for( int i=0; i < s1.length(); i++ ) {
            char c1 = s1.charAt( i );
            char c2 = s2.charAt( i );
            if( c1 != c2 ) return false;
        } // for i

        return true;
    }
    static public String readFileUrl(String filePath) throws MalformedURLException, IOException
    {
        String strSQL = "";

        try{
            URL objUrl = new URL(filePath);
            URLConnection objUrlConn = objUrl.openConnection();
            InputStream objInputStream = objUrlConn.getInputStream();
            int nLen = objInputStream.available();
            byte pBuffer[] = new byte[nLen];

            objInputStream.read( pBuffer );
            strSQL = new String( pBuffer );
            objInputStream.close();

        } catch(Exception exception) { ; }

        return strSQL;
    }
    static public String readFile(String strFilePath)
    {
        File objFile = new File( strFilePath );
        int nLength = (int)objFile.length( );
        byte[ ] pBuffer = new byte[nLength];
        String str = "";
        try {
            FileInputStream objInStream = new FileInputStream( strFilePath );
            objInStream.read( pBuffer, 0, nLength );
            str = new String( pBuffer );
            //System.out.print( str );
            objInStream.close();
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }
    static public String makeSQL_WhereCond(HashMap<String, String> objConditions)
    {
        if( objConditions == null ) return "";

        ArrayList<String> objFields = new ArrayList<String>( objConditions.keySet() );
        String strWhereCond = "";

        for( int i=0; i < objFields.size(); i++ ) {
            String strField = objFields.get( i );
            String strValue = objConditions.get( strField );

            if( strWhereCond.length() > 0 ) strWhereCond += " AND ";
            strWhereCond += strField+" = '"+strValue+"' \n";
        }

        return strWhereCond;
    }
    static public boolean deleteFile(String strFilePath)
    {
        File objFile = new File( strFilePath );

        return objFile.delete();
    }

    static private String enKey = "0123456789ABCDEF";
    static public String getUTF8HEX(String str)
    {
        byte[] pBuffer = null;

        try {
            pBuffer = str.getBytes("UTF-8");
        } catch( UnsupportedEncodingException unen_ex) {
            return "";
        } // try {

        return getUTF8HEX( pBuffer );

    }
    static public String getUTF8HEX(byte[] pBuffer)
    {
        StringBuffer objResponse = new StringBuffer();

        int byte_s = 0;
        int uw_index, lw_index;
        char uw_char, lw_char;

        for( int i=0; i < pBuffer.length; i++ ) {
            byte_s = pBuffer[i];
            if( byte_s < 0 ) byte_s = -1*byte_s + 127;
            uw_index = byte_s/16;
            lw_index = byte_s%16;
            uw_char = enKey.charAt( uw_index );
            lw_char = enKey.charAt( lw_index );

            objResponse.append( uw_char );
            objResponse.append( lw_char );
        } // for i

        return objResponse.toString();
    }
    static private int[] deKey = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            0,  1,  2,  3,  4,  5,  6,  7,  8,  9, -1, -1, -1, -1, -1, -1,
            -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };
    static public byte[] getFromUTF8HEX_Ary(String str)
    {
        //String strResponse = "";
        byte[] pBuffer = new byte[ str.length()/2 ];
        int byte_s = 0;
        int uw_index, lw_index;
        char uw_char, lw_char;
        int j = 0;

        for( int i=0; i < str.length(); ) {
            uw_char = str.charAt( i++ );
            lw_char = str.charAt( i++ );
            uw_index = deKey[ uw_char ];
            lw_index = deKey[ lw_char ];
            byte_s = uw_index*16 + lw_index;
            if( byte_s > 127 ) byte_s = -1*(byte_s-127);

            pBuffer[j++] = (byte)byte_s;
        } // for i

        return pBuffer;
    }
    static public String getFromUTF8HEX(String str)
    {
        if (str == null) str = "";
        String strResponse = "";
        byte[] pBuffer = new byte[ str.length()/2 ];
        int byte_s = 0;
        int uw_index, lw_index;
        char uw_char, lw_char;
        int j = 0;

        for( int i=0; i < str.length(); ) {
            uw_char = str.charAt( i++ );
            lw_char = str.charAt( i++ );
            uw_index = deKey[ uw_char ];
            lw_index = deKey[ lw_char ];
            byte_s = uw_index*16 + lw_index;
            if( byte_s > 127 ) byte_s = -1*(byte_s-127);

            pBuffer[j++] = (byte)byte_s;
        } // for i

        try {
            strResponse = new String( pBuffer, "UTF-8" );
        } catch( UnsupportedEncodingException unen_ex) {
            return "";
        } // try {

        return strResponse;
    }


    static public String getMailCode( String employee_FK, String screen_FK, String trackingNumber ) {
        String strAccessCode = "";
        strAccessCode += "employee_ID="+employee_FK+"&";
        strAccessCode += "screen_ID="+screen_FK+"&";
        strAccessCode += "trackingNumber="+trackingNumber+"&";

        return Util.getUTF8HEX(strAccessCode);
    }

    static public String createGUID()
    {
        // -----------------------------------------------------------------------------------------------------------------
        Random objRandom = new Random();
        String strEncode = "01234567890ABCDEF";
        String strGUID = "";
        int nPos = 0;
        char c = '\0';
        // -----------------------------------------------------------------------------------------------------------------
        for( int i=0; i < 36; i++ ) {
            if( i == 8 || i == 13 || i == 18 || i == 23 ) {
                c = '-';
            } else {
                nPos = objRandom.nextInt();
                if( nPos < 0 ) nPos *= -1;
                nPos = nPos % strEncode.length();
                c = strEncode.charAt( nPos );
            } // if( i
            strGUID += c;
        } // for i
        // -----------------------------------------------------------------------------------------------------------------
        return strGUID;
    }

    static public String getHander(String strActorId)
    {
        if (strActorId.indexOf("/") > -1) return strActorId.split("/")[1];
        else return strActorId;
    }


    public static String removeSelect(String hql) {
        if (StringUtil.isEmpty(hql))
            return null;
        String temp = hql.toLowerCase();
        if (temp.trim().startsWith("from"))
            return hql;
        else if (!temp.trim().startsWith("select"))
            return null;
        int index = hql.indexOf("from");
        if (index == -1)
            return null;
        return hql.substring(index);
    }

    /*
     * 随机生成密码
     */
    public static String genRandomNum(int pwd_len){
        //35是因为数组是从0开始的，26个字母+10个数字
        final int  maxNum = 36;
        int i;  //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < pwd_len){
            //生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }
    public static String  setNotEmpty(String param){
        if("null".equals(param)){
            param = "";
        }
        return param;
    }

    //将Date转换成Calendar
    public static Calendar dataToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}

