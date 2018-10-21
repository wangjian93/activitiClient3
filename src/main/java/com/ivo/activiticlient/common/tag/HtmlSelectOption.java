package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.StringUtil;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlSelectOption extends HtmlComponent{

    private String key;
    private String value;
    private boolean selected = false;

    public HtmlSelectOption() {
    }

    public HtmlSelectOption(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public HtmlSelectOption(String key, String value, boolean selected) {
        this.key = key;
        this.value = value;
        this.selected = selected;
    }

    @Override
    public String toHTML() {
        StringBuffer objHTML = new StringBuffer();

        objHTML.append("<option value=\"" + this.key.trim() +"\"" );
        if ( this.selected ) objHTML.append(" selected");
        objHTML.append(">" + this.value +"</option>\n");

        return objHTML.toString();
    }

    public String toEnHtml() {
        StringBuffer html = new StringBuffer();
        html.append("<option value=\"" + this.key.trim() +"\"" );
        if ( this.selected ) html.append(" selected");
        html.append(">");
        if ( StringUtil.notEmpty(this.key)) html.append("[" + this.key.trim() + "]");

        html.append("&nbsp;" + this.value +"</option>\n");

        return html.toString();
    }

    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}

