package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.Couple;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class Select extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String list;

    private String onchange;

    private boolean readonly = false;

    private String value;

    private String readonlyValue;

    private String readonlyStyle;

    @Override
    public int doEndTag() throws JspException {
        StringBuilder sb = new StringBuilder();
        if (readonly) {


            if (readonlyStyle != null) {
                if (readonlyStyle.equals("select"))
                    sb.append("<select><option>").append(
                            readonlyValue != null ? readonlyValue : "").append(
                            "</select></option>");
                else if (readonlyStyle.equals("text"))
                    sb.append(readonlyValue != null ? readonlyValue : "");
            } else
                sb.append("<input type=\"hidden\" id=\"").append(id).append(
                        "\" name=\"").append(id).append("\" value=\"").append(
                        value).append("\"/>").append(
                        "<input type=\"text\" id=\"").append(id+"_text").append(
                        "\" name=\"").append(id+"_text").append("\" value=\"").append(
                        readonlyValue != null ? readonlyValue : "").append(
                        "\" readonly/>");
        } else {
            sb
                    .append("<select id=\"")
                    .append(id)
                    .append("\" name=\"")
                    .append(id)
                    .append("\"")
                    .append(
                            onchange != null && !onchange.equals("") ? " onchange=\"selectClick("
                                    + onchange + ")\""
                                    : "").append(">");
            Object object = pageContext.getAttribute(list,
                    PageContext.REQUEST_SCOPE);
            if (object != null) {
                List<?> list = (List<?>) object;
                Couple couple;
                String key;
                String label;
                for (Object o : list) {
                    if (o == null)
                        continue;
                    if (o instanceof Couple) {
                        couple = (Couple) o;
                        key = couple.getKey();
                        label = couple.getLabel();
                    } else {
                        key = o.toString();
                        label = o.toString();
                    }
                    sb
                            .append("<option value=\"")
                            .append(key)
                            .append("\"")
                            .append(
                                    key.equals(value) ? " selected=\"selected\""
                                            : "").append(">").append(label)
                            .append("</option>");
                }
                sb.append("</select>");
                sb.append("<input type=\"hidden\" id=\"").append(id+"_text").append(
                        "\" name=\"").append(id+"_text").append("\" value=\"").append(
                        readonlyValue != null ? readonlyValue : "").append(
                        "\" readonly/>");
            }
        }
        try {
            pageContext.getOut().print(sb.toString());
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

    public void setReadonlyValue(String readonlyValue) {
        this.readonlyValue = readonlyValue;
    }

    public void setReadonlyStyle(String readonlyStyle) {
        this.readonlyStyle = readonlyStyle;
    }

}
