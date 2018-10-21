package com.ivo.activiticlient.common.tag;

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
public class MenuBarTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private StringBuilder sb;

    @Override
    public int doEndTag() throws JspException {
        sb = new StringBuilder();
        sb.append("<div class=\"menubj\" onselectstart=\"return false;\">");
        sb.append("<div class=\"menubj-fixed\">");
        sb.append("<div id=\"menubar\" class=\"menu\" onselectstart=\"return false;\">");
        sb.append("<div class=\"menu-fixed\">");
        String action = (String)pageContext.getAttribute("items",
                PageContext.REQUEST_SCOPE);

        JSONArray array = JSONArray.fromObject(action);
        for(int i = 0; i < array.size(); i++){
            JSONObject jsonObject = array.getJSONObject(i);
            String action_ = (String)jsonObject.get("action");
            String name_ = (String)jsonObject.get("name");
            String comment_ = (String)jsonObject.get("comment");
            String fun_ = (String)jsonObject.get("fun");
            makeItem(action_,name_,comment_,fun_);
        }
        sb.append("</div></div></div></div>");
        try {
            pageContext.getOut().print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    private void makeItem(String id, String nameCN, String nameEN,
                          String onclick) {
        sb.append("<a href=\"javascript:void(0);\" onclick=\"")
                .append(onclick).append("\" title=\"").append(nameCN).append("\">")
                .append("<div style=\"background:url(images/action/").append(id)
                .append(".png) no-repeat 10px center;\" >")
                .append(nameCN).append("</div></a>");
    }

}
