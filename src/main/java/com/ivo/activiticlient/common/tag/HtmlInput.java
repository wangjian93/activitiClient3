package com.ivo.activiticlient.common.tag;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlInput extends HtmlFormComponent
{
    protected String type;
    protected boolean checked;


    public HtmlInput()
    {
        this.initiate();
    }

    public HtmlInput(String type)
    {
        this.initiate();
        this.type = type;
    }

    public void initiate()
    {
        super.initiate();
        this.type = "";
        this.checked = false;

    }

    @Override
    public String toHTML()
    {
        StringBuffer objHTML = new StringBuffer();

        if(this.readonly && this.type.toLowerCase().equals("checkbox")){
            this.readonly = false;
            this.onClick="this.checked=!this.checked";
        }

        objHTML.append( "<input" + " type=\""+ this.type + "\"" + super.toHTML()+ ( this.checked?" checked ":"" )+"/>" );

        return objHTML.toString();
    }

    //-----------------------------------------------------------------------------------------
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }



}
