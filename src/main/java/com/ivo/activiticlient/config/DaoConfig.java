package com.ivo.activiticlient.config;

import com.ivo.activiticlient.common.dao.HibernateCaller;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Configuration
@EnableTransactionManagement
public class DaoConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan(new String[] {"com.ivo.activiticlient.*"});
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        props.setProperty("hibernate.show_sql", "false");
        props.setProperty("hibernate.format_sql", "false");
        props.setProperty("hibernate.use_sql_comments", "true");
        props.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");
        props.setProperty("hibernate.cache.use_query_cache", "false");
        props.setProperty("hibernate.cache.use_second_level_cache", "true");
        props.setProperty("current_session_context_class", "thread");

        bean.setHibernateProperties(props);
        return bean;
    }

    /**
     * 配置Hibernate事务管理器
     * @return
     */
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    /**
     * 配置事务异常封装
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public HibernateCaller hibernateCaller(SessionFactory sessionFactory) {
        HibernateCaller hibernateCaller = new HibernateCaller();
        hibernateCaller.setSessionFactory(sessionFactory);
        return hibernateCaller;
    }
}
