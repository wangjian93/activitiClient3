package com.ivo.activiticlient.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author wangjian
 * @date 2018/10/11
 */
public class StringUtil {

    private static Log log = LogFactory.getLog(StringUtil.class);

    public static String ENCODING = "UTF-8";

    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials
     * string is returned
     *
     * @param password Password or other credentials to use in authenticating
     *        this username
     * @param algorithm Algorithm used to do the digest
     *
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            log.error("Exception: " + e);
            return password;
        }
        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < encodedPassword.length; i++) {
            if (((int) encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) encodedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * Encode a string using Base64 encoding. Used when storing passwords
     * as cookies.
     *
     * This is weak encoding in that anyone can use the decodeString
     * routine to reverse the encoding.
     *
     * @param str
     * @return String
     */
    public static String encodeString(String str)  {
        if (str == null)
            return null;
        try {
            byte[] b = Base64.encodeBase64(str.getBytes(ENCODING));
            return new String(b, ENCODING);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String encodeStringURLSafe(String str)  {
        if (str == null)
            return null;
        try {
            byte[] b = Base64.encodeBase64URLSafe(str.getBytes(ENCODING));
            return new String(b, ENCODING);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Decode a string using Base64 encoding.
     *
     * @param str
     * @return String
     */
    public static String decodeString(String str) {
        if (str == null)
            return null;
        try {
            byte[] b = Base64.decodeBase64(str.getBytes(ENCODING));
            return new String(b, ENCODING);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param string
     * @return
     */
    public static String swapFirstLetterCase(String string) {
        StringBuffer sbuf = new StringBuffer(string);
        sbuf.deleteCharAt(0);
        if (Character.isLowerCase(string.substring(0, 1).toCharArray()[0])) {
            sbuf.insert(0, string.substring(0, 1).toUpperCase());
        } else {
            sbuf.insert(0, string.substring(0, 1).toLowerCase());
        }
        return sbuf.toString();
    }

    /**
     *
     * @param origString
     * @param trimString
     * @return
     */
    public static String trim(String origString, String trimString) {
        int startPosit = origString.indexOf(trimString);
        if (startPosit != -1) {
            int endPosit = trimString.length() + startPosit;
            return origString.substring(0, startPosit) + origString.substring(endPosit);
        }
        return origString;
    }

    /**
     * @param letters
     */
    public static boolean contain(String origString, String containString) {
        if (origString.indexOf(containString) != -1) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param origString
     * @param stringToken
     */
    public static String getLastString(String origString, String stringToken) {
        StringTokenizer st = new StringTokenizer(origString, stringToken);
        String lastString = "";
        while (st.hasMoreTokens()) {
            lastString = st.nextToken();
        }
        return lastString;
    }

    /**
     * @param string
     * @param token
     */
    public static String[] getStringArray(String string, String token) {
        if (string.indexOf(token) != -1) {
            StringTokenizer st = new StringTokenizer(string, token);
            String[] stringArray = new String[st.countTokens()];
            for (int i = 0 ; st.hasMoreTokens(); i++) {
                stringArray[i] = st.nextToken();
            }
            return stringArray;
        }
        return new String [] {string};
    }
    public static String[] getStringArray(String string) {
        StringTokenizer st = new StringTokenizer(string);
        String[] stringArray = new String[st.countTokens()];
        for (int i = 0 ; st.hasMoreTokens(); i++) {
            stringArray[i] = st.nextToken();
        }
        return stringArray;
    }
    /**
     * work$id - replace("work$id", "$", ".") - work.id
     * @param string
     * @param replaced
     * @param replace
     */
    public static String replace(String string, String replaced, String replace) {
        String newString = "";
        if (string.indexOf(replaced) != -1 ) {
            String s1 = string.substring(0, string.indexOf(replaced));
            String s2 = string.substring(string.indexOf(replaced) + 1);;
            newString = s1 + replace + s2;
        }
        return newString;
    }

    /**
     * Check if a String has length.
     * <p><pre>
     * StringUtil.hasLength(null) = false
     * StringUtil.hasLength("") = false
     * StringUtil.hasLength(" ") = true
     * StringUtil.hasLength("Hello") = true
     * </pre>
     * @param str the String to check, may be <code>null</code>
     * @return <code>true</code> if the String is not null and has length
     */
    public static boolean hasLength(String str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check if a String has text. More specifically, returns <code>true</code>
     * if the string not <code>null<code>, it's <code>length is > 0</code>, and
     * it has at least one non-whitespace character.
     * <p><pre>
     * StringUtil.hasText(null) = false
     * StringUtil.hasText("") = false
     * StringUtil.hasText(" ") = false
     * StringUtil.hasText("12345") = true
     * StringUtil.hasText(" 12345 ") = true
     * </pre>
     * @param str the String to check, may be <code>null</code>
     * @return <code>true</code> if the String is not null, length > 0,
     *         and not whitespace only
     * @see Character#isWhitespace
     */
    public static boolean hasText(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static String toString(Object obj) {
        return ToStringBuilder.reflectionToString(obj,ToStringStyle.DEFAULT_STYLE);
    }

    public static boolean notEmpty(String str) {
        if ( str == null )  return false;
        else if ( str.equals("") ) return false;
        else return true;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !s.trim().equals("");
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    public static boolean isNotNull(String s) {
        return s != null;
    }

    public static boolean isEqual(String s, Object o) {
        return isNotEmpty(s) && s.equals(o);
    }

    /**
     * 将字符串数组转化为in条件用的字符串
     * @param str
     * @return
     */
    public static String getInClause(String[] str) {
        StringBuffer result = new StringBuffer();

        for(int i=0; i<str.length; i++) {
            if(i>0) {
                result.append(",");
            }
            result.append("'" + str[i] +"'");
        }
        return result.toString();
    }

    public static String getInClause(List<String> list) {
        String[] arr = (String[])list.toArray(new String[list.size()]);
        return getInClause(arr);
    }

    public static boolean isNumeric(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
    }


    public static String jsonString(String s){
        String str = "";
        char[] temp = s.toCharArray();
        int n = temp.length;
        for(int i =0;i<n;i++){
            if(temp[i]=='"'){
                str =  str + "”";
            }else{
                str =  str + temp[i];
            }
        }
        return str;
    }
}

