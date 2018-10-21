package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.StringUtil;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlComponent
{
    protected String id;
    protected String cssClass;
    protected String cssStyle;
    protected String width;
    protected String height;
    protected String foreColor;
    protected String backColor;
    protected String hoverForeColor;
    protected String hoverBackColor;
    protected String borderColor;
    protected String valign;
    protected String align;

    protected String onMouseOver;
    protected String onMouseOut;
    protected String onDblClick;

    public HtmlComponent()
    {
        this.initiate();
    }
    public void initiate()
    {
        this.id = "";
        this.cssClass = "";
        this.cssStyle = "";
        this.width = "";
        this.height = "";
        this.foreColor = "";
        this.backColor = "";
        this.hoverForeColor = "";
        this.hoverBackColor = "";
        this.borderColor = "";
        this.valign = "";
        this.align = "";
    }
    public String toHTML()
    {
        StringBuffer objHTML = new StringBuffer();

        if( StringUtil.notEmpty( this.id ) ) objHTML.append( " id=\"" + this.id + "\" name=\"" + this.id + "\"" );
        if( StringUtil.notEmpty( this.width ) ) objHTML.append( " width=\"" + this.width + "\"" );

        if( StringUtil.notEmpty( this.cssClass ) ) objHTML.append( " class=\"" + this.cssClass + "\"" );
        if( StringUtil.notEmpty( this.cssStyle ) ) objHTML.append( " style=\"" + this.cssStyle + "\"" );
        if( StringUtil.notEmpty( this.valign ) ) objHTML.append( " valign=\"" + this.valign + "\"" );
        if( StringUtil.notEmpty( this.align ) ) objHTML.append( " align=\"" + this.align + "\"" );

        if( StringUtil.notEmpty( this.onMouseOver ) ) objHTML.append( " onmouseover=\"" + this.onMouseOver + "\"" );
        if( StringUtil.notEmpty( this.onMouseOut ) ) objHTML.append( " onmouseout=\"" + this.onMouseOut + "\"" );
        if( StringUtil.notEmpty( this.onDblClick ) ) objHTML.append( " ondblclick=\"" + this.onDblClick + "\"" );

        return objHTML.toString();
    }
    //===================================================================================================================================
    public String getCssClass()
    {
        return cssClass;
    }
    public void setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
    }
    public String getCssStyle()
    {
        return cssStyle;
    }
    public void setCssStyle(String cssStyle)
    {
        this.cssStyle = cssStyle;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getBackColor()
    {
        return backColor;
    }
    public void setBackColor(String backColor)
    {
        this.backColor = backColor;
    }
    public String getForeColor()
    {
        return foreColor;
    }
    public void setForeColor(String foreColor)
    {
        this.foreColor = foreColor;
    }
    public String getHoverBackColor()
    {
        return hoverBackColor;
    }
    public void setHoverBackColor(String hoverBackColor)
    {
        this.hoverBackColor = hoverBackColor;
    }
    public String getHoverForeColor()
    {
        return hoverForeColor;
    }
    public void setHoverForeColor(String hoverForeColor)
    {
        this.hoverForeColor = hoverForeColor;
    }
    public String getBorderColor()
    {
        return borderColor;
    }
    public void setBorderColor(String borderColor)
    {
        this.borderColor = borderColor;
    }
    public String getHeight()
    {
        return height;
    }
    public void setHeight(String height)
    {
        this.height = height;
    }
    public String getWidth()
    {
        return width;
    }
    public void setWidth(String width)
    {
        this.width = width;
    }
    public String getValign() {
        return valign;
    }
    public void setValign( String valign ) {
        this.valign = valign;
    }
    public String getAlign() {
        return align;
    }
    public void setAlign(String align) {
        this.align = align;
    }
    public String getOnMouseOver() {
        return onMouseOver;
    }
    public void setOnMouseOver( String onMouseOver ) {
        this.onMouseOver = onMouseOver;
    }
    public String getOnMouseOut() {
        return onMouseOut;
    }
    public void setOnMouseOut( String onMouseOut ) {
        this.onMouseOut = onMouseOut;
    }
    public String getOnDblClick() {
        return onDblClick;
    }
    public void setOnDblClick( String onDblClick ) {
        this.onDblClick = onDblClick;
    }

}
