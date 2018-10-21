package com.ivo.activiticlient;

import com.ivo.activiticlient.biz.BizService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangjian
 * @date 2018/10/11
 */
public class BizServiceTest extends ApplicationInitializerTest {

    @Autowired
    private BizService bizService;

    @Test
    public void test() {
        System.out.println(bizService.getLeader("C1607908"));
    }
}
