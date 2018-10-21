package com.ivo.activiticlient.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

/**
 * web初始化，替代xml配置
 * Servlet版本3.0以上
 * @author wangjian
 * @date 2018/10/10
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 配置ContextLoaderListener
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    /**
     * 配置DispatcherServlet
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    /**
     * 配置ServletMapping
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    /**
     * druid监控配置添加StatViewServlet
     * 配置WebStatFilter，Web关联监控
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        ServletRegistration.Dynamic druidServlet = servletContext.addServlet("statViewServlet", StatViewServlet.class);
        druidServlet.addMapping("/druid/*");
        druidServlet.setInitParameter("loginUsername", "druid");
        druidServlet.setInitParameter("loginPassword", "druid");


        FilterRegistration.Dynamic druidFilter = servletContext.addFilter("webStatFilter", WebStatFilter.class);
        druidFilter.addMappingForUrlPatterns(null, false, "/*");
        druidFilter.setInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        druidFilter.setInitParameter("profileEnable", "true");
    }

    @Override
    protected Filter[] getServletFilters() {

        // 统一设置MVC编码格式
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        // 通过OpenSessionInViewFilter解决hibernate懒加载问题
        OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();
        return new Filter[] { characterEncodingFilter, openSessionInViewFilter };
    }

}
