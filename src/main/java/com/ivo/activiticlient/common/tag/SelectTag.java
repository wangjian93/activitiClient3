package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.Couple;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class SelectTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String list;

    private String onchange;

    private String cssStyle;

    private boolean readonly = false;

    private String value;

    private boolean showKey = false;

    @Override
    public int doEndTag() throws JspException {
        StringBuilder sb = new StringBuilder();
        sb
                .append("<select id=\"")
                .append(id)
                .append("\" name=\"")
                .append(id)
                .append("\" ")
                .append(readonly ? "disabled" : "")
                .append(onchange != null && !onchange.equals("") ? " onchange=\""+onchange+"\"" : "")
                .append(cssStyle != null && !cssStyle.equals("") ? " style=\""+cssStyle + "\"" : "")
                .append(">");
        Object object = pageContext.getAttribute(list,
                PageContext.REQUEST_SCOPE);
        if (object != null) {
            List<?> list = (ArrayList<?>) object;
            Couple couple;
            String key;
            String label;
            for (Object o : list) {
                if (o instanceof Couple) {
                    couple = (Couple) o;
                    key = couple.getKey();
                    label = couple.getLabel();
                } else
                    continue;
                sb
                        .append("<option value=\"")
                        .append(key)
                        .append("\"")
                        .append(
                                key.equals(value) ? " selected=\"selected\""
                                        : "").append(">");
                if(showKey){
                    sb.append("[" + key + "]");
                }
                sb.append(label)
                        .append("</option>");
            }
            sb.append("</select>");
        }
        try {
            pageContext.getOut().println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setList(String list) {
        this.list = list;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public boolean isShowKey() {
        return showKey;
    }

    public void setShowKey(boolean showKey) {
        this.showKey = showKey;
    }
}
