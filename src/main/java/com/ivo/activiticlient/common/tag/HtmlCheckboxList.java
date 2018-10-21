package com.ivo.activiticlient.common.tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HtmlCheckboxList extends HtmlInput
{
    private List<String> checkboxValues;
    private int columnNumber;

    public HtmlCheckboxList()
    {
        this.initiate();
    }

    public HtmlCheckboxList(List<String> checkboxValues, int columnNumber)
    {
        this.initiate();
        this.checkboxValues = checkboxValues;
        this.columnNumber = columnNumber;
    }

    public void initiate()
    {
        super.initiate();
        this.checkboxValues = null;
        this.columnNumber = 0;
    }

    @Override
    public String toHTML()
    {
        StringBuffer objHTML = new StringBuffer();
        List objCheckboxList = new ArrayList();
        int fbit = 0;
        int fCheckValue = Integer.parseInt( this.value );
        int fColumnNumber = this.columnNumber;
        int fRowSize = 0;
        int fCheckboxSize = this.checkboxValues.size();

        fRowSize = fCheckboxSize/fColumnNumber;
        if ( fCheckboxSize % fColumnNumber != 0 ) fRowSize++;

        for( int i=0; i<checkboxValues.size(); i++ ) {
            String checkbox = checkboxValues.get( i );
            fbit = (int)Math.pow(2, i);

            HtmlInput objHtmlInput = new HtmlInput();

            objHtmlInput.setId( this.id+"_chk_"+i );
            objHtmlInput.setType( "checkbox" );
            if( this.readonly ){
                objHtmlInput.setOnClick( "this.checked = !this.checked;" );
            }else{
                objHtmlInput.setOnClick( "ChooseCheckbox(this, '"+this.id+"');" );
            }
            objHtmlInput.setValue( fbit+"" );
            if( (fCheckValue & fbit) == fbit ) objHtmlInput.setChecked( true );

            objCheckboxList.add( objHtmlInput.toHTML()+checkbox+"&nbsp;" );
        }

        int k = 0;
        objHTML.append( "<table>" );
        for( int i=0; i<fRowSize; i++ ){
            objHTML.append( "<tr>" );
            for ( int j=0; j<fColumnNumber; j++ ){
                if ( k >= fCheckboxSize ) break;
                objHTML.append( "<td class="+this.cssClass+">" );
                objHTML.append( objCheckboxList.get( k++ ));
                objHTML.append( "</td>" );
            }
            objHTML.append( "</tr>" );
        }
        objHTML.append( "</table>" );

        return objHTML.toString();
    }
//-----------------------------------------------------------------------------------------------------

    public List<String> getCheckboxValues()
    {
        return checkboxValues;
    }

    public void setCheckboxValues(List<String> checkboxValues)
    {
        this.checkboxValues = checkboxValues;
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
