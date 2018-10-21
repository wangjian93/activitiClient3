package com.ivo.activiticlient;

import com.ivo.activiticlient.config.RootConfig;
import com.ivo.activiticlient.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class})
public class ApplicationInitializerTest {

    @Test
    public void contextLoads() {
    }
}
