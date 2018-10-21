package com.ivo.activiticlient.common.tag;

import com.ivo.activiticlient.common.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class RecordTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private StringBuilder sb;

    @Override
    public int doEndTag() throws JspException {
        sb = new StringBuilder();
        sb.append("<table class=\"record\"><thead><tr>");
        makeTh("13%", "发送人");
        makeTh("13%", "处理人");
        makeTh("13%", "代理人");
        makeTh("9%", "签核环节");
        makeTh("7%", "决策");
        makeTh("14%", "签核时间");
        makeTh("31%", "意见");
        sb.append("</tr></thead><tbody>");
        String records = (String) pageContext.getAttribute("records",
                PageContext.REQUEST_SCOPE);

        if (StringUtil.isNotEmpty(records)) {
            JSONArray array = JSONArray.fromObject(records);
            for(int i = 0; i < array.size(); i++){
                JSONObject jsonObject = array.getJSONObject(i);
                String sender = (String)jsonObject.get("sender");
                String handler = (String)jsonObject.get("handler");
                String deputy = (String)jsonObject.get("deputy");
                String node = (String)jsonObject.get("node");
                String transition = (String)jsonObject.get("transition");
                String handleDate = (String)jsonObject.get("handleDate");
                String comment = (String)jsonObject.get("comment");
                sb
                        .append("<tr")
                        .append(
                                transition.equals("处理中")
                                        || transition.equals("保留") ? " style=\"background-color:#999;color:#fff\""
                                        : (transition.equals("不同意") ? " style=\"background-color:#FF6789;\""
                                        : "")).append(">");
                makeTd(sender);
                makeTd(handler);
                makeTd(deputy);
                makeTd(node);
                makeTd(transition);
                makeTd(handleDate);
                makeTd(comment);
                sb.append("</tr>");
            }
        }
        sb.append("</tbody></table>");
        try {
            pageContext.getOut().print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    private void makeTh(String width, String title) {
        sb.append("<th class=\"Header_Text\" style=\"width:").append(width).append("\">").append(
                title).append("</th>");
    }

    private void makeTd(String value) {
        sb.append("<td>").append(value).append("</td>");
    }

}
