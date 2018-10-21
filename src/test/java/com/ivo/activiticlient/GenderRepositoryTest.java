package com.ivo.activiticlient;

import com.ivo.activiticlient.common.dao.HibernateCaller;
import com.ivo.activiticlient.domain.Gender;
import com.ivo.activiticlient.system.repository.GenderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/11
 */
public class GenderRepositoryTest extends ApplicationInitializerTest {

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private HibernateCaller hibernateCaller;

    @Test
    public void test() {
        List<Gender> list = genderRepository.list();
        System.out.println(list.size());
    }

    @Test
    public void test2() {
        hibernateCaller.find("from Gender");
    }
}
