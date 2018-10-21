package com.ivo.activiticlient.biz;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjian
 * @date 2018/10/13
 */
public abstract class SimpleOrderBehavior implements OrderBehavior {

    @Override
    public void extractModel(Extractor extractor, Model model) {
        // TODO Auto-generated method stub
    }

    @Override
    public Map<String, Object> prepareVariables(Model model, String transition, String sid, String token) {
        return new HashMap<String, Object>();
    }

    @Override
    public String validate(Model model, String transition) {
        return "";
    }

    @Override
    public Map<String, Object> addAttributes(Model model) {
        return new HashMap<String, Object>();
    }

    @Override
    public void setAttribute(HttpServletRequest request, Model model) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTransition(Model model,String token, String transition) {
    }

    @Override
    public void afterTransition(Model model,String token, String transition) {
    }

    @Override
    public String getEntity(){
        return "";
    }

    @Override
    public String getViewPath(){
        return "";
    }

    @Override
    public void createOrder(String action, Model model) {
        // TODO Auto-generated method stub

    }

    @Override
    public String sortParam(String params) {
        return params;
    }
}
