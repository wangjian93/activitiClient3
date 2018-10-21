package com.ivo.activiticlient.mm.iec;

import com.ivo.activiticlient.biz.Extractor;
import com.ivo.activiticlient.biz.Model;
import com.ivo.activiticlient.biz.SimpleOrderBehavior;
import com.ivo.activiticlient.common.util.StringUtil;
import com.ivo.activiticlient.domain.Employee;
import com.ivo.activiticlient.restful.RestfulImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangjian
 * @date 2018/10/13
 */
@Controller(value = "iecController")
@RequestMapping("/IEC.do")
public class EquipmentClearanceController extends SimpleOrderBehavior {

    @Autowired
    private EquipmentClearanceService equipmentClearanceService;

    private RestfulImpl restfulImpl = new RestfulImpl();

    @Override
    public void extractModel(Extractor extractor, Model model) {
        String user_ID = (String)extractor.getRequest().getSession().getAttribute("user_ID");
        String node = restfulImpl.getToken(user_ID, model.getOrderNumber());
        EquipmentClearance equipmentClearance = (EquipmentClearance) model;
        if(node.equals("S020")){
            //poInformationItem
            List<PoInformationItem> poInformationItem = new ArrayList<PoInformationItem>();
            for(int i=0; i<Integer.parseInt( extractor.getString("LastRow")); i++){
                PoInformationItem item = new PoInformationItem();
                item.setItemLine(extractor.getString("itemLine"+i));
                item.setMaterial(extractor.getString("material"+i));
                item.setMaterialDescription(extractor.getString("materialDescription"+i));
                item.setStorageLocation(extractor.getString("storageLocation"+i));
                item.setOrderQty(extractor.getString("orderQty"+i));
                item.setMeasureUnit(extractor.getString("measureUnit"+i));
                item.setEquipment(extractor.getString("equipment"+i));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfDelivery = null;
                if(extractor.getString("dateOfDelivery"+i)==null || !extractor.getString("dateOfDelivery"+i).equals("")){
                    try {
                        dateOfDelivery = dateFormat.parse(extractor.getString("dateOfDelivery"+i));  //po日期
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                item.setDateOfDelivery(dateOfDelivery);
                poInformationItem.add(item);
            }
            //poInformation
            PoInformation poInformation = new PoInformation();
            poInformation.setPurchaseOrder(extractor.getString("purchaseOrder"));
            poInformation.setVendor(extractor.getString("vendor"));
            poInformation.setVendorContact(extractor.getString("vendorContact"));
            poInformation.setVendorFax(extractor.getString("vendorFax"));
            poInformation.setZipCode(extractor.getString("zipCode"));
            poInformation.setVendorContactExt(extractor.getString("vendorContactExt"));
            poInformation.setAddress(extractor.getString("address"));
            poInformation.setDeliveryPlace(extractor.getString("deliveryPlace"));
            poInformation.setDeliveryTerm(extractor.getString("deliveryTerm"));
            poInformation.setGoodsReceiveContact(extractor.getString("goodsReceiveContact"));
            poInformation.setWarrantyTerm(extractor.getString("warrantyTerm"));
            poInformation.setContactExt(extractor.getString("contactExt"));
            poInformation.setRequisitioner(extractor.getString("requisitioner"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfOrder = null;
            if(extractor.getString("dateOfOrder")==null || !extractor.getString("dateOfOrder").equals("")){
                try {
                    dateOfOrder = dateFormat.parse(extractor.getString("dateOfOrder"));  //po日期
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            poInformation.setDateOfOrder(dateOfOrder);
            poInformation.setPoInformationItem(poInformationItem);
            //取参数
            String logiticsConfirm = "0";
            String erConfirm = "0";
            String purConfirm1 = "0";
            String purConfirm2 = "0";
            String purConfirm3 = "0";
            String purConfirm4 = "0";
            String purConfirm5 = "0";
            String purConfirm6 = "0";
            String purConfirm7 = "0";
            String purConfirm8 = "0";
            String purConfirm9 = "0";
            String answer1 = extractor.getString("answer1");
            String answer2 = extractor.getString("answer2");
            String answer3 = extractor.getString("answer3");
            String answer4 = extractor.getString("answer4");
            String answer5 = extractor.getString("answer5");
            String answer6 = extractor.getString("answer6");
            String answer7 = extractor.getString("answer7");
            String answer8 = extractor.getString("answer8");
            String answer9 = extractor.getString("answer9");
            String answer10 = extractor.getString("answer10");
            String answer11 = extractor.getString("answer11");
            String answer12 = extractor.getString("answer12");
            //保存数据库
            equipmentClearance.setLogiticsConfirm(logiticsConfirm);
            equipmentClearance.setERConfirm(erConfirm);
            equipmentClearance.setPurConfirm1(purConfirm1);
            equipmentClearance.setPurConfirm2(purConfirm2);
            equipmentClearance.setPurConfirm3(purConfirm3);
            equipmentClearance.setPurConfirm4(purConfirm4);
            equipmentClearance.setPurConfirm5(purConfirm5);
            equipmentClearance.setPurConfirm6(purConfirm6);
            equipmentClearance.setPurConfirm7(purConfirm7);
            equipmentClearance.setPurConfirm8(purConfirm8);
            equipmentClearance.setPurConfirm9(purConfirm9);
            equipmentClearance.setAnswer1(answer1);
            equipmentClearance.setAnswer2(answer2);
            equipmentClearance.setAnswer3(answer3);
            equipmentClearance.setAnswer4(answer4);
            equipmentClearance.setAnswer5(answer5);
            equipmentClearance.setAnswer6(answer6);
            equipmentClearance.setAnswer7(answer7);
            equipmentClearance.setAnswer8(answer8);
            equipmentClearance.setAnswer9(answer9);
            equipmentClearance.setAnswer10(answer10);
            equipmentClearance.setAnswer11(answer11);
            equipmentClearance.setAnswer12(answer12);
            equipmentClearance.setPoInformation(poInformation);

            equipmentClearanceService.save(poInformation);
            for(int i=0; i<poInformationItem.size(); i++ ){
                equipmentClearanceService.save(poInformationItem.get(i));
            }
            equipmentClearanceService.save(equipmentClearance);
        }else if(node.equals("IEC100")){
            equipmentClearance.setLogiticsConfirm(extractor.getString("logiticsConfirm"));
            equipmentClearance.setAnswer1(extractor.getString("answer1"));
            equipmentClearanceService.save(equipmentClearance);
        }else if(node.equals("IEC200")){
            equipmentClearance.setERConfirm(extractor.getString("erConfirm")==null?"0":extractor.getString("erConfirm"));
            equipmentClearance.setAnswer3(extractor.getString("answer3"));
            equipmentClearanceService.save(equipmentClearance);
        }else if(node.equals("IEC300")){
            equipmentClearance.setPurConfirm1(extractor.getString("purConfirm1")==null?"0":extractor.getString("purConfirm1"));
            equipmentClearance.setPurConfirm2(extractor.getString("purConfirm2")==null?"0":extractor.getString("purConfirm2"));
            equipmentClearance.setPurConfirm3(extractor.getString("purConfirm3")==null?"0":extractor.getString("purConfirm3"));
            equipmentClearance.setPurConfirm4(extractor.getString("purConfirm4")==null?"0":extractor.getString("purConfirm4"));
            equipmentClearance.setPurConfirm5(extractor.getString("purConfirm5")==null?"0":extractor.getString("purConfirm5"));
            equipmentClearance.setPurConfirm6(extractor.getString("purConfirm6")==null?"0":extractor.getString("purConfirm6"));
            equipmentClearance.setPurConfirm7(extractor.getString("purConfirm7")==null?"0":extractor.getString("purConfirm7"));
            equipmentClearance.setPurConfirm8(extractor.getString("purConfirm8")==null?"0":extractor.getString("purConfirm8"));
            equipmentClearance.setPurConfirm9(extractor.getString("purConfirm9")==null?"0":extractor.getString("purConfirm9"));
            equipmentClearance.setAnswer4(extractor.getString("answer4"));
            equipmentClearance.setAnswer5(extractor.getString("answer5"));
            equipmentClearance.setAnswer6(extractor.getString("answer6"));
            equipmentClearance.setAnswer7(extractor.getString("answer7"));
            equipmentClearance.setAnswer8(extractor.getString("answer8"));
            equipmentClearance.setAnswer9(extractor.getString("answer9"));
            equipmentClearance.setAnswer10(extractor.getString("answer10"));
            equipmentClearance.setAnswer11(extractor.getString("answer11"));
            equipmentClearance.setAnswer12(extractor.getString("answer12"));
            //equipmentClearance.setPurConfirm8("purConfirm9");
            //equipmentClearance.setAnswer11(extractor.getString("answer12"));
            equipmentClearanceService.save(equipmentClearance);
        }

    }

    @Override
    public Map<String, Object> prepareVariables(Model model,
                                                String transition, String sid, String token) {
        Map<String, Object> map = super.prepareVariables(model, transition, sid, token);
        EquipmentClearance equipmentClearance = (EquipmentClearance) model;

        if (StringUtil.isEqual(model.getStatus(), "020") && transition.equals("Submit")) {

            String requisitioner = equipmentClearance.getPoInformation().getRequisitioner();
            Employee employee = equipmentClearanceService.getEmp(requisitioner);
            String handler = "";
            if(employee == null){
                employee = equipmentClearance.getDrafter();
            }
            handler = employee.getDepartment().getDepartment_ID() + "/" + employee.getEmployee_ID();
            map.put("ERconfirm", handler);
            map.put("reviewLevel", 7);
        }else if("IEC100".equals(token) && transition.equals("Submit")) {
            boolean pmFlag = false;
            if(StringUtil.isEqual(equipmentClearance.getLogiticsConfirm(), "1")){
                pmFlag =  true;
            }
            map.put("pmFlag", pmFlag);
            map.put("reviewLevel", 7);
        }else if ("IEC200".equals(token) && transition.equals("Submit")) {
            map.put("reviewLevel", 6);
        }else if ("IEC400".equals(token) && transition.equals("Submit")) {
            boolean kmFlag = false;
            if(StringUtil.isEqual(equipmentClearance.getPurConfirm1(), "1")&&
                    StringUtil.isEqual(equipmentClearance.getPurConfirm2(), "1")&&
                    StringUtil.isEqual(equipmentClearance.getPurConfirm3(), "1")&&
                    StringUtil.isEqual(equipmentClearance.getPurConfirm4(), "1")&&
                    StringUtil.isEqual(equipmentClearance.getPurConfirm5(), "1")&&
                    StringUtil.isEqual(equipmentClearance.getPurConfirm6(), "1")&&
                    StringUtil.isEqual(equipmentClearance.getPurConfirm7(), "1")&&
                    StringUtil.isEqual(equipmentClearance.getPurConfirm8(), "1")&&
                    StringUtil.isEqual(equipmentClearance.getPurConfirm9(), "1")) {
                kmFlag = true;
            }
            map.put("kmFlag", kmFlag);
        }
        return map;
    }

    @Override
    public void setAttribute(HttpServletRequest request, Model model) {
        RestfulImpl restfulImpl  = new RestfulImpl();
        Extractor extractor = new Extractor(request);
        String user_ID = (String)extractor.getRequest().getSession().getAttribute("user_ID");
        String node = restfulImpl.getToken(user_ID, model.getOrderNumber());
        boolean draftflag = false;
        boolean logiticsflag = false;
        boolean epflag = false;
        boolean erflag = false;
        if(StringUtil.isEqual(model.getStatus(), "020")){
            draftflag = true;
        }else if("IEC100".equals(node)){
            logiticsflag = true;
        }else if("IEC200".equals(node)){
            epflag = true;
        }else if("IEC300".equals(node)){
            erflag = true;
        }
        request.setAttribute("draftflag", draftflag);
        request.setAttribute("epflag", epflag);
        request.setAttribute("erflag", erflag);
        request.setAttribute("logiticsflag", logiticsflag);

    }

    @Override
    public String getEntity() {
        return "com.ivo.activiticlient.mm.iec.EquipmentClearance";
    }

    @Override
    public String getViewPath() {
        return "mm/iec/EquipmentClearance";
    }

    /**
     * 通过ajax实现页面传如po单号异步获取po信息
     */
    @RequestMapping(params = "m=getPoInformation")
    public void getPoInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String purchaseOrder = request.getParameter("purchaseOrder");
        String poJson = equipmentClearanceService.ConnectionSQL(purchaseOrder);
        PrintWriter out = response.getWriter();
        out.print(poJson);
        out.close();
    }
}
