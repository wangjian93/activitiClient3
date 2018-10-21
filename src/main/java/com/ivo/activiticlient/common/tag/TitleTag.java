package com.ivo.activiticlient.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class TitleTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String title;
    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut()
                    .print("<div class=\"title\">"
                            + title + "</div><div class=\"back-to\"><a href=\"#top\" title=\"返回顶部\"></a></div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
