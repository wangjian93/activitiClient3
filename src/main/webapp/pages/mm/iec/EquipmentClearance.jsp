<%@ page language="java" pageEncoding="UTF-8"
	import="com.ivo.activiticlient.mm.iec.*"
	import="com.ivo.activiticlient.common.tag.*"
	import="com.ivo.activiticlient.common.util.*"
	import="java.text.SimpleDateFormat"
%>
<%
	EquipmentClearance model = (EquipmentClearance)request.getAttribute("model");

%>
<!DOCTYPE HTML>
<html>
<head>
	<title>进口设备清关信息单</title>
	<style type="text/css">
		.input {
		    width: 400px;
		    min-height: 20px;
		    border: 1px solid #ccc;
		    line-height:20px;
		    resize: none;
		    overflow: hidden;
		    word-wrap: break-word;
		}  
		.hide {
		    position: absolute;
		    z-index: -100;
		    visibility: hidden;
		}
    </style>
	<%@ include file="/pages/common/source.jsp" %>
	<script type="text/javascript" src="./pages/mm/iec/EquipmentClearance.js"></script>
	<script type="text/javascript">
	function Document_OnData(){
	<%	if(model.getStatus() != "020"){
		for( int i=0; i < model.getPoInformation().getPoInformationItem().size(); i++  ) {
			PoInformationItem objItem = model.getPoInformation().getPoInformationItem().get( i );
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
			String date;
			if(objItem.getDateOfDelivery()==null){
				date = "";
			}else{
				date = df.format(objItem.getDateOfDelivery());
			}
	%>
			Order_addItem(
				"<%=objItem.getItemLine()%>", 
				"<%=objItem.getMaterial()%>", 
				"<%=objItem.getMaterialDescription()%>", 
				"<%=objItem.getStorageLocation()%>", 
				"<%=objItem.getOrderQty()%>", 
				"<%=objItem.getMeasureUnit()%>", 
				"<%=objItem.getEquipment()%>",
				"<%=date%>"
				
			);
	<%	} }
	%>
	}
	</script>
