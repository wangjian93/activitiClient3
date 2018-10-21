package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.StringUtil;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlFormComponentTag extends HtmlComponentTag {

    private static final long serialVersionUID = -4135803404721594899L;

    protected String value;
    protected String readonly;
    protected String disable;
    protected String onClick;
    protected String onChange;

    protected void populateParams() {
        super.populateParams();
        HtmlFormComponent objHtmlFormComponent = (HtmlFormComponent)objHtmlComponent;

        if ( StringUtil.notEmpty( this.value ) ) objHtmlFormComponent.setValue( this.value );
        if ( this.readonly!=null && this.readonly.equals("true") ) objHtmlFormComponent.setReadonly( true );
        if ( this.disable!=null && this.disable.equals("true") ) objHtmlFormComponent.setDisable( true );
        if ( StringUtil.notEmpty( this.onClick ) ) objHtmlFormComponent.setOnClick( this.onClick );
        if ( StringUtil.notEmpty( this.onChange ) ) objHtmlFormComponent.setOnChange( this.onChange );

    }
    //	------------------------------------------------------------------------------------------------------
    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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

    public String getOnChange()
    {
        return onChange;
    }
    public void setOnChange(String onChange)
    {
        this.onChange = onChange;
    }
}