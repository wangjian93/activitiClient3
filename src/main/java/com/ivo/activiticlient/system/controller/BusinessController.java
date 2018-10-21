package com.ivo.activiticlient.system.controller;

import com.ivo.activiticlient.biz.BizService;
import com.ivo.activiticlient.biz.Extractor;
import com.ivo.activiticlient.biz.Model;
import com.ivo.activiticlient.biz.OrderBehavior;
import com.ivo.activiticlient.common.util.*;
import com.ivo.activiticlient.domain.Department;
import com.ivo.activiticlient.domain.Employee;
import com.ivo.activiticlient.restful.RestfulImpl;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author wangjian
 * @date 2018/10/13
 */
@Controller
public class BusinessController {

    private Map<Integer, String> map;
    {
        map = new HashMap<Integer, String>();
        map.put(0, "Submit");
        map.put(1, "Reject");
        map.put(2, "HandIn");
        //3交办
        map.put(3, "HandOver");
        map.put(4, "Recosign");
        map.put(5, "Hold");
        map.put(6, "Void");
        map.put(7, "Save");
        map.put(9, "Recall");
    }


    private RestfulImpl restfulImpl = new RestfulImpl();

    @Autowired
    private ListableBeanFactory beanFactory;

    @Autowired
    private BizService bizService;


