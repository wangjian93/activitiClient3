package com.ivo.activiticlient.common.tag;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlImageButton
{
    public String Input_ID;
    public String Label;
    public String Callback;
    public String ImageUrl_onMouseOut;
    public String ImageUrl_onMouseOver;

    public HtmlImageButton()
    {
        initiate();
    }
    public HtmlImageButton(String strInput_ID, String strLabel, String strCallback, String strImageUrl_onMouseOut, String strImageUrl_onMouseOver)
    {
        initiate();
        this.Input_ID = strInput_ID;
        this.Label = strLabel;
        this.Callback = strCallback;
        this.ImageUrl_onMouseOut = strImageUrl_onMouseOut;
        this.ImageUrl_onMouseOver = strImageUrl_onMouseOver;
    }
    public void initiate()
    {
        this.Input_ID = "";
        this.Label = "";
        this.Callback = "";
        this.ImageUrl_onMouseOut = "";
        this.ImageUrl_onMouseOver = "";
    }
    public String toHTML()
    {
        String strHTML = "";

        strHTML += "<img id="+Input_ID+" style=\"cursor:hand; \" \n";
        strHTML += "	onmouseout=\"ImageButton_onMouseOut('"+Input_ID+"', '"+ImageUrl_onMouseOut+"'); \" \n";
        strHTML += "	onmouseover=\"ImageButton_onMouseOver('"+Input_ID+"', '"+ImageUrl_onMouseOver+"'); \" \n";
        strHTML += "	onclick=\""+Callback+"\" \n";
        strHTML += "	src=\""+ImageUrl_onMouseOut+"\" \n";
        strHTML += "	title=\""+Label+"\"> \n";

        return strHTML;
    }
}