</head>
<body>
	<hr:menubar/>
	<div class="main">
		<hr:title title="进口设备清关信息单"/>
		<form id="orderForm" method="post">
			<hr:hidden />
			<hr:sort sort="表单信息">
				<table class="table three-row">
					<hr:basicinfo />
				</table>
			</hr:sort>
			<hr:sort sort="PO信息">
				<table class = "table">
					<tr>
						<!-- 任务节点 -->
						<td><input id="token" type="hidden" name="token" value="${token}"/></td>
						<!-- 登录用户部门代码  -->
						<td><input id="dept_ID" type="hidden" name="dept_ID" value="${dept_ID}"/></td>
					</tr>
					
					<tr>
						<td class="label">采购订单号：</td>
						<td>
							<input id="purchaseOrder"  type="text" name="purchaseOrder" ${(draftflag) ? "" : "readonly"} onblur="getPurchaseOrder()" value="${model.poInformation.purchaseOrder}"/>
							<span id="msg" style="font-size:10px;color:red"></span>
						</td>
						<td class="label">日期：</td>
						<% 	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							String date = ""; 
							if(model.getPoInformation()!=null){
								if(model.getPoInformation().getDateOfOrder()==null){
									date = "";
								}else{
									date = df.format(model.getPoInformation().getDateOfOrder());
								}
							}else{
								date = "";
							}
							%>
						<td>
							<input id="dateOfOrder"  type="text" name="dateOfOrder" readonly  value="<%=date%>"/>
						</td>
						<td class="label">供应商：</td>
						<td>
							<input class="longText" id="vendor" type="text" name="vendor" readonly  value="${model.poInformation.vendor }"/>
						</td>
					</tr>
					
					<tr>
						<td class="label">厂商地址：</td>
						<td>
							<input class="longText" id="address" type="text" name="address" readonly  value="${model.poInformation.address }"/>
						</td>
						<td class="label">邮编：</td>
						<td>
							<input id="zipCode" type="text" name="zipCode" readonly  value="${model.poInformation.zipCode }"/>
						</td>
						<td class="label">交货方式：</td>
						<td>
							<input id="deliveryTerm" type="text" name="deliveryTerm" readonly  value="${model.poInformation.deliveryTerm }"/>
						</td>
					</tr>
							
					<tr>
						<td class="label">电话：</td>
						<td>
							<input id="vendorContactExt" type="text" name="vendorContactExt" readonly  value="${model.poInformation.vendorContactExt }"/>
						</td>
						<td class="label">保固期：</td>
						<td>
							<input id="warrantyTerm"  type="text" name="warrantyTerm" readonly  value="${model.poInformation.warrantyTerm }"/>
						</td>
						<td class="label">传真：</td>
						<td>
							<input id="vendorFax" type="text" name="vendorFax" readonly  value="${model.poInformation.vendorFax }"/>
						</td>
					</tr>
					
					<tr>
						<td class="label">联系人：</td>
						<td>
							<input id="vendorContact" type="text" name="vendorContact" readonly  value="${model.poInformation.vendorContact }"/>
						</td>
						<td class="label">收货地点：</td>
						<td>
							<input id="deliveryPlace" type="text" name="deliveryPlace" readonly  value="${model.poInformation.deliveryPlace }"/>
						</td>
						<td class="label">收货联系人：</td>
						<td>
							<input id="goodsReceiveContact" type="text" name="goodsReceiveContact" readonly  value="${model.poInformation.goodsReceiveContact }"/>
						</td>
					</tr>
					
					<tr>
						<td class="label">联系电话：</td>
						<td>
							<input id="contactExt" type="text" name="contactExt" readonly  value="${model.poInformation.contactExt }"/>
						</td>
						<td class="label">采购员：</td>
						<td>
							<input id="requisitioner" type="text" name="requisitioner" readonly value="${model.poInformation.requisitioner}"/>
						</td>
					</tr>
				</table>
				</br>
				<input id="LastRow" type="hidden" name="LastRow" value="0"/>
				<table  id="poItem"   cellspacing="0" cellpadding="0" class="record" style='align:center;border:1px;'>
					<tr>
						<td style="width:8%" class="Header_Text">行项目</td> 
						<td style="width:12%" class="Header_Text">物料号</td> 
						<td class="Header_Text">品名规格</td> 
						<td style="width:12%" class="Header_Text">存放地点</td> 
						<td style="width:10%" class="Header_Text">数量</td> 
						<td style="width:8%" class="Header_Text">单位</td> 
						<td style="width:12%" class="Header_Text">机台编码</td> 
						<td style="width:12%" class="Header_Text">交付日期</td> 
						
					</tr>
				</table>
				</br>
			</hr:sort>
			<hr:sort sort="确认信息">
				<table  >
					<tr>
						<td style='width:450px'>
						<div id='div1' style="display:block;"><font style="font-weight:bold;color:#1177a7">物流确认</font></div>
						</td>
						<td style='width:400px'><b>回复</b></td>
						<td ></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  id="logiticsConfirm2" ${(logiticsflag) ? "" : "disabled"} type="radio" name="logiticsConfirm" ${(model.logiticsConfirm=="2") ? "checked":" "}  style="vertical-align:middle" value='2'>&nbsp;&nbsp;一般贸易缴税进场（含零关税）&nbsp;&nbsp;&nbsp;&nbsp; <input id="logiticsConfirm1" ${(logiticsflag) ? "" : "disabled"}  type="radio" name="logiticsConfirm" ${(model.logiticsConfirm=="1") ? "checked":" "} style="vertical-align:middle" value='1'>&nbsp;&nbsp;免税免表进场</td>
						<td><pre class="input hide" id="pre1">${model.answer1}</pre><textarea class="input" id="textarea1"  ${(logiticsflag) ? "" : "readonly"}  name="answer1" >${model.answer1}</textarea></td>
						<td></td>
					</tr>
					
				</table>
				
				<table>
					<tr>
						<td style='width:450px'>
							<font style="font-weight:bold;color:#1177a7">对外事务确认</font>
						</td>
						<td style='width:400px'><b>回复</b></td>
						<td ></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="erConfirm" ${(epflag) ? "" : "disabled"}  type="checkbox" name="erConfirm" ${(model.ERConfirm=="1") ? "checked":" "}  style="vertical-align:middle;" value="1">&nbsp;&nbsp;办理小清单</td>
						<td><pre class="input hide" id="pre3"></pre><textarea class="input" id="textarea3" ${(epflag) ? "" : "readonly"} name="answer3">${model.answer3}</textarea></td>
						<td valign="center">
							<div class="upload">
								<a  ${(draftflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""}   title="添加附件"></a>
							</div>
						</td>
					</tr>
				</table>
				
				<table >
					<tr>
						<td style='width:450px'>
							<div id='div1' style="display:block;"><font style="font-weight:bold;color:#1177a7">采购确认</font></div>
						</td>
						<td style='width:400px'><b>回复</b></td>
						<td ></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"}  type="checkbox" name="purConfirm1" ${(model.purConfirm1=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;1.发货人信息：公司名称、地址、联系人、FOB具体港口、预计日期</td>
						<td><pre class="input hide" id="pre4"></pre><textarea class="input" id="textarea4"  ${(erflag) ? "" : "readonly"}   name="answer4">${model.answer4}</textarea></td>
						<td valign="center">
							<div class="upload">
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"} type="checkbox" name="purConfirm2" ${(model.purConfirm2=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;2.双方用印的PO</td>
						<td><pre class="input hide" id="pre5"></pre><textarea class="input" id="textarea5"   ${(erflag) ? "" : "readonly"}  name="answer5">${model.answer5}</textarea></td>
						<td valign="center">
							<div class="upload">
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>	
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"} type="checkbox" name="purConfirm3"  ${(model.purConfirm3=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;3.请确认是否需要气垫车等特殊车辆运输</td>
						<td><pre class="input hide" id="pre6"></pre><textarea class="input" id="textarea6"   ${(erflag) ? "" : "readonly"}  name="answer6">${model.answer6 }</textarea></td>
						<td valign="center">
							<div class="upload">
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>	
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"} type="checkbox" name="purConfirm4" ${(model.purConfirm4=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;4.申报要素及相关技术资料配合填写</td>
						<td><pre class="input hide" id="pre7"></pre><textarea class="input" id="textarea7"   ${(erflag) ? "" : "readonly"}  name="answer7">${model.answer7 }</textarea></td>	
						<td valign="center">
							<div class="upload">
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"}  type="checkbox" name="purConfirm5"  ${(model.purConfirm5=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;5.木质包装要提供IPPC标示及代码并且打在Packing List上</td>
						<td><pre class="input hide" id="pre8"></pre><textarea class="input" id="textarea8"  ${(erflag) ? "" : "readonly"}  name="answer8">${model.answer8 }</textarea></td>
						<td valign="center">
							<div class="upload">
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>	
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"} type="checkbox" name="purConfirm6" ${(model.purConfirm6=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;6.若多个柜子，厂商需要提供每个柜子的装箱明细，
							最好有具体的</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;装箱位置图,木箱上用记号笔表示铭牌位置及照片，方便海关查验</td>
						<td><pre class="input hide" id="pre9"></pre><textarea class="input" id="textarea9"  ${(erflag) ? "" : "readonly"}  name="answer9">${model.answer9 }</textarea></td>	
						<td valign="center">
							<div class="upload">
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"}  type="checkbox" name="purConfirm7" ${(model.purConfirm7=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;7.发票、箱单也需要发货人印章，海外的需签字</td>
						<td><pre class="input hide" id="pre10"></pre><textarea class="input" id="textarea10"  ${(erflag) ? "" : "readonly"}  name="answer10">${model.answer10 }</textarea></td>	
						<td >
							<div class="upload" >
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"}  type="checkbox" name="purConfirm8"  ${(model.purConfirm8=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;8.确认有无垫木，如有需提供垫木数量</td>
						<td><pre class="input hide" id="pre11"></pre><textarea class="input" id="textarea11"  ${(erflag) ? "" : "readonly"}   name="answer11">${model.answer11 }</textarea></td>
						<td valign="center">
							<div class="upload">
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>	
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  ${(erflag) ? "" : "disabled"}  type="checkbox" name="purConfirm9" ${(model.purConfirm9=="1") ? "checked":" "} style="vertical-align:middle" value="1">&nbsp;&nbsp;9.实物照片&铭牌照片</td>
						<td><pre class="input hide" id="pre12"></pre><textarea class="input" id="textarea12"  ${(erflag) ? "" : "readonly"}   name="answer12">${model.answer12 }</textarea></td>
						<td valign="center">
							<div class="upload">
								<a ${(erflag) ? "href='javascript:void(0)'  onclick='onUpload()'" : ""} title="添加附件"></a>
							</div>
						</td>	
					</tr>
				</table>
				<table class = "table"></table>
			</hr:sort>
			
		</form>
		<hr:sort sort="附件"><hr:attachment/></hr:sort>
		
 		<hr:sort sort="签核日志"><hr:record/></hr:sort>
	</div>

	<!-- ----------------------------------------------------- -->
	<!-- Detail HTML Template - FurItem -->
	
</body>
</html>