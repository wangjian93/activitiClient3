package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.StringUtil;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlComponentTag extends TagSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected HtmlComponent objHtmlComponent;

    protected String id;
    protected String cssClass;
    protected String cssStyle;

    protected void populateParams() {
        objHtmlComponent.setId( this.id );
        if ( StringUtil.notEmpty( this.cssClass )) objHtmlComponent.setCssClass( this.cssClass );
        if ( StringUtil.notEmpty( this.cssStyle )) objHtmlComponent.setCssStyle( this.cssStyle );
    }
    //	------------------------------------------------------------------------------------------------------
    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HtmlComponent getObjHtmlComponent() {
        return objHtmlComponent;
    }

    public void setObjHtmlComponent(HtmlComponent objHtmlComponent) {
        this.objHtmlComponent = objHtmlComponent;
    }

}
