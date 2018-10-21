package com.ivo.activiticlient.system.controller;

import com.ivo.activiticlient.biz.BizService;
import com.ivo.activiticlient.biz.HrService;
import com.ivo.activiticlient.common.util.DateUtil;
import com.ivo.activiticlient.common.util.StringUtil;
import com.ivo.activiticlient.common.util.Util;
import com.ivo.activiticlient.domain.Employee;
import com.ivo.activiticlient.restful.RestfulImpl;
import com.ivo.activiticlient.restful.RmiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Controller
public class MainController {

    @Autowired
    private BizService bizService;


    private RestfulImpl restfulImpl = new RestfulImpl();

    @Autowired
    private HrService hrService;

    public int withoutLoginCheck;

    @SuppressWarnings("unused")
    @RequestMapping("/home.do")
    public ModelAndView show(
            HttpServletRequest request, HttpSession session,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        String uid = (String)session.getAttribute("user_ID");
        if(StringUtil.isEmpty(uid)) {
            mv.setView(new RedirectView("./login.do"));
            return mv;

//            uid = "C1607908";
        }

        Employee objEmp = (Employee)bizService.findByID("Employee", "employee_ID", uid);

        mv.setViewName("index");
        session.setAttribute("employee", objEmp);

        boolean attspFlag = bizService.getAttspFlag(uid);
        mv.addObject("attspFlag", attspFlag);

        boolean attFlag = bizService.getAttFlag(uid);
        mv.addObject("attFlag", attFlag);

        String strEventTypes = "'5100','6100','7100'";
        String strEmpGroup_FK = objEmp.getEmpGroup().getEmpGroup_ID().trim();
        String strEmpGrade_FK = objEmp.getEmpGrade().getEmpGrade_ID();
        boolean foreignFlag = strEmpGroup_FK.equals("T") || strEmpGroup_FK.equals("J");
        if(strEmpGrade_FK.compareTo("G10") >= 0){
            strEventTypes = strEventTypes + ",'7930'";
        }
        if(!foreignFlag){
            strEventTypes = strEventTypes + ",'1100','2100'";
        }

        String monthOfThis = "";
        String strDate = Util.getNow();

        String day = strDate.split("-")[2].substring(0,2);
        Integer intDay = new Integer(day);
        int nYear = Integer.parseInt(strDate.split("-")[0]);
        int nMonth = Integer.parseInt(strDate.split("-")[1]);
        if (intDay > 26) {
            if(nMonth == 12) {
                nMonth = 1;
                nYear = nYear + 1;
            }else{
                nMonth = nMonth + 1;
            }
        }
        monthOfThis = nYear + "-" + nMonth + "-" + "01";

        List<RmiResult> executions = restfulImpl.getRecords(uid, 1, "HR", null);
        mv.addObject("executions", executions);
        mv.addObject("executionCount", executions == null ? 0 : executions.size());
        List<RmiResult> holds = restfulImpl.getRecords(uid, 2, "HR", null);
        mv.addObject("holds", holds);
        mv.addObject("holdCount", holds == null ? 0 : holds.size());

        List<RmiResult> historys = restfulImpl.getRecords(uid, 3, "HR", null);
        mv.addObject("historys", historys);

        List<RmiResult> agents = restfulImpl.getRecords(uid, 4, "HR", null);
        mv.addObject("agents", agents);
        mv.addObject("agentCount", agents == null ? 0 : agents.size());

        List<RmiResult> traces = restfulImpl.getRecords(uid, 5, "HR", null);
        mv.addObject("traces", traces);
        mv.addObject("traceCount", traces == null ? 0 : traces.size());


        /**以下代码是用来考勤资料的工号加密，到考勤系统进行解密，若匹配上，则可以访问考勤资料**/
        String strHrefAtt = "user_ID="+ uid +"&now="+ DateUtil.formatDate(new Date());
        strHrefAtt = Util.getUTF8HEX(strHrefAtt);
        /******END******/
        mv.addObject("url", strHrefAtt);
        return mv;
    }



}
