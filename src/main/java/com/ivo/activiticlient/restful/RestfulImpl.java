package com.ivo.activiticlient.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ivo.activiticlient.common.util.HttpConnectionManager;
import com.ivo.activiticlient.common.util.SpringContextUtil;
import com.ivo.activiticlient.common.util.StringUtil;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class RestfulImpl {
    private static RestSiteService restSiteService = (RestSiteService)SpringContextUtil.getBean("restSiteService");

    //	private static String arcurl = "http://10.20.48.146:8080/Arcadia/";
    private static String arcurl = restSiteService.getRestSiteInfo("Arcadia");
    private static String url = "http://10.20.46.54:8080/Myivo2/bpm/"; //restSiteService.getRestSiteInfo("Myivo2");
    private static String qmsurl = restSiteService.getRestSiteInfo("QMS");
    private static String hrurl = restSiteService.getRestSiteInfo("HR");
    public static String gsmurl = restSiteService.getRestSiteInfo("GSM");
    public static String hisurl = restSiteService.getRestSiteInfo("data-service");
    public static String orgurl = restSiteService.getRestSiteInfo("ORG");
    public static RestTemplate restTemplate;

    int EXECUTION = 1;

    int HOLD = 2;

    int HISTORY = 3;

    int AGENT = 4;

    int TRACE = 5;
    static {
        createRestTemplate();
    }

    public static RestTemplate getRestTemplate(){
        return restTemplate;
    }

    private static void createRestTemplate() {
        restTemplate = new RestTemplate();
        //		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();//消息体转换器列表
        //		MarshallingHttpMessageConverter marshalConverter = new MarshallingHttpMessageConverter();//xom类型的消息体转换器
        //		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();//创建JAXB2类型的xom环境
        //		marshaller.setClassesToBeBound(Student.class,StudentList.class);//将类绑定到JAXB2
        //		marshalConverter.setMarshaller(marshaller);//设置编组器
        //		marshalConverter.setUnmarshaller(marshaller);//设置解组器
        //		converters.add(marshalConverter);//将xom消息体转换器添加到列表
        //		converters.add(new StringHttpMessageConverter());
        //		restTemplate.setMessageConverters(converters);//将转换器列表放入RestTemplate
    }


    public static final String Drafting = "020";

    public static final String Voided = "990";

    public static final String Signing = "100";

    public static final String Finished = "980";


    /**
     * 获取单号
     *
     * */
    public String getTrackingNumber(String source_ID){
        String result =restTemplate.getForObject(url+"getTrackingNumber/{source_ID}", String.class,source_ID);
        return result;
    };

    /**
     * 获取Ful单号
     *
     * */
    public String getTrackingNumberFul(String source_ID){
        String result =restTemplate.getForObject(url+"getTrackingNumber", String.class,source_ID);
        return result;
    };

    /**
     * 获取签核历史
     * ordernumber：单号
     *
     * */
    public String getRecords(String orderNumber){
        String result =restTemplate.getForObject(url+"getRecords/{orderNumber}", String.class,orderNumber);
        return result;
    };


    /**
     * 获取单子的操作按钮
     * ordernumber：单号
     * handler：工号
     *
     * */
    public String getActions(String orderNumber, String handler){
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNumber", orderNumber);
        map.put("handler", handler);
        ClientHttpRequestInterceptor formRequest = new FormPostClientHttpRequestInterceptor();
        restTemplate.setInterceptors(Collections.singletonList(formRequest));

        String result =restTemplate.getForObject(url+"/getActions/{orderNumber}/{handler}", String.class,map);
        return result;
    };


    /**
     * 新建流程实例
     * drafter：工号
     * drafterUnit：部门ID
     * orderNumber：单号
     * */
    public void create(String drafter,String drafterUnit, String orderNumber){
        Map<String, String> map = new HashMap<String, String>();
        map.put("drafter", drafter);
        map.put("drafterUnit", drafterUnit);
        map.put("orderNumber", orderNumber);
        restTemplate.postForObject(url+"create/{drafter}/{drafterUnit}/{orderNumber}","",String.class,map);
    };


    /**
     * 提交操作即点击按钮后会执行
     * ordernumber：：单号
     * handler：处理人即工号
     * handlerUnit:处理人部门
     * uids：交办、征询意见等会用到的人员String格式为（10000222/C1105006）
     * comment：签核中填写的内容
     * action：按钮对应的数字
     * map：参数
     *
     * */
    public String signal(String orderNumber, String handler, String handlerUnit, List<String> uids, String comment,
                         int action, Map<String, Object> map){
        String maps = "";
        String list ="";
        if (uids!=null && !uids.equals("")) {
            list =JSON.toJSONString(uids);
        }

        if (map!=null && !map.equals("")) {
            maps = JSON.toJSONString(map);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("orderNumber", orderNumber);
        m.put("handler", handler);
        m.put("handlerUnit", handlerUnit);
        m.put("action", action+"");

        ClientHttpRequestInterceptor formRequest = new FormPostClientHttpRequestInterceptor();
        restTemplate.setInterceptors(Collections.singletonList(formRequest));


        String request;
        byte[] requestBody = null;
        try {
            request = "map="+maps+"&uids="+list+"&comment="+URLEncoder.encode(comment, "UTF-8");
            requestBody = request.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String result = restTemplate.postForObject(url+"signal/{orderNumber}/{handler}/{handlerUnit}/{action}",requestBody ,String.class,m);

        return result;
    };

    /**
     * 获取当前的节点
     * uid：用户ID
     * order：单号
     *
     * */
    public  String getToken(String uid, String order){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", uid);
        map.put("order", order);
        String result = restTemplate.getForObject(url+"getToken/{user}/{order}", String.class,map);

        return result;
    };

    /**
     * 获取当前的节点
     * 当前的节点接收日期
     * 当前节点处理人
     * 表单结束日期
     * */
    public  String getSORData(String order){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", order);
        String result = restTemplate.getForObject(url+"getSORData/{order}", String.class,map);
        return result;
    };

    /**
     * 判断人员是否有权限打开此单
     * order：单号
     * uid：用户ID
     *
     * */
    public  boolean permissionCheck(String order, String uid){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", order);
        map.put("uid", uid);
        String result = restTemplate.getForObject(url+"permissionCheck/{order}/{uid}",String.class,map);
        return result.equals("true");
    };


    /**
     * 获取待办事项、签核追踪、历史记录。。。
     * uid：用户ID
     * siti：所属项目（sec_source的site_FK 如HR）
     *
     * key-Value： EXECUTION=1-待办事项,HOLD=2-保留事项，HISTORY=3-签核历史，TRACE=5-签核追踪
     * */
    public  Map<Integer, List<RmiResult>> getAllRecords(String uid,
                                                        String site){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("site", site);
        map.put("uid", uid);
        String result = restTemplate.getForObject(url+"getAllRecords/{uid}/{site}",String.class,map);
        Map<Integer, List<RmiResult>> map2 = JSON.parseObject(result,new TypeReference<Map<Integer, List<RmiResult>>>(){});
        return map2;

    };


    /**
     * 获取待办事项、签核追踪、历史记录。。。
     * uid：用户ID
     * type： 1-待办事项,2-保留事项，3-签核历史，5-签核追踪
     * siti：所属项目（sec_source的site_FK 如HR）
     * key：单子模糊搜索 如SOR1501
     * */
    public  List<RmiResult> getRecords(String uid, int type,
                                       String site, String key){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("type", type);
        map.put("site", site);

        String request;
        byte[] requestBody = null;
        String key2 = "";

        if (key!=null && !key.equals("")) {
            key2 = JSON.toJSONString(key);
        }
        request = "key="+key2;
        try {
            requestBody = request.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String result = restTemplate.postForObject(url+"getRecords/{uid}/{type}/{site}",requestBody,String.class,map);
        List<RmiResult> list= JSON.parseObject(result,new TypeReference<List<RmiResult>>(){});
        return list;

    };

    public  String getExecution(String order){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", order);
        String result = restTemplate.getForObject(url+"getExecution/{order}", String.class,map);
        return result;
    };

    public  Map<String, Object> getHistoryRecord(Map<String, Object> map){

        String request;
        byte[] requestBody = null;
        try {
            request = "dept="+map.get("dept")+"&user_ID="+map.get("user_ID")+"&drafter="+
                    map.get("drafter")+"&orderNumber="+map.get("orderNumber")+"&dateOfBegin="+map.get("dateOfBegin")+
                    "&dateOfEnd="+map.get("dateOfEnd")+"&bizType="+map.get("bizType")+"&page="+map.get("page");
            requestBody = request.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ClientHttpRequestInterceptor formRequest = new FormPostClientHttpRequestInterceptor();
        restTemplate.setInterceptors(Collections.singletonList(formRequest));

        String result = restTemplate.postForObject(url+"getHistoryRecord",requestBody ,String.class);
        Map<String, Object> map2 = JSON.parseObject(result,new TypeReference< Map<String, Object>>(){});
        return map2;
    };


    /**
     * 获取变量
     * @param name 变量名
     * @param order 单号
     * @return
     */
    public  String getVariable(String name, String order){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("order", order);
        String result = restTemplate.getForObject(url+"getVariable/{name}/{order}", String.class,map);

        return result;
    };

    /**
     * 根据条件获取签核历史
     * @param map
     * @return
     */
    public  String getHistory(Map<String, Object> map){


        String request;
        byte[] requestBody = null;
        try {
            request = "dept="+map.get("dept")+"&user_ID="+map.get("user_ID")+"&drafter="+
                    map.get("drafter")+"&orderNumber="+map.get("orderNumber")+"&sendDateOfBegin="+map.get("sendDateOfBegin")+
                    "&sendDateOfEnd="+map.get("sendDateOfEnd")+"&bizType="+map.get("bizType")+"&start="+map.get("start")+
                    "&size="+map.get("size")+"&order="+map.get("order")+"&node="+map.get("node");
            requestBody = request.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ClientHttpRequestInterceptor formRequest = new FormPostClientHttpRequestInterceptor();
        restTemplate.setInterceptors(Collections.singletonList(formRequest));

        String result = restTemplate.postForObject(url+"getHistory",requestBody ,String.class);
        return result;
    }

    /**根据owner查询对应的代理人
     * @param map
     * @return
     */
    public String getDeputy(String owner) {
        String result = restTemplate.getForObject(url + "getDeputy/{owner}",String.class, owner);
        return result;
    }

    /**查询仪器数据
     * @param map
     * @return
     */
    public String getEquips() {
        String result = restTemplate.getForObject(url + "getEquips",String.class, "");
        return result;
    }

    //查询Cell特殊退货单料号
    public String getProductSearch(String product_FK ,String prodVersion_FK){
        String result =restTemplate.getForObject(arcurl+"activitiClientService.do?m=getProduct_FKsearch&product_FK="+product_FK+"&prodVersion_FK="+prodVersion_FK+"", String.class);
        return result;
    }

    //查询厂商名称
    public String getVendor(String name){
        String result="";
        try {
            String vendor=URLEncoder.encode(name, "UTF-8");
            result =restTemplate.getForObject(arcurl+"activitiClientService.do?m=getVendor&vendor="+vendor+"", String.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;

    }

    public String getEcInfo(String materialedpro){
        String result =restTemplate.getForObject(qmsurl+"getallImprojectList.do?materialedpro="+materialedpro+"", String.class);
        return result;
    }
    //查询PQM巡检记录机种
    public String getallImprojectList(){
        String result =restTemplate.getForObject(qmsurl+"getallImprojectList.do", String.class);
        return result;
    }

    public String getEcNumberInfo(String order,String ecPublisher){
        String result =restTemplate.getForObject(arcurl+"ecService.do?m=getEcNumber&order="+order+"&ecPublisher="+ecPublisher+"", String.class);
        return result;
    }

    public String getVer1Info(String orderNumber){
        String result =restTemplate.getForObject(arcurl+"ecService.do?m=getVer1Info&orderNumber="+orderNumber+"", String.class);
        return result;
    }

    public String getVer2Info(String orderNumber){
        String result =restTemplate.getForObject(arcurl+"ecService.do?m=getVer2Info&orderNumber="+orderNumber+"", String.class);
        return result;
    }

    /*获取Fur单号,主题*/
    public String getFurOrder(String keyWord,String employee_ID){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=searchFUROrder&KeyWord="+keyWord+"&employee_ID="+employee_ID+"", String.class);

        return result;
    }
    /*获取业务客户编码及客户名称*/
    public String getCustomers(){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=getCustomerNO", String.class);
        return result;
    }

    /*获取物料组编号*/
    public String getMtrlGroup(){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=searchMtrlGroupFK", String.class);

        return result;
    }

    /*获取物料编号*/
    public String getMtrlFK(String mrtlGroupFK,String keyWord){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=searchMtrlFK&mrtlGroupFK="+mrtlGroupFK+"&keyWord="+keyWord+"", String.class);

        return result;
    }

    /*获取仓储编号*/
    public String getStorageID(String plant){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=getStorageID&Plant="+plant+"", String.class);

        return result;
    }
    /*获取IT仓库库存*/
    public String findInventory(String MTRL_ID,String MTRL_GROUP,String WERKS,String STORAGE_ID){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=findInventory&MTRL_ID="+MTRL_ID+"&MTRL_GROUP="+MTRL_GROUP+"&WERKS="+WERKS+"&STORAGE_ID="+STORAGE_ID+"", String.class);

        return result;
    }


    /*插入IT仓库库存记录*/
    public String insertInventoryHis(String Mtrl_ID, String Werks, String Mtrl_dsc, String Mtrl_Group, int ValidFlag, Calendar DateOfCreate, Calendar DateOfUpdate, String Unit, String User_id, double InQty, String TrackingNumber, String Storage){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=insertInventoryHis&Mtrl_ID="+Mtrl_ID+"&Werks="+Werks+"&Mtrl_dsc="+Mtrl_dsc+"&Mtrl_Group="+Mtrl_Group+"&ValidFlag="+ValidFlag+""
                + "&DateOfCreate="+DateOfCreate+"&DateOfUpdate="+DateOfUpdate+"&Unit="+Unit+"&User_id="+User_id+"&InQty="+InQty+"&TrackingNumber="+TrackingNumber+"&Storage="+Storage+"", String.class);
        return result;
    }

    /*扣除IT仓库库存*/
    public String minusInventory(String Mtrl_ID,String Werks,String Mtrl_Group,String Storage,double totalqty){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=minusInventory&Mtrl_ID="+Mtrl_ID+"&Werks="+Werks+"&Mtrl_Group="+Mtrl_Group+"&totalqty="+totalqty+"&Storage="+Storage+"", String.class);
        return result;
    }


    /*获取主岗位及副岗位所在部门所有人员的ID,name,en*/
    public String getVicePostEmpTree(String Employee_ID,String keyWord){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=getVicePostEmpTree&Employee_ID="+Employee_ID+"&keyWord="+keyWord+"", String.class);
        return result;
    }

    /**
     * insert memo to 2.53 MYIVOdb
     *
     **/
    public String setMemoToMyivodb(String order,String memo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(memo==null||memo==""){
            memo = " ";
        }
        map.put("order", order);
        map.put("memo", StringUtil.encodeStringURLSafe(memo));
        String result = restTemplate.getForObject(url+"memorizeMyIVO2Record/{order}/{memo}", String.class,map);
        return result;
    }

    /**
     * query Order to 2.53 MYIVOdb
     *
     **/
    public String getSignOffOrderNumbers(String keyword){
        Map<String, Object> map = new HashMap<String, Object>();
        if(keyword==null){
            keyword = "SOR";
        }
        map.put("keyword", keyword);
        String result = restTemplate.getForObject(url+"getSignOffOrderNumbers/{keyword}", String.class,map);
        return result;
    }

    //2.81EIFD
    //查询入库单的料号
    public String getMtrl(String instorType){
        String result = restTemplate.getForObject(arcurl+"activitiClientService.do?m=getGisMtrl&instorType="+instorType+"",String.class);
        return result;
    }
    //模糊查询
    public String geMtrlSearch(String mtrls){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=searchGisMtrl&mtrls="+mtrls+"", String.class);
        return result;
    }
    //查询入库单仓位
    public String getPosition(String instorType,String areaType ){
        String result = restTemplate.getForObject(arcurl+"activitiClientService.do?m=getGisPosition&instorType="+instorType+"&areaType="+areaType+"",String.class);
        return result;
    }
    //资讯入库单的库存
    public String getInventory(String MTRL_ID,String MTRL_GROUP,String WERKS,String STORAGE_ID){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=getInventory&MTRL_ID="+MTRL_ID+"&MTRL_GROUP="+MTRL_GROUP+"&WERKS="+WERKS+"&STORAGE_ID="+STORAGE_ID+"", String.class);
        return result;
    }


    /*插入资讯入库单的库存*/
    public String getInsertInventory(String Mtrl_ID,String Werks,String Mtrl_dsc,String Mtrl_Group,int ValidFlag,Calendar DateOfCreate,Calendar DateOfUpdate,String Unit,String User_id,double InQty,String TrackingNumber,String Storage){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=getInsertInventory&Mtrl_ID="+Mtrl_ID+"&Werks="+Werks+"&Mtrl_dsc="+Mtrl_dsc+"&Mtrl_Group="+Mtrl_Group+"&ValidFlag="+ValidFlag+""
                + "&DateOfCreate="+DateOfCreate+"&DateOfUpdate="+DateOfUpdate+"&Unit="+Unit+"&User_id="+User_id+"&InQty="+InQty+"&TrackingNumber="+TrackingNumber+"&Storage="+Storage+"", String.class);
        return result;
    }

    /*修改资讯入库单的库存*/
    public String getUpdateInventory(String Mtrl_ID,String Werks,String Mtrl_Group,double totalqty){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=getUpdateInventory&Mtrl_ID="+Mtrl_ID+"&Werks="+Werks+"&Mtrl_Group="+Mtrl_Group+"&totalqty="+totalqty+"", String.class);
        return result;
    }

    public String getUpdateInventory1(String Mtrl_ID,String Werks,String Mtrl_Group,String Storage,double totalqty){
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=getUpdateInventory1&Mtrl_ID="+Mtrl_ID+"&Werks="+Werks+"&Mtrl_Group="+Mtrl_Group+"&totalqty="+totalqty+"&Storage="+Storage+"", String.class);
        return result;
    }
    /**
     * Iec单子从Arcadia 抓取PO信息
     * @param purchaseOrder 采购订单号
     * @return
     */
    public String getPOForIEC(String purchaseOrder){
        String result = restTemplate.getForObject(arcurl+"activitiClientService.do?m=getPurchaseOrderForIEC&purchaseOrder="+purchaseOrder, String.class);
        return result;
    }

    /*待处理资产申请单获取材料的接口*/
    public String selectAssets(String condition){
        String result = restTemplate.getForEntity(arcurl+"IarDataList.do?condition="+condition+"", String.class).getBody();
        return result;
    }

    /*待处理资产申请报表的接口*/
    public String selectReport(String condition){
        String result = restTemplate.getForEntity(arcurl+"IarReport.do?condition="+condition+"", String.class).getBody();
        return result;
    }

    /*获取时间*/
    public String selectUpdate(String condition){
        String result = restTemplate.getForEntity(arcurl+"IarDate.do?condition="+condition+"", String.class).getBody();
        return result;
    }

    /*公务电话申请单接口*/
    public String select(String employee_ID){
        String result = restTemplate.getForObject(hrurl+"getResignFlag.do?employee_ID="+employee_ID+" ", String.class);
        return result;
    }
    /*收货单接口*/
    public String getGoodsReceiveNumberForWH(String goodsReceiveNumber){
        // String urll="http://10.20.46.108:8080/Arcadia/";
        String result = restTemplate.getForObject(arcurl+"activitiClientService.do?m=getGoodsReceiveNumberForWH&goodsReceiveNumber="+goodsReceiveNumber, String.class);
        return result;
    }
    /*update 收货单*/
    public String getInsertGR(String trackingNumber,String orderNumber){
        // String urll="http://10.20.46.108:8080/Arcadia/";
        String result=restTemplate.getForObject(arcurl+"activitiClientService.do?m=getInsertGR&trackingNumber="+trackingNumber+"&orderNumber="+orderNumber, String.class);
        return result;
    }

    /*设备追踪单--获取具体的PR对象*/
    public String getspecification(String prNumber){
        System.out.println(arcurl);
        String result = restTemplate.getForEntity(arcurl+"activitiClientService.do?m=getspecification&prNumber="+prNumber, String.class).getBody();
        return result;
    }

    public String getALL(String projectCode){
        String result = restTemplate.getForEntity(arcurl+"activitiClientService.do?m=getALL&projectCode="+projectCode, String.class).getBody();
        return result;
    }

    /*获取背板编号接口*/
    public String getBPNumber(){
        String result = restTemplate.getForEntity(arcurl+"BPNumberService.do?m=getBPNumber", String.class).getBody();
        return result;
    }

    /*更新背板类型接口*/
    public void updateBPStyle(String BPNumber, String BPStyle){
        restTemplate.getForEntity(arcurl+"BPNumberService.do?m=updateBPStyle&BPNumber="+ BPNumber +"&BPStyle="+ BPStyle, String.class).getBody();
    }

    /*获取pr设备名称接口*/
    public String getDeviceName(String projectCode){
        String result = restTemplate.getForEntity(arcurl+"activitiClientService.do?m=getDeviceName&projectCode="+ projectCode, String.class).getBody();
        return result;
    }

    /*获取起草人座机号码接口*/
    public String getTelephoneByEmployee_ID(String employee_ID){
        String result = restTemplate.getForEntity(arcurl+"activitiClientService.do?m=getTelephoneByEmployee_ID&employee_ID="+ employee_ID, String.class).getBody();
        return result;
    }

    //查询空房间
    public String getVacantRoom(){
        String result = restTemplate.getForEntity(gsmurl+"info/getVacantRoom",String.class).getBody();
        return result;
    }
    public String getDormMessage(String employee){
        String result = restTemplate.getForEntity(gsmurl + "info/dormitoryInfo/empid='"+employee+"'", String.class).getBody();
        return result;
    }
    //查询所有住宿信息
    public String getDromEmpMsg(String empID,String buildID,String roomID,String type,String moveInDate){
        String result = restTemplate.getForObject(gsmurl + "info/getDormEmpMsg?empID="+empID+"&buildID="+buildID+"&roomID="+roomID+"&type="+type+"&moveInDate="+moveInDate+" ", String.class);
        return result;
    }
    //发送住宿人员信息
    public void addEmpMsg(String jsonList){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("jsonList", jsonList);
        restTemplate.getForObject(gsmurl+"info/addEmpMsg/{jsonList}",String.class,map);
    }

    //查询双职工工号
    public String getCoupleID(String employee_ID){
        String result = restTemplate.getForEntity(hrurl+"getRltEmp.do?employee_FK="+employee_ID,String.class).getBody();
        return result;
    }

    //积分排名--查询绩效
    public String getAchievements(String employee_ID){
        String result = restTemplate.getForEntity(hrurl+"getPerfInt.do?employee_FK="+employee_ID,String.class).getBody();
        return result;
    }

    /*根据IP发送至测试人*/
    public String getVerifyMail(String ipAddress){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ipAddress", ipAddress);
        String result = restTemplate.getForObject(hisurl+"verifytoMail/{ipAddress}",String.class,map);
        return result;
    }

    /*新增功能是否RUN*/
    public String getISRunning(String paramName){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paramName", paramName);
        String result = restTemplate.getForObject(hisurl+"functionControl/{paramName}",String.class,map);
        return result;
    }

    public String getIfResign(String employee_ID){
        String result = restTemplate.getForEntity(hrurl+"getResignFlag.do?employee_ID="+employee_ID,String.class).getBody();
        return result;
    }

    public String getPRDataForIEProject(String IEProject_ID){
        String result = restTemplate.getForEntity(arcurl+"prDataForIEProject.do?ieProject_ID="+IEProject_ID,String.class).getBody();
        return result;
    }

    public String getEquipmentDataList(String strKeyword){
        String result = restTemplate.getForEntity(arcurl+"EquipmentDataList.do?condition="+strKeyword,String.class).getBody();
        return result;
    }

    public String getNewEmp(){
        String result = restTemplate.getForEntity(gsmurl+"date/xinEmployee",String.class).getBody();
        return result;
    }

    public String doPost(String method, String json,HttpServletRequest request) {
        //HttpClient client = HttpClients.createDefault();
        HttpConnectionManager httpConnectionManager = new HttpConnectionManager();
        CloseableHttpClient client = httpConnectionManager.getHttpClient();
        String url = hisurl + method;
        HttpPost post = new HttpPost(url);
        String response = null;
        json=StringUtil.encodeStringURLSafe(json);
        try {
            StringEntity s = new StringEntity(json);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            post.addHeader("content-type", "text/xml");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(3000)
                    .setSocketTimeout(5000).build();
            post.setConfig(requestConfig);
            CloseableHttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String content = null;
                try {
                    //接收响应实体
                    content = EntityUtils.toString(res.getEntity());
                    //消耗响应实体
                    EntityUtils.consume(res.getEntity());
                    if(res!=null)
                        try {
                            //把连接放回连接池
                            res.close();
                            client.close();
                        } catch (IOException e1) {
                            System.out.println("响应关闭失败！"+ e1);
                        }
                } catch (ParseException e2) {
                    System.out.println("解析响应实体异常！"+ e2);
                } catch (IOException e3) {
                    System.out.println("java IO 异常！"+ e3);
                }
                response = content;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     *
     * 获取ACT_EX_NODE/CAN_ATTACH属性来标识附件TAG
     *
     ****/
    public String ISAttach(String source,String node){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("source", source);
        map.put("node", node);
        String result = restTemplate.getForObject(url+"getISAttach/{source}/{node}", String.class,map);
        return result;
    }

    /*------------------------------------org接口----------------------------------------*/
    public String loadOrgInfo(String method, String root, String id){
        String callback = "jQuery"+UUID.randomUUID().toString().replaceAll("-", "")+"_"+System.currentTimeMillis();

        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<String, Object>();
        postParameters.add("root", root);
        if(!"10000000".equals(id)){
            postParameters.add("id", id);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<MultiValueMap<String, Object>>(postParameters, headers);

        String result = restTemplate.postForObject(orgurl+method+"?m=load&callback="+callback,r ,String.class);
        String json = result.substring(callback.length()+1, result.length()-2);
        if(json.equals("[]")) return null;
        return json;
    }

    public String queryOrgInfo(String method, String root, String queryString ){
        String callback = "jQuery"+UUID.randomUUID().toString().replaceAll("-", "")+"_"+System.currentTimeMillis();

        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<String, Object>();
//		queryString = StringUtil.encodeStringURLSafe(queryString);
        postParameters.add("root", root);
        postParameters.add("queryString", queryString);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<MultiValueMap<String, Object>>(postParameters, headers);

        String result = restTemplate.postForObject(orgurl+method+"?m=query&callback="+callback,r ,String.class);
        String json = result.substring(callback.length()+1, result.length()-2);
        if(json.equals("[]")) return null;
        return json;
    }
    /*------------------------------------org接口----------------------------------------*/

}
