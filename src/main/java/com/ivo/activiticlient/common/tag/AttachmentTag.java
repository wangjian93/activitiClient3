package com.ivo.activiticlient.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class AttachmentTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public int doEndTag() throws JspException {
        try {


            boolean bReadOnly = (Boolean)pageContext.getAttribute("bReadOnly",PageContext.REQUEST_SCOPE);

            StringBuilder sb = new StringBuilder();
            sb.append("<div class=\"attach\"><div class=\"attachPanel\" id=\"attachPanel\"></div>");
            if(!bReadOnly){
                sb.append("<div class=\"upload\"><a href=\"javascript:void(0)\" onclick=\"onUpload()\" title=\"添加附件\"></a></div>");
            }
            sb.append("</div><div style=\"display:none\"><iframe id=\"uploadFrame\" src=\"upload.do\" ></iframe></div>");


            pageContext.getOut().print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

}
