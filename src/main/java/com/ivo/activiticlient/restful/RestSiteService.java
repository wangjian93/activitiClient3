package com.ivo.activiticlient.restful;

import com.ivo.activiticlient.biz.BizService;
import com.ivo.activiticlient.common.dao.HibernateCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/12
 */
@Service
public class RestSiteService extends BizService {

    @Autowired
    HibernateCaller hibernateCaller;

    @SuppressWarnings("unchecked")
    public String getRestSiteInfo(String systemName){
        List<RestSite> list = findAllByConditon("RestSite", " ID = '"+systemName+"' AND ValidFlag = '1' ");
        if (list.size() <= 0) {
            return null;
        }else{
            return list.get(0).getRestHost();
        }
    }
}
