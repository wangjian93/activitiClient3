package com.ivo.activiticlient.system.service;

import com.ivo.activiticlient.domain.Gender;
import com.ivo.activiticlient.system.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Service
public class HelloService {

    @Autowired
    private GenderRepository genderRepository;

    public String hello() {

        List<Gender> list = genderRepository.list();

        return "HelloService";
    }
}
