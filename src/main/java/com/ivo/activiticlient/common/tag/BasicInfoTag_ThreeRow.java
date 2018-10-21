package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.biz.Model;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class BasicInfoTag_ThreeRow extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private StringBuilder sb;

    @Override
    public int doEndTag() throws JspException {
        Model model = (Model) pageContext.getAttribute("model",
                PageContext.REQUEST_SCOPE);
        sb = new StringBuilder();
        sb.append("<tr>");
        makeTd("签核单号：", "orderNumber", model.getOrderNumber());
        String status = "签核中";
        if (model.getStatus().equals("020"))
            status = "起草中";
        else if (model.getStatus().equals("990"))
            status = "已删除";
        else if (model.getStatus().equals("980"))
            status = "签核结束";
		/*makeTd("签核状态：", "state", status);
		sb.append("</tr><tr>");*/
        makeTd("起草人：", "drafter", "[" + model.getDrafter().getEmployee_ID() + "] "
                + model.getDrafter().getEmployeeName());
        makeTd("起草单位：", "draftGroup", "[" + model.getDraftGroup().getDepartment_ID()
                + "] " + model.getDraftGroup().getDepartmentName());
		/*sb.append("</tr><tr>");
		makeTd("起草时间：", "draftDate", DateUtil.formatDateTime(model
				.getDraftDate()));
		makeTd("结束时间：", "endDate", DateUtil.formatDateTime(model.getEndDate()));*/
        sb.append("</tr>");
        try {
            pageContext.getOut().print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    private void makeTd(String label, String id, String value) {
        sb.append("<td class=\"label\">").append(label).append(
                "</td><td><input type=\"text\" id=\"").append(id).append(
                "\" name=\"").append(id).append("\" value=\"").append(value)
                .append("\" readonly></td>");
    }
}
