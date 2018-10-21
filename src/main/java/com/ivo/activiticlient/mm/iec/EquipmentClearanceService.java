package com.ivo.activiticlient.mm.iec;

import com.ivo.activiticlient.biz.BizService;
import com.ivo.activiticlient.restful.RestfulImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/13
 */
@Service
@Transactional
public class EquipmentClearanceService extends BizService {


    public void sendMail(String mail,String strMail){
        String strBiz = "提醒：进口设备清关采购确认";
        StringBuffer sb = new StringBuffer();
        sb.append("Dear Sir,<br><br>");
        sb.append("你的进口设备清关签核单有确认项即将Delay,为不影响设备报关进场,请及时确认<br><br>");
        sb.append(strMail);
        //mailService.sendHtmlMail("myIVO@ivo.com.cn", mail, strBiz, sb.toString());
    }

    /**
     * 计算两个日期之间的相差天数
     * @param date
     * @return
     */
    public int daysBetween(Date date){
        Date date1 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date);
        long time = cal.getTimeInMillis();
        long between_days=(time1-time)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 查询po信息
     * @param purchaseOrder:PO单号
     * @return
     */
    public String ConnectionSQL(String purchaseOrder){
        RestfulImpl restfulImpl = new RestfulImpl();
        String returnStr = restfulImpl.getPOForIEC(purchaseOrder);
        return returnStr;
    }
}
