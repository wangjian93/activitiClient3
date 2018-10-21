package com.ivo.activiticlient.biz;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangjian
 * @date 2018/10/13
 */
public abstract interface OrderBehavior {

    public abstract void extractModel(Extractor extractor, Model model);

    public abstract Map<String, Object> prepareVariables(Model model, String transition, String sid, String token);

    public abstract String validate(Model model, String transition);

    public abstract void beforeTransition(Model model, String token, String transition);

    public abstract void afterTransition(Model model, String token, String transition);

    public abstract Map<String, Object> addAttributes(Model model);

    public abstract void setAttribute(HttpServletRequest request, Model model);

    public abstract String getEntity();

    public abstract String getViewPath();

    public abstract void createOrder(String action, Model model);

    public abstract String sortParam(String params);
}
