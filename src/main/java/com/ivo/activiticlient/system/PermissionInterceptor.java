package com.ivo.activiticlient.system;

import com.ivo.activiticlient.biz.HrService;
import com.ivo.activiticlient.common.util.BizUtil;
import com.ivo.activiticlient.common.util.HttpRequestDeviceUtil;
import com.ivo.activiticlient.domain.Employee;
import com.ivo.activiticlient.restful.RestfulImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wangjian
 * @date 2018/10/13
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private RestfulImpl restfulImpl = new RestfulImpl();
    @Autowired
    private HrService hrService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String orderNumber = request.getParameter("orderNumber");
        boolean ismobile = HttpRequestDeviceUtil.isMobileDevice(request);
        if (ismobile) return true;
        String uid = ((Employee) (request.getSession()
                .getAttribute("user"))).getEmployee_ID();
        if (restfulImpl.permissionCheck(orderNumber, uid))
            return true;

        //权限
        String strSid = BizUtil.getSource(orderNumber);
        List<?> authoritys = hrService.getDao().sqlQuery("SELECT Count(*) nCount FROM AT_R_Authority " +
                "WHERE ValidFlag = 1 AND Employee_FK = '"+uid+"' " +
                "AND (Sid = '"+strSid+"' OR Sid = '')");
        if(authoritys != null && authoritys.size() > 0){
            Map<?, ?> map = (Map<?, ?>)authoritys.get(0);
            int nCount = (Integer)map.get("nCount");
            if(nCount > 0)
                return true;
        }
        if ("MPI".equals(strSid)) {//应用户要求，新产品实验转量产申请单不做验证卡控 by_C1610011
            return true;
        }

        request.setAttribute("error", "你没有足够的权限打开或创建此签核单！");
        request.getRequestDispatcher("pages/common/error.jsp").forward(
                request, response);
        return false;
    }
}
