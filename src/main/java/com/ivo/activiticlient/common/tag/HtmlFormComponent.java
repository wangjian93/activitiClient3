package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.StringUtil;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlFormComponent extends HtmlComponent
{
    protected String value;
    protected String label;
    protected boolean readonly;
    protected boolean disable;
    protected String onClick;
    protected String onChange;
    protected String onMouseOut;
    protected String onMouseOver;

    public HtmlFormComponent()
    {
        this.initiate();
    }
    public void initiate()
    {
        super.initiate();
        this.value = "";
        this.label = "";
        this.readonly = false;
        this.disable = false;
        this.onClick = "";
        this.onChange = "";
        this.onMouseOut = "";
        this.onMouseOver = "";
    }
    public String toHTML()
    {
        StringBuffer objHTML = new StringBuffer(super.toHTML());

        if( StringUtil.notEmpty( this.value ) ) objHTML.append( " value=\"" + this.value + "\"" );
        if ( this.readonly ) objHTML.append( " readonly " );
        if ( this.disable ) objHTML.append( " disable=\"" + this.disable + "\"" );
        if ( StringUtil.notEmpty( this.onClick ) ) objHTML.append( " onclick=\"" + this.onClick + "\"" );
        if ( StringUtil.notEmpty( this.onChange ) ) objHTML.append( " onchange=\"" + this.onChange + "\"" );
        if ( StringUtil.notEmpty( this.onMouseOut ) ) objHTML.append( " onMouseOut=\"" + this.onMouseOut + "\"" );
        if ( StringUtil.notEmpty( this.onMouseOver ) ) objHTML.append( " onMouseOver=\"" + this.onMouseOver + "\"" );

        return objHTML.toString();
    }

    public boolean isReadonly()
    {
        return readonly;
    }
    public void setReadonly(boolean readonly)
    {
        this.readonly = readonly;
    }
    public boolean isDisable()
    {
        return disable;
    }
    public void setDisable(boolean disable)
    {
        this.disable = disable;
    }
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    public String getOnClick()
    {
        return onClick;
    }
    public void setOnClick(String onClick)
    {
        this.onClick = onClick;
    }
    public String getOnChange() {
        return onChange;
    }
    public void setOnChange( String onChange ) {
        this.onChange = onChange;
    }
    public String getOnMouseOut() {
        return onMouseOut;
    }
    public void setOnMouseOut( String onMouseOut ) {
        this.onMouseOut = onMouseOut;
    }
    public String getOnMouseOver() {
        return onMouseOver;
    }
    public void setOnMouseOver( String onMouseOver ) {
        this.onMouseOver = onMouseOver;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel( String label ) {
        this.label = label;
    }

}
