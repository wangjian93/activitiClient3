package com.ivo.activiticlient.system.controller;

import com.ivo.activiticlient.biz.BizService;
import com.ivo.activiticlient.common.util.StringUtil;
import com.ivo.activiticlient.domain.Employee;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Controller
public class LoginController
{
    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    private BizService bizService;
    public int withoutLoginCheck;
    //private String host = "http://10.20.46.106:8081/Myivo2/";
    private String host = "http://10.20.46.54:8080/Myivo2/";

    @RequestMapping({"/login.do"})
    public ModelAndView loginAction(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ParseException, IOException
    {
        ModelAndView mv= new ModelAndView();
        String url = request.getParameter("url");
        String attribute = (String)session.getAttribute("user_ID");
        if (StringUtil.isNotEmpty(attribute)) {
            mv.setView(new RedirectView(url==null?"home.do":new String(Base64.decodeBase64(url))));
            return mv;
            //session.setAttribute("user_ID", attribute);
            //Employee objEmployee = (Employee)bizService.findByID("Employee", "employee_ID",  attribute);
            //session.setAttribute("user", objEmployee);
        }

        mv.setView(new RedirectView(host+"business.do?site=Client&action=nosession"));

        if(url!=null)
            mv.addObject("url",url);
        return mv;
    }

    @RequestMapping({"/logout.do"})
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView(new RedirectView(this.host + "logout.do"));
    }

    @RequestMapping({"/create.do"})
    public ModelAndView create(@RequestParam("user") String uid, @RequestParam("url") String url, HttpSession session) {
        Employee objEmployee = (Employee)this.bizService.findByID("Employee", "employee_ID", uid.toUpperCase());

        if ((objEmployee != null) && (objEmployee.getValidFlag().intValue() == 1)) {
            String attribute = (String)session.getAttribute("user_ID");

            if ((attribute == null) || (!attribute.equals(objEmployee.getEmployee_ID())))
            {
                session.setAttribute("user", objEmployee);
                session.setAttribute("user_ID", objEmployee.getEmployee_ID());
                System.out.println(objEmployee.getDepartment().getDepartmentName());
                session.setAttribute("userName", objEmployee.getEmployeeName());
                session.setAttribute("department", objEmployee.getDepartment());
                session.setAttribute("group", objEmployee.getDepartment());
            }
            return new ModelAndView(new RedirectView(new String(Base64.decodeBase64(url))));
        }return new ModelAndView("error").addObject("error", "Employee is null!");
    }
}
