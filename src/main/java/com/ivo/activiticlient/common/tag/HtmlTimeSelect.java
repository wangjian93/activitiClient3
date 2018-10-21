package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.FormatUtil;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlTimeSelect extends HtmlFormComponent{

    private int beforeDays;
    private String employee_ID;
    private String cssStyle;

    @Override
    public String toHTML(){
        StringBuffer html = new StringBuffer("");

        String date = "";
        String hour = "00";
        String min = "00";

        if(this.value.length()==19){
            date = this.value.substring(0, 10);
            hour = this.value.substring(11, 13);
            min = this.value.substring(14, 16);
        }

        //个人日历选择框
        HtmlInput dateInput = new HtmlInput("text");
        dateInput.setId(id+"_date");
        dateInput.setValue(date);
        dateInput.setCssStyle(cssStyle);
        dateInput.setReadonly(true);

        if( !this.onChange.equals("") ) dateInput.setOnChange("setDatetime('"+this.id+"'); "+this.onChange);
        else dateInput.setOnChange("setDatetime('"+this.id+"');");

        if(this.readonly){
            dateInput.setCssClass("readOnlyInput");
            cssStyle = "width:40%;";
            dateInput.setCssStyle(cssStyle);
        } else {
            dateInput.setOnClick("Dlg_openShiftArrangement('"+id+"_date"+"', '"+employee_ID+"','"+beforeDays+"');");
            dateInput.setCssClass("selectInput");
        }

        html.append(dateInput.toHTML());
        html.append("&nbsp;");

        //小时选择框
        HtmlSelect hourSelect = new HtmlSelect();
        hourSelect.setId(id+"_hour");
        hourSelect.setValue(hour);
        hourSelect.setOnChange("setDatetime( '"+this.id+"'); ActualHour_onLabel('@Row_ID@');");
        hourSelect.setReadonly(this.readonly);
        if(this.readonly){
            hourSelect.setCssClass("readOnlyInput");
        } else {
            hourSelect.setCssClass("");
        }
        for(int i=0;i<24;i++){
            String value = FormatUtil.formatNumber("00", i);
            hourSelect.addOptions(value, value);
        }

        html.append(hourSelect.toHTML());
        html.append(":");

        //分钟下拉框
        HtmlSelect miniteSelect = new HtmlSelect();
        miniteSelect.setId(id+"_min");
        miniteSelect.setValue(min);
        miniteSelect.setOnChange("setDatetime( '"+this.id+"'); ActualHour_onLabel('@Row_ID@');");
        miniteSelect.setReadonly(this.readonly);
        if(this.readonly){
            miniteSelect.setCssClass("readOnlyInput");
        } else {
            miniteSelect.setCssClass("");
        }

        miniteSelect.addOptions("00", "00");
        miniteSelect.addOptions("30", "30");

        html.append(miniteSelect.toHTML());
        html.append("\n");

        //隐藏的日期值
        HtmlInput datetimeInput = new HtmlInput("hidden");
        datetimeInput.setId(this.id);
        datetimeInput.setValue(value);

        html.append(datetimeInput.toHTML());
        //html.append("<br/>");

        return html.toString();
    }

    public int getBeforeDays() {
        return beforeDays;
    }

    public void setBeforeDays( int beforeDays ) {
        this.beforeDays = beforeDays;
    }

    public String getEmployee_ID() {
        return employee_ID;
    }

    public void setEmployee_ID( String employee_ID ) {
        this.employee_ID = employee_ID;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }



}
