package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.biz.Model;
import com.ivo.activiticlient.domain.Department;
import com.ivo.activiticlient.domain.Employee;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class HiddenTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private StringBuilder sb;

    @Override
    public int doEndTag() throws JspException {
        sb = new StringBuilder();

        System.out.println((String)pageContext.getAttribute("error",
                PageContext.REQUEST_SCOPE));
        Model model = (Model) pageContext.getAttribute("model",
                PageContext.REQUEST_SCOPE);
        Employee user = (Employee) pageContext.getAttribute("user",
                PageContext.SESSION_SCOPE);
        Department group = (Department) pageContext.getAttribute("group",
                PageContext.SESSION_SCOPE);

        generateHidden("error", (String)pageContext.getAttribute("error",
                PageContext.REQUEST_SCOPE));
        generateHidden("comment", "");
        generateHidden("userList", "");
        generateHidden("node", model.getStatus() != null ? model.getStatus() : "");
        generateHidden("currentUser", user != null ? user.getEmployee_ID() : "");
        generateHidden("currentUserName", user != null ? user.getEmployeeName() : "");
        generateHidden("currentGroup", group != null ? group.getDepartment_ID() : "");
        generateHidden("isNewOrder", pageContext.getAttribute(
                "isNewOrder", PageContext.REQUEST_SCOPE));
        generateHidden("opcode", "-1");

        String token = (String)pageContext.getAttribute("token");
        generateHidden("token", token);

        try {
            pageContext.getOut().print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    private void generateHidden(String id, Object value) {
        if (value == null)
            value = "";
        sb.append("<input id=\"").append(id).append("\" name=\"").append(id)
                .append("\" type=\"hidden\" value =\"").append(value).append(
                "\"/>");
    }
}
