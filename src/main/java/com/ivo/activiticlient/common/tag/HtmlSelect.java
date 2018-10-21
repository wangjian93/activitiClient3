package com.ivo.activiticlient.common.tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlSelect extends HtmlFormComponent
{
    private boolean enhance;
    private List<HtmlSelectOption> options = new ArrayList<HtmlSelectOption>();

    public HtmlSelect()
    {
    }

    public HtmlSelect(String id)
    {
        this.id = id;
    }

    public HtmlSelect(String value, boolean readonly)
    {
        this.value = value;
        this.readonly = readonly;
    }

    public HtmlSelect(String value, List<HtmlSelectOption> options)
    {
        this.value = value;
        this.options = options;
    }

    @Override
    public String toHTML()
    {
        StringBuffer objHTML = new StringBuffer();

        if(this.readonly){
            objHTML.append("<input type=\"text\" id=\"").append(id).append(
                    "\" name=\"").append(id).append("\" value=\"")
                    .append(value).append( "\" style=\"width:40PX;\" readonly/>");

        }else{
            objHTML.append( "<select style=\"width:50PX;\"" + super.toHTML() + ">\n" );

            for( HtmlSelectOption option : options ) {
                if( this.readonly ) {
                    if( option.getKey().equals( this.value ) ) {
                        objHTML.append( enhance ? option.toEnHtml() : option.toHTML() );
                    }
                } else {
                    objHTML.append( enhance ? option.toEnHtml() : option.toHTML() );
                }
            }
            objHTML.append( "</select>" );
        }

        return objHTML.toString();
    }

    public List<HtmlSelectOption> getOptions()
    {
        return options;
    }

    public void setOptions(List<HtmlSelectOption> options)
    {
        this.options = options;
    }

    public void addOptions(HtmlSelectOption option)
    {
        this.options.add( option );
    }

    public void addOptions(String value)
    {
        HtmlSelectOption option = null;
        if( this.value != null && this.value.equals( value ) ) option = new HtmlSelectOption( value, value, true );
        else option = new HtmlSelectOption( value, value );
        this.addOptions( option );
    }

    public void addOptions(String key, String value)
    {
        HtmlSelectOption option = null;
        if( this.value != null && this.value.equals( key ) ) option = new HtmlSelectOption( key, value, true );
        else option = new HtmlSelectOption( key, value );
        this.addOptions( option );
    }

    public boolean isEnhance()
    {
        return enhance;
    }

    public void setEnhance(boolean enhance)
    {
        this.enhance = enhance;
    }

}
