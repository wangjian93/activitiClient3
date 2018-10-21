<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
	<title>HR Attendance System</title>
	<%@ include file="/pages/common/source_rep.jsp" %>
	<link rel="stylesheet" type="text/css" href="./styles/index.css">
	<link rel="stylesheet" type="text/css" href="./styles/clear.css">
	<script type="text/javascript">
	function homeReload(){
		window.location.reload();
	}
	
	function href_(id){
		for(var i = 1; i <=5; i++ ){
			if(i == id){
				document.getElementById("tab_"+i).style.display="";
				document.getElementById("menu_"+i).className="selected";
			}
			else{
				document.getElementById("tab_"+i).style.display="none";
				document.getElementById("menu_"+i).className="";
			}
		}
	}
	function openDetail(emp_FK,type){
		document.getElementById("emp_FK").value = emp_FK;
		document.getElementById("type").value = type;
		document.getElementById("form").submit();
	}
	</script>
  </head>
  
  <body>
	<div class="top-bg">
		<div class="top-menu">
			<ul>
				<li><a href="http://myivo.ivo.com.cn" target="_blank">MYIVO</a></li>
				<li><a href="http://myivo.ivo.com.cn/Arcadia/pages/dcc/IssuedDocument.jsp" target="_blank">文管中心</a></li>
				<li><a href="http://km.ivo.com.cn" target="_blank">KM管理</a></li>
				<li><a href="http://mt.ivo.com.cn" target="_blank">会议管理</a></li>
				<li><a href="#">更多</a></li>
				
			</ul>
			<span>${userName}&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://myivo.ivo.com.cn/Myivo2/logout.do">登出</a></span>
		</div>
	</div>
	
	<div class="banner-bj">
		<div class="banner-items">
			<ul>
				<li><a href="#">首页</a></li>
				<li><a href="PersonlInfo.do?param=${url}" target="_blank">个人信息</a></li>
				<li><a href="attendance.do?method=getDeptAtt&url=${url}" target="_blank">考勤资料</a></li>
				<li><a href="historyRecord.do" target="_blank">签核记录</a></li>
			</ul>
		</div>
	</div>
	
	<div style="width:960px;margin:25px auto;">
		<div class="left-list">
			<ul>
				<h3>业务申请</h3>
				<a href="./business.do?sid=OT" target="_blank"><li>
					<img src="./images/main/list_overtime.png"/>加班申请单</li></a>
				<a href="./business.do?sid=LR" target="_blank"><li>
					<img src="./images/main/list_leave.png"/>请假申请单</li></a>
				<a href="./business.do?sid=VR" target="_blank"><li>
					<img src="./images/main/list_void.png"/>销假申请单</li></a>
				<a href="./business.do?sid=RS" target="_blank"><li>
					<img src="./images/main/list_restamp.png"/>补刷卡申请单</li></a>
				<a href="./business.do?sid=TR" target="_blank"><li>
					<img src="./images/main/list_travel.png"/>出差申请单</li></a>
				<a href="./business.do?sid=TC" target="_blank"><li>
					<img src="./images/main/list_change.png"/>出差变更申请单</li></a>
				<a href="./business.do?sid=OC" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>加班变更申请单</li></a>
				<a href="./business.do?sid=DRR" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>住宿申请单</li></a>
				<a href="./business.do?sid=FUR" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>任务需求单</li></a>
				<a href="./business.do?sid=DSP" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>住宿特殊加分申请</li></a>
				<a href="./business.do?sid=RB" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>班车标示申请单</li></a>
				<a href="./business.do?sid=PL" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>模组标签确认申请单</li></a>
				<a href="./business.do?sid=ST" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>公务电话申请单</li></a>
				<a href="./business.do?sid=IEH" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>仪器异常处理单</li></a>
					
				<c:if test="${attspFlag == true || attFlag == true}">
					<a href="./business.do?sid=SR" target="_blank"><li>
						<img src="./images/main/shift.png"/>排班申请单</li></a>
				</c:if>
				<a href="./business.do?sid=IC" target="_blank"><li>
					<img src="./images/main/list_otchange.png"/>个人信息修改单</li></a>
			</ul>
			
			<ul>
				<h3>查询报表</h3>			
				<!--<li><a href="./moreThanG10Att.do" target="_blank">
				<img src="./images/main/list_default.png"/>G10以上考勤报表</a></li>
				<li><img src="<./images/main/list_default.png"/>考勤事件报表</li>
				<li><img src="./images/main/list_default.png"/>每日出勤报表</li>
				<li><img src="./images/main/list_default.png"/>补刷卡报表</li> -->
				<li><a href="./regularBusReport.do" target="_blank"><img src="./images/main/list_default.png"/>7:20-7:40 班车申请单报表</li>
				<li><a href="./changeRegularBusReport.do" target="_blank"><img src="./images/main/list_default.png"/>7:20-7:40 班车管理员维护界面</li>
			</ul> 
			<ul style="display:${attspFlag == true || user_ID == 'C0605001' ? 'block' : 'none'}">
				<h3>考勤管理</h3>
				<li>
					<a href="./hourSelect.do" target="_blank">
					<img src="./images/main/hour.png"/>时数管理</a></li>
				<li>
					<a href="./shiftSelect.do" target="_blank">
					<img src="./images/main/shift.png"/>排班管理</a></li>
				<li>
					<a href="./cardInfoSelect.do" target="_blank">
					<img src="./images/main/card.png"/>识别卡管理</a></li>
				<li>
					<a href="./tempStampSelect.do" target="_blank">
					<img src="./images/main/stamp.png"/>刷卡数据管理</a></li>
				<li>
					<a href="./spcProcSelect.do" target="_blank">
					<img src="./images/main/list_default.png"/>免考勤管理</a></li>
			</ul>
		</div>
		
		<div class="center-main">
			<div style="font-weight:bold;color:red;">系统提示：上一年度转入的年假时数将在本年度8月31日清零，请在清零日期前使用。</div>
			<div class="main-item"><span>账户可用时数</span>
				<c:if test="${ not empty pbList }">
					<c:forEach items="${pbList}" var="item">
						<li>${item.eventTypeName}：
						<a href="./pbDetail.do?employee_FK=${user_ID}&eventType_FK=${item.eventType_FK}"
						target="_blank">${item.balance}</a>H</li>
					</c:forEach>
				</c:if>
				<span style="margin-left:30px;font-size:13px;">
				<a href="./pbDetail.do?employee_FK=${user_ID}" target="_blank">查看详细信息</a></span>
			</div>
			<div class="hr"></div>
			
			<div class="main-item"><span>个人考勤记录</span>
			<form id="form" method="post" action="./attendance.do?method=getDeatil" target="_blank" >
				<input name="emp_FK" id="emp_FK" type="hidden" value="" />
				<input name="dateOfBegin" type="hidden" value="${dateOfBegin}" />
				<input name="dateOfEnd" type="hidden" value="${dateOfEnd}" />
				<input name="type" id="type" type="hidden" value=""  />
			</form>
				<div style="float:right">
				<a href="javaScript:void(0)" onclick="openDetail('${user_ID}','stamp');return false;" 
				target="_blank">刷卡记录</a>
				<a href="javaScript:void(0)" onclick="openDetail('${user_ID}','shift');return false;" 
				target="_blank">排班</a>
				<a href="javaScript:void(0)" onclick="openDetail('${user_ID}','form');return false;" 
				target="_blank">表单</a>
				<a href="javascript:void(0);" onclick="openDetail('${user_ID}','detail');return false;" 
				target="_blank">明细</a>
				</div>
				<table>
					<tr>
						<th rowspan="2" style="width:35px;">出勤时数</th>
						<th rowspan="2" style="width:30px;">缺勤时数</th>
						<th rowspan="2" style="width:30px;">迟到次数</th>
						<th rowspan="2" style="width:30px;">早退次数</th>
						<th rowspan="2" style="width:45px;">刷卡补单次数</th>
						<th rowspan="2" style="width:30px;">夜班次数</th>
						<th colspan="11" style="width:360px;">假别</th>
						<th colspan="4" style="width:140px;">加班</th>
					</tr>
					<tr>
						<th>事假</th><th>病假</th><th>年假</th><th>补休</th>
						<th>公假</th><th>出差</th><th>婚假</th><th>产假</th>
						<th>丧假</th><th>伤假</th><th>其他</th>
						<th>排班</th><th>平日</th><th>公休</th><th>法定</th>
					</tr>
					<c:if test="${not empty objDA}">
					<tr>
						<td>${objDA.attdHour}</td><td>${objDA.abscHour}</td><td>${objDA.lateQty}</td>
						<td>${objDA.earlyQty}</td><td>${objDA.stampLost}</td><td>${objDA.nightAllow}</td>
						<td>${objDA.prsnLeave}</td><td>${objDA.sickLeave}</td>
						<td>${objDA.annlLeave}</td><td>${objDA.cmpnHour}</td><td>${objDA.bizTravel}</td>
						<td>${objDA.bizLeave}</td><td>${objDA.marrLeave}</td><td>${objDA.pregLeave}</td>
						<td>${objDA.fnrlLeave}</td><td>${objDA.hurtLeave}</td><td>${objDA.othrLeave}</td>
						<td>${objDA.shftOtHour}</td><td>${objDA.nrmlOtHour}</td>
						<td>${objDA.ghdyOtHour}</td><td>${objDA.shdyOtHour}</td>
					</tr>
					</c:if>
				</table>
			</div>
			
			
			<div class="order-menu">
				<span id="menu_1" class="selected">
					<a href="javascript:void(0);" onClick="href_('1');">待办事项</a></span>
				<span id="menu_2">
					<a href="javascript:void(0);" onClick="href_('2');">保留事项</a></span>
				<span id="menu_3">
					<a href="javascript:void(0);" onClick="href_('3');">签核历史</a></span>	
				<span id="menu_4" style="display:${ not empty agents ? '' : 'none' }">
					<a href="javascript:void(0);" onClick="href_('4');">代理事项</a></span>
				<span id="menu_5">
					<a href="javascript:void(0);" onClick="href_('5');">追踪事项</a></span>
			</div>
			<div id="tab_1">
				<c:if test="${ not empty executions }">
				<div class="total"><span>共${executionCount}待办事项</span></div>
				<c:forEach items="${executions}" var="item">
					<div class="order">
						<li class="order-item">
							<!-- <div class="actions"><a href="javascript:void(0);" class="lnk-ope">快捷操作</a></div> -->
							<h5>
								<a href="http://myivo.ivo.com.cn/Myivo2/business.do?order=${item.order}" target="_blank">
									<span class="form-num">${item.order}</span>${item.sourceName}
								</a>
								<span class="info-right">${item.sendDate}来自
									<a href="javascript:void(0);">${item.senderName}</a></span>
							</h5>
							<div class="more-info">
								由<a href="javascript:void(0);">${item.draftGroupName}</a>的
								<a href="javascript:void(0);">${item.drafterName}</a>
								于${item.draftDate}起草
								<div class="info-memo">${item.order}</div>
							</div>
						</li>
					</div>
				</c:forEach>
				</c:if>
			</div>
			<div id="tab_2" style="display:none;">
				<c:if test="${ not empty holds }">
				<div class="total"><span>共${holdCount}条保留事项</span></div>
				<c:forEach items="${holds}" var="item">
					<div class="order">
						<li class="order-item">
							<h5>
								<a href="./show.do?orderNumber=${item.order}" target="_blank">
									<span class="form-num">${item.order}</span>${item.sourceName}
								</a>
								<span class="info-right">${item.sendDate}来自
									<a href="javascript:void(0);">${item.senderName}</a></span>
							</h5>
							<div class="more-info">
								由<a href="javascript:void(0);">${item.draftGroupName}</a>的
								<a href="javascript:void(0);">${item.drafterName}</a>
								于${item.draftDate}起草
								<div class="info-memo">${item.order}</div>
							</div>
						</li>
					</div>
					
				</c:forEach>
				</c:if>
			</div>
 			<div id="tab_3" style="display:none;">
				<c:if test="${ not empty historys }">
				<c:forEach items="${historys}" var="item">
					<div class="order">
						<li class="order-item">
							<h5>
								<a href="./show.do?orderNumber=${item.order}" target="_blank">
									<span class="form-num">${item.order}</span>${item.sourceName}
								</a>
								<span class="info-right">
									于${item.handleDate}签核完毕</span>
							</h5>
							<div class="more-info">
								由<a href="javascript:void(0);">${item.draftGroupName}</a>的
								<a href="javascript:void(0);">${item.drafterName}</a>
								于${item.draftDate}起草
								<div class="info-memo">${item.order}</div>
							</div>
						</li>
					</div>
				</c:forEach>
				</c:if>
			</div>
			<div id="tab_4" style="display:none;">
				<c:if test="${ not empty agents }">
				<div class="total"><span>共${agentCount}条代理事项</span></div>
				<c:forEach items="${agents}" var="item">
					<div class="order">
						<li class="order-item">
							<div class="actions"><a href="javascript:void(0);" class="lnk-ope">快捷操作</a></div>
							<h5>
								<a href="./show.do?orderNumber=${item.order}" target="_blank">
									<span class="form-num">${item.order}</span>${item.sourceName}
								</a>
								<span class="info-right">${item.sendDate}来自
									<a href="javascript:void(0);">${item.senderName}</a></span>
							</h5>
							<div class="more-info">
								由<a href="javascript:void(0);">${item.draftGroupName}</a>的
								<a href="javascript:void(0);">${item.drafterName}</a>
								于${item.draftDate}起草
								<div class="info-memo">${item.order}</div>
							</div>
						</li>
					</div>
				</c:forEach>
				</c:if>
			</div>
			<div id="tab_5" style="display:none;">
				<c:if test="${ not empty traces }">
				<div class="total"><span>共${traceCount}条追踪事项</span></div>
				<c:forEach items="${traces}" var="item">
					<div class="order">
						<li class="order-item">
							<h5>
								<a href="./show.do?orderNumber=${item.order}" target="_blank">
									<span class="form-num">${item.order}</span>${item.sourceName}
								</a>
								<span class="info-right">
									<a href="javascript:void(0);">${item.handlerGroupName}</a>的
									<a href="javascript:void(0);">${item.handlerName}</a>正在处理</span>
							</h5>
							<div class="more-info">
								于<a href="javascript:void(0);">${item.draftDate}</a>起草&nbsp;
								<a href="javascript:void(0);">${item.sendDate}到达处理人</a>
							</div>
						</li>
					</div>
					
				</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
<!-- 	
	<script type="text/javascript">
		handlerIn=function(el){
			el.addClass("enter")
		}
		handlerOut=function(){
			el.removeClass("enter")
		}
		$(".order .order-item").mouseenter(function(){$(this).addClass("enter")}).mouseleave(function(){$(this).removeClass("enter")});
		
	</script>
	 -->
  </body>
</html>
