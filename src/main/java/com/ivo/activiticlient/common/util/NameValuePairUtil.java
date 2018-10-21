package com.ivo.activiticlient.common.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/13
 */
public class NameValuePairUtil {
    private List<NameValuePair> list;

    public List<NameValuePair> getList() {
        return list;
    }

    public void setList(List<NameValuePair> list) {
        this.list = list;
    }

    public NameValuePairUtil(){
        list = new ArrayList<NameValuePair>();
    }

    public void add(String key, String value){
        list.add(new BasicNameValuePair(key, value));
    }
}
