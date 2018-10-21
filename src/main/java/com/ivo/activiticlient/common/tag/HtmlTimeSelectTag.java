package com.ivo.activiticlient.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlTimeSelectTag extends HtmlFormComponentTag {


    private static final long serialVersionUID = 8843740105440927350L;

    private int beforeDays;
    private String employee_ID;
    private String cssStyle;

    private HtmlTimeSelect objHtmlTimeSelect;

    protected void populateParams() {
        super.populateParams();
        objHtmlTimeSelect = (HtmlTimeSelect) objHtmlComponent;
        objHtmlTimeSelect.setBeforeDays(beforeDays);
        objHtmlTimeSelect.setEmployee_ID(employee_ID);
        objHtmlTimeSelect.setCssStyle(cssStyle);
    }

    @Override
    public int doEndTag() throws JspException {
        objHtmlComponent = new HtmlTimeSelect();
        this.populateParams();

        try {
            JspWriter out = pageContext.getOut();

            out.println( objHtmlTimeSelect.toHTML() );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Tag.EVAL_PAGE;
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