    @RequestMapping("/show.do")
    public ModelAndView show(@RequestParam("orderNumber") String orderNumber,
                             @ModelAttribute("error") String error,
                             HttpServletRequest request, HttpSession session,
                             HttpServletResponse response) throws Exception {

        response.setCharacterEncoding("UTF-8");
        Employee user = bizService.getEmp((String)session.getAttribute("user_ID"));
        ModelAndView mv = new ModelAndView();

        Department group = user.getDepartment();
        String strSid = BizUtil.getSource(orderNumber);

        String token = restfulImpl.getToken(user.getEmployee_ID(),orderNumber);


        OrderBehavior behavior = getOrderBehavior(strSid);
        if (behavior == null) {
            mv.setViewName("common/error");
            return mv.addObject("error", "你请求的URL不合法！");
        }

        String isNewOrder = "old";
        Model model = getModel(behavior.getEntity(), orderNumber);
        if (model == null) {
            token = "";
            model = createModel(behavior.getEntity());
            model.setOrderNumber(orderNumber);
            model.setDrafter(user);
            model.setDraftGroup(group);
            isNewOrder = "new";
        }

        mv = new ModelAndView(behavior.getViewPath());
        boolean ismobile = HttpRequestDeviceUtil.isMobileDevice(request);
        if(ismobile){
            if(strSid.equals("DA") && model.getStatus().equals("020")){
                mv.setViewName("android/GAAssetsRepair");
            }else if(strSid.equals("VB") && model.getStatus().equals("020")){
                mv.setViewName("android/VisitBook");
            }
        }

        Map<String, Object> attributes = behavior.addAttributes(model);
        if (attributes != null) mv.addAllObjects(attributes);
        behavior.setAttribute(request,model);

        boolean bReadOnly = false;
        boolean importFlag = false;
        boolean isCanAttach = false;
        String canAttach = "";
        try{
            if(strSid!=null&&token!=null&&!"".equals(token)){
                canAttach = restfulImpl.ISAttach(strSid,token);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        if("true".equals(canAttach)){
            isCanAttach = true;
        }
        if((!model.getStatus().equals("020") ||
                token == null)&&!isCanAttach) bReadOnly = true;
        if (model.getStatus().equals("020") && token !=null) importFlag = true;

        mv.addObject("uid",user.getEmployee_ID());
        mv.addObject("token", token);
        mv.addObject("isNewOrder", isNewOrder);
        mv.addObject("model", model);
        mv.addObject("bReadOnly", bReadOnly);
        mv.addObject("error", error);
        mv.addObject("importFlag",importFlag);
        //long d1 = System.currentTimeMillis();
        mv.addObject("items", restfulImpl.getActions(orderNumber, user.getEmployee_ID()));
        //long d2 = System.currentTimeMillis();

        mv.addObject("records", restfulImpl.getRecords(orderNumber));

        //System.out.println(d2-d1);
        return mv;
    }

    private Model createModel(String name) throws Exception {
        try {
            return (Model) Thread.currentThread().getContextClassLoader()
                    .loadClass(name).newInstance();
        } catch (Exception e) {
            throw e;
        }
    }

    private Model getModel(String name, String orderNumber) {
        List<?> list = bizService.find("from " + name
                + " x where x.orderNumber=? and x.validFlag=1", orderNumber);
        if (list.size() > 0)
            return (Model) list.get(0);
        return null;
    }

    @RequestMapping("/submit.do")
    @Transactional
    public ModelAndView submit(@RequestParam("orderNumber") String orderNumber,
                               @RequestParam("opcode") int opcode, HttpServletRequest request,
                               HttpSession session, RedirectAttributes redirectAttributes
    ) throws Exception {
        String transition = map.get(opcode);
        Employee user = bizService.getEmp((String)session.getAttribute("user_ID"));
        Department group = user.getDepartment();
        String memo = request.getParameter("memo");
        String strSid = BizUtil.getSource(orderNumber);
        ModelAndView mv = new ModelAndView();
        OrderBehavior behavior = getOrderBehavior(strSid);
        if (behavior == null) {
            mv.setViewName("common/error");
            return mv.addObject("error", "你请求的URL不合法！");
        }

        Model model = getModel(behavior.getEntity(), orderNumber);

        if (model == null) {
            if(transition.equals("Void")){
                return new ModelAndView(new RedirectView("pages/common/proxy.jsp"));
            }else{
                model = createModel(behavior.getEntity());
                model.setOrderNumber(orderNumber);
                model.setDrafter(user);
                model.setDraftGroup(group);
                restfulImpl.create(user.getEmployee_ID(),user.getDepartment().getDepartment_ID(),orderNumber);
            }
        }
        model.setMemo(memo);
        String status = model.getStatus();

        String token = restfulImpl.getToken(user.getEmployee_ID(),orderNumber);
        request.setAttribute("token", token);
        behavior.extractModel(new Extractor(request), model);

        if(status.equals(RestfulImpl.Signing) && transition.equals("Void")){
            mv.setViewName("common/error");
            return mv.addObject("error", "此操作不合法！");
        }

        if(status.equals(RestfulImpl.Drafting) && (
                transition.equals("Reject") || transition.equals("HandIn")
                        || transition.equals("Recosign") || transition.equals("Hold")
        )){
            mv.setViewName("common/error");
            return mv.addObject("error", "此操作不合法！");
        }

        String error = behavior.validate(model,transition);

        Log logValidate = LogFactory.getLog("Validate");
        logValidate.info(model.getOrderNumber()+" Validate:"+error+" "+DateUtil.formatDateTime(new Date()));

        if (StringUtil.isNotEmpty(error)) {
            redirectAttributes.addFlashAttribute("error", error);

            return new ModelAndView(new RedirectView("show.do?orderNumber="
                    + model.getOrderNumber()));
        }

        if(status.equals("020") &&
                (transition.equals("Submit") || transition.equals("Save"))){
            restfulImpl.setMemoToMyivodb(orderNumber,memo);
        }

        if (transition.equals("Submit") ||
                transition.equals("Reject") || transition.equals("Void")
                || transition.equals("Recall")
                ){
            String transition_ = transition;
            if(transition_.equals("Recall"))
                transition_ = "Reject";
            behavior.beforeTransition(model,token, transition_);
        }

        if (!transition.equals("Save")) {
            String s = request.getParameter("userList");
            List<String> uids = null;
            if (s != null && !s.equals("")) {
                uids = new ArrayList<String>();
                String[] temp = s.split(",");
                for (String x : temp) {
                    if (!x.equals("") && !uids.contains(x))
                        uids.add(x);
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            bizService.save(model);
            map.putAll(behavior.prepareVariables(model,transition,strSid,token));

            bizService.getDao().clear();
            model = getModel(behavior.getEntity(), orderNumber);

            status = restfulImpl.signal(orderNumber,user.getEmployee_ID(),user.getDepartment().getDepartment_ID(),uids,
                    request.getParameter("comment"),opcode, map);
            model.setStatus(status);
            //System.out.println(model.getOrderNumber()+":"+status);
            if (status.equals(RestfulImpl.Finished)
                    || status.equals(RestfulImpl.Voided)){
                model.setEndDate(new Date());
                Log logger = LogFactory.getLog("EndDate");
                logger.info(model.getOrderNumber()+":"+status+" "+DateUtil.formatDateTime(new Date()));
            }
        }
        bizService.save(model);
        boolean ismobile = HttpRequestDeviceUtil.isMobileDevice(request);
        if(ismobile){
            String qrcode = request.getParameter("qrcode");
            Map<String,String> map= new HashMap<String,String>();
            map.put("orderNumber", orderNumber);
            map.put("transition",transition);
            map.put("status",status);
            map.put("uid",user.getEmployee_ID());
            JSONObject json = JSONObject.fromObject(map);

            String url = "http://myivo.ivo.com.cn/Myivo2/appAction.do";
            NameValuePairUtil NameValuePairList = new NameValuePairUtil();
            NameValuePairList.add("data", json.toString());
            HttpRequestUtil.postHttpAsyncClient(url,NameValuePairList.getList());

            if(qrcode!=null && qrcode.equals("1") && strSid.equals("DA") && transition.equals("Submit"))
                return new ModelAndView(new RedirectView("pages/android/success.jsp"));
        }
        if (transition.equals("Submit") || transition.equals("Recall") ||
                transition.equals("Reject") || transition.equals("Void")){
            String transition_ = transition;
            if(transition_.equals("Recall"))
                transition_ = "Reject";
            behavior.afterTransition(model,token, transition_);
        }

        if (transition.equals("Save")) {
            return new ModelAndView(new RedirectView("show.do?orderNumber=" + model.getOrderNumber()));
        } else {
            return new ModelAndView(new RedirectView("pages/common/proxy.jsp"));
        }
    }

    @RequestMapping("/batchShow.do")
    @Transactional
    public ModelAndView batchSubmit(@RequestParam("orders") String orders,HttpSession session,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        String param = new String(Base64.decodeBase64(request.getParameter("orders")));

        String strSid = BizUtil.getSource(param.split(",")[0]);
        OrderBehavior behavior = getOrderBehavior(strSid);

        param = behavior.sortParam(param);

        String[] list = param.split(",");
        List<Object> lists = new ArrayList<Object>();
        for(int i = 0; i < list.length; i++ ){
            String orderNumber = list[i];
            Model model = getModel(behavior.getEntity(), orderNumber);
            lists.add(model);
        }

        ModelAndView mv = new ModelAndView(behavior.getViewPath()+"Batch");
        mv.addObject("list", lists);
        return mv;
    }

    @RequestMapping("/batchSubmit.do")
    @Transactional
    public ModelAndView batchSubmit(@RequestParam("nCount") int nCount,
                                    @RequestParam("entity") String entity, HttpSession session,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Employee user = (Employee)session.getAttribute("user");
        for(int i = 0; i < nCount; i++){
            String orderNumber = request.getParameter("orderNumber_"+i);
            String comment = request.getParameter("comment_"+i);
            String opcode = request.getParameter("radio_"+i);
            String transition = map.get(Integer.parseInt(opcode));

            Map<String, Object> map_ = new HashMap<String, Object>();
            String s = request.getParameter("userList");
            List<String> uids = null;
            if (s != null && !s.equals("")) {
                uids = new ArrayList<String>();
                String[] temp = s.split(",");
                for (String x : temp) {
                    if (!x.equals("") && !uids.contains(x))
                        uids.add(x);
                }
            }
            Model model = getModel(entity,orderNumber);
            String strSid = BizUtil.getSource(orderNumber);
            OrderBehavior behavior = getOrderBehavior(strSid);

            String status = restfulImpl.signal(orderNumber,user.getEmployee_ID(),user.getDepartment().getDepartment_ID(),uids,
                    comment,Integer.parseInt(opcode), map_);

            model.setStatus(status);
            System.out.println(model.getOrderNumber()+":"+status);
            if (status.equals(RestfulImpl.Finished)
                    || status.equals(RestfulImpl.Voided)){
                model.setEndDate(new Date());
                Log logger = LogFactory.getLog("EndDate");
                logger.info(model.getOrderNumber()+":"+status+" "+DateUtil.formatDateTime(new Date()));
            }


            bizService.save(model);

            if (transition.equals("Submit") ||
                    transition.equals("Reject"))
                behavior.afterTransition(model,"", transition);

            boolean ismobile = HttpRequestDeviceUtil.isMobileDevice(request);
            if(ismobile){
                Map<String,String> map= new HashMap<String,String>();
                map.put("orderNumber", orderNumber);
                map.put("transition",transition);
                map.put("status",status);
                map.put("uid",user.getEmployee_ID());
                JSONObject json = JSONObject.fromObject(map);

                String url = "http://myivo.ivo.com.cn/Myivo2/appAction.do";
                NameValuePairUtil NameValuePairList = new NameValuePairUtil();
                NameValuePairList.add("data", json.toString());
                HttpRequestUtil.postHttpAsyncClient(url,NameValuePairList.getList());
            }
        }
        return new ModelAndView(new RedirectView("pages/common/proxy.jsp"));
    }

    private OrderBehavior getOrderBehavior(String sid) {
        try {
            return beanFactory.getBean(sid.toLowerCase() + "Controller",
                    OrderBehavior.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @RequestMapping("/importItem.do")
//    public void importItem(@RequestParam("orderNumber") String postRisk,
//                           @RequestParam("preRisk") String preRisk,
//                           HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setCharacterEncoding("UTF-8");
//        String hql ="from RiskAssessment where orderNumber = '"+postRisk+"' and validFlag = 1";
//        RiskAssessment postRiskAssessment = (RiskAssessment) bizService.findByhql(hql);
//        if (postRiskAssessment == null){
//            response.getWriter().print("请先保存再导入");
//        } else{
//            String prehql ="from RiskAssessment where orderNumber = '"+preRisk+"' and validFlag = 1";
//            RiskAssessment preRiskAssessment = (RiskAssessment) bizService.findByhql(prehql);
//            List<RiskAssessmentItem> preItemList = preRiskAssessment.getItems();
//            for (RiskAssessmentItem preItem : preItemList) {
//                RiskAssessmentItem item = new RiskAssessmentItem();
//                item.setRiskAssessments(postRiskAssessment);
//                item.setProcessName(preItem.getProcessName());
//                item.setMaterialTypeRA(preItem.getMaterialTypeRA());
//                item.setMaterialNameRA(preItem.getMaterialNameRA());
//                item.setPurposeOfuse(preItem.getPurposeOfuse());
//                item.setStatusOfuse(preItem.getStatusOfuse());
//                item.setMaterialID(preItem.getMaterialID());
//                item.setMaterialType(preItem.getMaterialType());
//                item.setVendor(preItem.getVendor());
//                item.setRiskAssessment(preItem.getRiskAssessment());
//                item.setIsNeedDoc(preItem.getIsNeedDoc());
//                item.setTestReportID(preItem.getTestReportID());
//                item.setExpirationTime(preItem.getExpirationTime());
//                item.setProductID(preItem.getProductID());
//                item.setRemarks(preItem.getRemarks());
//                bizService.save(item);
//            }
//            //postRa.addAll(riskAssessmentItem);
//            //riskAssessment.setItems(raiList);
//
//        }
//    }
}
