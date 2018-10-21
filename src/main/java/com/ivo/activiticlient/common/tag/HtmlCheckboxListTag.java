package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlCheckboxListTag extends HtmlFormComponentTag {

    private static final long serialVersionUID = -643451054921613166L;

    private int columnNumber;
    private String checkboxValues;
    private HtmlCheckboxList objHtmlCheckboxList;

    protected void populateParams() {
        super.populateParams();
        this.objHtmlCheckboxList = (HtmlCheckboxList)objHtmlComponent;
        this.objHtmlCheckboxList.setColumnNumber( this.columnNumber == 0 ? 1 : this.columnNumber );
        this.objHtmlCheckboxList.setCheckboxValues( null );
    }

    @Override
    public int doEndTag() throws JspException {

        this.objHtmlComponent = new HtmlCheckboxList();
        this.populateParams();

        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();

        StringBuffer objHTML = new StringBuffer();
        HtmlInput objHtmlInput = new HtmlInput();
        objHtmlInput.setId( this.id );
        objHtmlInput.setType( "hidden" );
        objHtmlInput.setValue( this.value );

        objHTML.append( objHtmlInput.toHTML() + "\n" );

        if ( StringUtil.notEmpty( this.checkboxValues ) ){
            List objCheckboxValues = (List)request.getAttribute( this.checkboxValues );

            this.objHtmlCheckboxList.setCheckboxValues( objCheckboxValues );
        }

        objHTML.append( this.objHtmlCheckboxList.toHTML() );

        JspWriter out = pageContext.getOut();
        try{
            out.println( objHTML.toString() );
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return Tag.EVAL_PAGE;
    }
//  --------------------------------------------------------------------------------------------------------------------------

    public String getCheckboxValues()
    {
        return checkboxValues;
    }

    public void setCheckboxValues(String checkboxValues)
    {
        this.checkboxValues = checkboxValues;
    }

    public HtmlCheckboxList getObjHtmlCheckboxList()
    {
        return objHtmlCheckboxList;
    }

    public void setObjHtmlCheckboxList(HtmlCheckboxList objHtmlCheckboxList)
    {
        this.objHtmlCheckboxList = objHtmlCheckboxList;
    }

    public int getColumnNumber()
    {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber)
    {
        this.columnNumber = columnNumber;
    }


}
