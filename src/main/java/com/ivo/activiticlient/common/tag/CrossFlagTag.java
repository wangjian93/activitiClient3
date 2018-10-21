package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.DateUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class CrossFlagTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String timeOfBeg;
    private String timeOfEnd;

    @Override
    public int doEndTag() throws JspException {
        int crossFlag = 0;
        String beginFrom = DateUtil.getFromDate(DateUtil.parseDate(timeOfBeg));
        String endFrom = DateUtil.getFromDate(DateUtil.parseDate(timeOfEnd));
        if(!beginFrom.equals(endFrom))crossFlag = 1;
        try {
            pageContext.getOut().print(crossFlag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public String getTimeOfBeg() {
        return timeOfBeg;
    }

    public void setTimeOfBeg(String timeOfBeg) {
        this.timeOfBeg = timeOfBeg;
    }

    public String getTimeOfEnd() {
        return timeOfEnd;
    }

    public void setTimeOfEnd(String timeOfEnd) {
        this.timeOfEnd = timeOfEnd;
    }

}
