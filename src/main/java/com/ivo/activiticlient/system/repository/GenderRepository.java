package com.ivo.activiticlient.system.repository;

import com.ivo.activiticlient.domain.Gender;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Repository
public class GenderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Gender> list() {
       return sessionFactory.getCurrentSession().createCriteria(Gender.class).list();
    }
}
