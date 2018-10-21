$(document).ready(function() {
	var error = document.getElementById("error");
	if (error && error.value != "") {
		alert(error.value);
	}
	if (typeof (Document_onLoad) != "undefined") {
		Document_onLoad();
	}
	
	//时间格式
	$('.form_datetime').datetimepicker({
	    //精确到分的时间
	    format: 'yyyy-mm-dd hh:ii',
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 2,
	    forceParse: 0,
	    showMeridian: 1,
	    minuteStep : 30
	});
	$('.form_date').datetimepicker({
	    //年月日
	    format: 'yyyy-mm-dd',
	    language:  'fr',
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 2,
	    minView: 2,
	    forceParse: 0
	});
	$('.form_time').datetimepicker({
	    //时间
	    format:"hh:ii",
	    language:  'fr',
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 1,
	    minView: 0,
	    maxView: 1,
	    forceParse: 0,
	    minuteStep : 1
	});
	$('.form_month').datetimepicker({
	    //年月
	    format: 'yyyy-mm',  
	     weekStart: 1,  
	     autoclose: true,  
	     startView: 3,  
	     minView: 3,  
	     forceParse: false,  
	     language: 'zh-CN'
	});
	$('.form_month_date').datetimepicker({
	    //月日
	    format:"mm-dd",
	    language:  'fr',
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 2,
	    minView: 2,
	    forceParse: 0
	});
	$('.form_year').datetimepicker({
	    format: 'yyyy-mm',  
	     weekStart: 1,  
	     autoclose: true,  
	     startView: 4,  
	     minView: 4,  
	     forceParse: false,  
	     language: 'zh-CN' 
	    
	});

	var newDate = new Date();
	var t = newDate.toJSON(); 
	$('.form_date_start').datetimepicker({
	    //从今天开始
	    format: 'yyyy-mm-dd',
	    language:  'fr',
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 2,
	    minView: 2,
	    forceParse: 0,
	    weekStart: 1, 
	    startDate:new Date(t),
	});
	$('.form_date_end').datetimepicker({
	    //到今天结束
	    format: 'yyyy-mm-dd',
	    language:  'fr',
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 2,
	    minView: 2,
	    forceParse: 0,
	    weekEnd: 1, 
	    endDate:new Date(t),
	});
	
	
});
function ImageButton_onMouseOut(strInput_ID, strImageUrl_onMouseOut)
{
	var objImage = document.getElementById(strInput_ID); 
	if(objImage != null){
		objImage.src = strImageUrl_onMouseOut; 
	}
}

function ImageButton_onMouseOver(strInput_ID, strImageUrl_onMouseOver)
{
	var objImage = document.getElementById(strInput_ID); 
	if(objImage != null){
		objImage.src = strImageUrl_onMouseOver; 
	}
}

var g_GuiLocked = false; 
function GUI_isLocked()
{
	return g_GuiLocked; 
}
function GUI_lock()
{
	g_GuiLocked = true; 
}

function messagebox(strMessage, strTitle)
{
	alert( strMessage );
}

function Table_insertRow(strTable_ID) 
{
	//messagebox( "Table_insertRow Begin" );
	if( GUI_isLocked() ) return; 	
	var objTable = Input_get( strTable_ID );
	if( objTable == null ) return; 
	
   	var objHtmlSource = Input_get( strTable_ID+"_HTML" );
   	if( objHtmlSource == null ) return;
	var strHtmlTmpl = objHtmlSource.innerHTML; 
	var nRow_ID = Table_getNextRowIndex( strTable_ID );
	//===============================================================================
	var objRow = objTable.insertRow( objTable.rows.length );
	objRow.id = strTable_ID+"_"+nRow_ID;

	for ( var i=0; i < objHtmlSource.rows[0].cells.length; i++ ) {
		var objCell = objRow.insertCell(-1);
		objRow.appendChild( objCell );
		strHtmlTmpl = objHtmlSource.rows[0].cells[i].innerHTML; 
		strHtmlTmpl = replace( strHtmlTmpl, "@Row_ID@", nRow_ID );
		objCell.innerHTML = strHtmlTmpl; 
	}
	Input_setValue( strTable_ID+"_LastRow", nRow_ID );
	//messagebox( "nRow_ID: " + nRow_ID);
	return nRow_ID; 
}
function Table_deleteRow(strTable_ID, nRow_ID) 
{
	if( GUI_isLocked() ) return; 
	//messagebox( "Table_deleteRow Begin" );
	var objTable = Input_get( strTable_ID );
	
	if (objTable.rows.length < 1) 
	{
		return;
	}
	
	for (var i=0; i < objTable.rows.length; i++) {
		var objRow = objTable.rows[i]; 
		//messagebox( objRow.id + " == " + nRow_ID );
		if (objRow.id == strTable_ID+"_"+nRow_ID) {
			objTable.deleteRow(i);
			break;
		}
	} // for i
}
function getRow_ID(strTable_ID){
	if( GUI_isLocked() ) return; 
	var objTable = Input_get( strTable_ID );
	if( objTable == null ) return; 
	var objHtmlSource = Input_get(strTable_ID+"_HTML");
   	if( objHtmlSource == null ) return;
   	var strHtmlTmpl = objHtmlSource.innerHTML; 
   	var nRow_ID = Table_getNextRowIndex( strTable_ID );
	//===============================================================================
	// Process New Row and HTML Content
	var objRow = objTable.insertRow( objTable.rows.length );
	objRow.id = strTable_ID+"_"+nRow_ID; 	
	var objCell = objRow.insertCell(-1);
	objCell.innerHTML = replace( strHtmlTmpl, "@Row_ID@", nRow_ID ); 	
	objCell.colSpan = 99; 	
	Input_setValue( strTable_ID+"_LastRow", nRow_ID ); 
	return nRow_ID; 
}
function restorestr(str)
{
	if( str == null ) return ""; 
	str = replace( str, "@13;", "\n" );
	str = replace( str, "@34;", "\"" );
	str = replace( str, "@39;", "'" );
	str = replace( str, "&#34;", "\"" );
	str = replace( str, "&#39;", "'" );
	
	return str; 
}

function Input_get(strInput_ID)
{
	var objInput = document.getElementById(strInput_ID); 
	
	if( objInput == null ) { messagebox( "Input_get: Object ["+strInput_ID+"] cannot be found." ); return; }
	
	return objInput; 
}
function Input_getValue(strInput_ID)
{
	var objInput = document.getElementById(strInput_ID); 
	if( objInput == null ) { messagebox( "Input_getValue: Object ["+strInput_ID+"] cannot be found." ); return; }

	return objInput.value; 
}
function Input_setValue(strInput_ID, strValue)
{

	var objInput = document.getElementById(strInput_ID); 
	if( objInput == null ) { messagebox( "Input_setValue: Object ["+strInput_ID+"] cannot be found." ); return; }
	objInput.value = strValue; 
}

function Input_setReadonlyValue(strInput_ID, strValue)
{
	var objInput = document.getElementById(strInput_ID); 
	
	if( objInput == null ) { messagebox( "Input_setValue: Object ["+strInput_ID+"] cannot be found." ); return; }
	objInput.readonlyValue = strValue; 
}

function Select_trim(strInput_ID) // trim, left select value
{
	var objInput = Input_get( strInput_ID );
	var strValue = objInput.value; 
	var i = 0 ;
	
	while( i < objInput.options.length ) {
		if( objInput.options[i].value == strValue ) { i++; continue; } 
		objInput.options.remove( i ); 
	} // for i
}

function Input_setTimeSelect(strInput_ID, strValue, bReadonly)
{
	Input_setValue( strInput_ID, strValue );
	var date = strValue.substr(0,10);
	//alert("date: "+date);
	var hour = strValue.substr(11,2);
	if( hour == "" ) hour = "00";
	//alert("hour: "+hour);
	var min = strValue.substr(14,2);
	if( min == "" ) min = "00";
	//alert("min: "+min);
	Input_setValue( strInput_ID+"_date", date );
	Input_setValue( strInput_ID+"_hour", hour );
	Input_setValue( strInput_ID+"_min", min );
}

function Table_getNextRowIndex(strTable_ID) 
{
	//messagebox( "Table_getNextRowIndex Begin" );
	//alert("strTable_ID: "+strTable_ID);
	var objTable = Input_get(strTable_ID);
	var nMax_ID = -1; 

	//alert("objTable.rows.length: "+objTable.rows.length);
	for(var i=0; i < objTable.rows.length; i++ ) {
		var objRow = objTable.rows[i]; 
		//messagebox( objRow.id );
		var nRow_ID = parseInt(replace( objRow.id, strTable_ID+"_", "" ));
		//messagebox( nRow_ID );
		if( nRow_ID > nMax_ID ) nMax_ID = nRow_ID; 
	} // for i
	
	//messagebox( nMax_ID );
	return nMax_ID+1; 
}

function replace(str, srcstr, dstsrc) 
{
	while( str.indexOf(srcstr) != -1 ) {
		str = str.replace( srcstr, dstsrc ); 
	}
	return str; 
}

function setDatetime(cmpId)
{
	var date = document.getElementById(cmpId + "_date").value;
	var hour = document.getElementById(cmpId + "_hour").value;
	var min = document.getElementById(cmpId + "_min").value;
	
	var datetime = document.getElementById(cmpId);
	if (date != "") datetime.value = date + " " + hour + ":" + min;
	else alert("Please select date.");
}


function Window_openOnlyOne(theURL, winName, features) 
{ 
	var openWindow = null;
	if (openWindow != undefined && isOpen()) openWindow.close();
	openWindow = window.open("", winName, features);
	openWindow.location.replace(theURL);
}

function getEvent(){ //同时兼容ie和ff的写法
	if(document.all) return window.event;
	func=getEvent.caller;
	while(func!=null){
		var arg0=func.arguments[0];
		if(arg0){
			if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){
				return arg0;
			}
		}
		func=func.caller;
	}
	return null;
}

function getElement(){
	var event = getEvent();
	if(event){
		return element = event.srcElement || event.target;
	}else
		return null;
}

function Document_onMouseOver(){
	var element = getElement();
	if(element){
		var objRow = element.parentElement; 
		objRow.className = "DialogLineHover";
	}	
}
function Document_onMouseOut(){
	var element = getElement();
	if(element){
		var objRow = element.parentElement; 
		objRow.className = "DialogLine";
	}
}

function validate() {
	if (typeof (validateForm) != "undefined") {
		var x = validateForm();
		if (x) {
			if (x != "-1") {
				alert(x);
			}
			return false;
		}
	}
	return true;
}

function onClose() {
	if (window.opener != null) {
		try {
			var x = false;
			if (window.opener.homeReload) {
				x = true;
				window.opener.homeReload();
				window.open('', '_self');
				window.close();
			}
			if(!x)
				throw new Error();
		} catch (e) {
			var iframe = document.createElement('iframe');
			iframe.style.display = 'none';
			iframe.src = 'http://myivo.ivo.com.cn/Arcadia/pages/web/proxy.jsp#homeReload';
			loadfn = function() {
				window.open('', '_self');
				window.close();
			};
			if (iframe.attachEvent) {
				iframe.attachEvent('onload', loadfn);
			} else {
				iframe.onload = loadfn;
			}
			document.body.appendChild(iframe);
		}
	} else {
		window.open('', '_self');
		window.close();
	}
}

function help() {
	alert("如对签核存在疑惑,可与IT徐如蓉联系,分机18330,邮箱rurongXu@ivo.com.cn!");
}
function onPrint() {
	var order = document.getElementById("order").value;
	window.open("print.do?order=" + order, "", "");
	return;
}
function menubar_onclick(x) {
	document.getElementById("opcode").value = x;
	document.getElementById("orderForm").action = "submit.do";
	if(x == 7 && !validate()){
		return;
	}
	if (x == 0 || x == 1 || x == 2 || x == 9) {
		if (x == 0  && !validate()) {
			return;
		}

		var message = MessageBoxFactory.makeMessageBox("comment_panel",
				"Comment:", function() {
					var comment = message.getMessage();
					if (!comment || comment == "") {
						if (x == 0) {
							comment = "Agree(同意)";
						} else {
							return "请输入意见!";
						}
					}
					document.getElementById("comment").value = comment;
					document.getElementById("menubar").style.display = "none";
					document.getElementById("orderForm").submit();
				});
		message.show();
		
	} else if (x == 3) {
		var tree = XTreeFactory.makeTree("handover_panel", "交办", function() {
			var comment = tree.getComment();
			if (!comment || comment == "") {
				return "请输入意见!";
			}
			var nodes = tree.getSelectedNodes();
			if (nodes.length == 0) {
				return "请选择您所需要交办的人!";
			} else {
				var userList = [];
				for ( var i = 0; i < nodes.length; i++) {
					userList.push(nodes[i].id);
				}
				document.getElementById("userList").value = userList;
				document.getElementById("comment").value = comment;
				document.getElementById("menubar").style.display = "none";
				document.getElementById("orderForm").submit();
			}
		}, {
			"root" : document.getElementById("currentGroup").value,
			"url" : "org.do",
			"displayItems" : [ "id", "name", "ename" ],
			"multi" : false,
			"onlySelf" : true,
			"comment" : true
		});
		tree.show();
	} else if (x == 4) {
		var tree = XTreeFactory.makeTree("recosign_panel", "征询意见", function() {
			var comment = tree.getComment();
			if (!comment || comment == "") {
				return "请输入意见!";
			}
			var nodes = tree.getSelectedNodes();
			if (nodes.length == 0) {
				return "请选择您所需要征询意见的人!";
			} else {
				var userList = [];
				for ( var i = 0; i < nodes.length; i++) {
					userList.push(nodes[i].id);
				}
				document.getElementById("userList").value = userList;
				document.getElementById("comment").value = comment;
				document.getElementById("menubar").style.display = "none";
				document.getElementById("orderForm").submit();
			}
		}, {
			"displayItems" : [ "id", "name", "ename" ],
			"comment" : true,
			"onlySelf" : true
		});
		tree.show();
	}else if (x == 10) {
			document.getElementById("opcode").value = 4;
			var tree = XTreeFactory.makeTree("recosign_panel", "征询意见", function() {
			var comment = tree.getComment();
			if (!comment || comment == "") {
				return "请输入意见!";
			}
			var nodes = tree.getSelectedNodes();
			if (nodes.length == 0) {
				return "请选择您所需要征询意见的人!";
			} else {
				var userList = [];
				for ( var i = 0; i < nodes.length; i++) {
					userList.push(nodes[i].id);
				}
				document.getElementById("userList").value = userList;
				document.getElementById("comment").value = comment;
				document.getElementById("menubar").style.display = "none";
				document.getElementById("orderForm").submit();
			}
		}, {
			"displayItems" : [ "id", "name", "ename" ],
			"comment" : true,
			"multi" : true,
			"onlySelf" : true
		});
		tree.show();
	} else if (x == 5 || x == 7) {
		document.getElementById("menubar").style.display = "none";
		document.getElementById("orderForm").submit();
	} else if (x == 6) {
		var r = confirm("确定要删除此签核单？");
		if (r) {
			document.getElementById("menubar").style.display = "none";
			document.getElementById("orderForm").submit();
		} else {
			document.getElementById("menubar").style.display = "";
		}
	}
}

function LTrim(str){ //去掉字符串 的头空格 
	var i; 
	for(i=0;i<str.length; i++) { 
		if(str.charAt(i)!=" ") break;  
	} 
	str = str.substring(i,str.length); 
	return str; 
} 

function RTrim(str){ //去掉字符串的尾空格 
	var i; 
	for(i=str.length-1;i>=0;i--){ 
		if(str.charAt(i)!=" "&&str.charAt(i)!=" ") break; 
	} 
	str = str.substring(0,i+1); 
	return str; 
} 
function Trim(str){ 
	return LTrim(RTrim(str)); 
} 

function Order_onItemInvalid(strView_ID, nRow_ID, strInput_ID, strInput_Label, strMessage ){
	objFocus = Input_get(strInput_ID);
	if( strMessage == null ) strMessage = " should not be empty."; 
	return "Item field ["+strInput_Label+"] in Line["+nRow_ID+"]"+strMessage;
}

function ActualHour_onLabel(nRow_ID)
{
}


function NewDate(str) { 
	str = str.split('-'); 
	var date = new Date(); 
	date.setUTCFullYear(str[0], str[1] - 1, str[2]); 
	date.setUTCHours(0, 0, 0, 0); 
	return date; 
} 

//指定日期向后推n天
function date2str(date,n){
	var s, d, t, t2;
	t = NewDate(date).getTime();
	t2 = n * 1000 * 3600 * 24;
	t+= t2;
	d = new Date(t);
	s = d.getUTCFullYear() + "-";
	s += ("00"+(d.getUTCMonth()+1)).slice(-2) + "-";
	s += ("00"+d.getUTCDate()).slice(-2);
	return s;
} 

function Radio_getValue(strInput_ID){
	var obj = document.getElementsByName(strInput_ID); 
	
	for ( var i=0; i<obj.length; i++ ){  
		if ( obj[i].checked )  return obj[i].value; 
	}	
}

//判断结算周期开始日期
function fromDate(date){
	var fromDate;
	var strDate = date.split("-")[2];
	if(strDate.substr(0,1) == 0)
		strDate = strDate.substr(1,1);
	var strMonth = date.split("-")[1];
	if(strMonth.substr(0,1) == 0)
		strMonth = strMonth.substr(1,1);
	var date_ = parseInt(strDate);
	var month_ = parseInt(strMonth);
	var year_ = parseInt(date.split("-")[0]);

	if(date_ < 26){
		if(month_ == 1){
			year_ = year_ - 1;
			fromDate = year_ + "-12-26";
		}else{
			fromDate = year_ + "-"+(month_-1)+"-26";
		}
	}else{
		fromDate = year_ + "-"+month_+"-26";
	}
	return fromDate;
}

//获取两个日期之间相差的天数
function getDiffDate(date1,date2){
	var date1_ = NewDate(date1).getTime();
	var date2_ = NewDate(date2).getTime();
	var n = (date1_ - date2_)/(1000*60*60*24);
	return n;
}

function Calendar_bind(objInput){
	if( typeof(objInput.Calendar) != "undefined" ) return; 
	var objCalendarParams = new Array();
	objInput.Calendar = objCalendarParams; 
	objCalendarParams["inputField"] = objInput.id;
	objCalendarParams["button"] = objInput.id;
	objCalendarParams["ifFormat"] = "%Y-%m-%d";  
	Calendar_setup( objCalendarParams );	
	objInput.onmousedown(); 
}
/**
 * 
 */
var deptArray = new Array();
function showTree(nRow_ID,strID){
	deptArray = [];
	$(".add").remove("");
	var cls="add";
	
	jQuery.ajax({
		 url : "./org.do?method=load&root=10000000", 
		 type : 'get',
		 dataType : 'text',
		 success:function(data){
			 var ary = eval(data);
	          for(i=ary.length-1;i>=0;i--){
	        	  $("#DeputyTable").after("<tr class='"+cls+"' onclick=Deputy_onClick('"+strID+"','"+ary[i].id+"','"+ary[i].name+"','"+ary[i].isParent+"','"+nRow_ID+"')><td  class='dept'></td><td>"+ary[i].name+"</td></tr>");
	          }
	          $("#DeputyModal").modal("show");
	          
	          
	          $("#DeputyModal").on('shown.bs.modal', function () {
	        	  $("#deputyinput").focus().tooltip('show');
	          });
	          
	          $("#DeputyModal").keydown(function(event){
	              if(event.keyCode === 13){
	 	        	 var con = $("#deputyinput").val();
		        	 if(con!="")queryEmpAction(strID,con,nRow_ID);
	              }
	          });
	          
	          $("#deputybtn").click(function(){
	        	 var con = $("#deputyinput").val();
	        	 if(con!="")queryEmpAction(strID,con,nRow_ID);
	          });
	          
	          $("#returnbtn").click(function(){
	        	  if(deptArray.length==1) {showTree(nRow_ID);deptArray.pop();}
	        	  else if(deptArray.length > 1 ){
	        	  id = deptArray[deptArray.length-2].id;//当前部门的前一个部门
	        	  name = deptArray[deptArray.length-2].name;
	        	  isParent = deptArray[deptArray.length-2].isParent;
	        	  nRow_ID = deptArray[deptArray.length-2].nRow_ID;
	        	  
	      		$(".add").remove("");
	    		jQuery.ajax({
	    			 url : "./org.do?method=load&root="+id, 
	    			 type : 'get',
	    			 dataType : 'text',
	    			 async:false, //设置成同步
	    			 success:function(data){
	    				 var icon ="";
	    				 var ary = eval(data);
	    		         for(i=ary.length-1;i>=0;i--){
	    		        	 if(ary[i].isParent) icon="dept";
	    		        	 else {
	    		        		 if(ary[i].iconSkin=="leader")icon="leader_ico";
	    			        	 else icon="user_ico";
	    		        		 }
	    		        	  $("#DeputyTable").after("<tr class='add'  onclick=\"Deputy_onClick('"+strID+"','"+ary[i].id+"','"+ary[i].name+"','"+ary[i].isParent+"','"+nRow_ID+"')\"><td class='"+icon+"'></td><td>"+ary[i].name+"</td></tr>");
	    		          }
	    		          $("#DeputyModal").modal("show");
	    		          deptArray.pop();
	    		      }
	    		});
	          }
        	 });
	      }
	});
}

function Deputy_onClick(strID,id,name,isParent,nRow_ID){
	if(isParent=="false")
	 {
		Input_setValue( strID, "["+id+"]"+name );
		$("#DeputyModal").modal("hide");
	 }
	else if(isParent=="true"){
		deptArray.push({"id":id,"name":name,"isParent":isParent,"nRow_ID":nRow_ID});
		$(".add").remove("");
		
		jQuery.ajax({
			 url : "./org.do?method=load&root="+id, 
			 type : 'get',
			 dataType : 'text',
			 success:function(data){
				 var icon ="";
				 var ary = eval(data);
		         for(i=ary.length-1;i>=0;i--){
		        	 if(ary[i].isParent) icon="dept";
		        	 else {
		        		 if(ary[i].iconSkin=="leader")icon="leader_ico";
			        	 else icon="user_ico";
		        		 }
		        	  $("#DeputyTable").after("<tr class='add'  onclick=\"Deputy_onClick('"+strID+"','"+ary[i].id+"','"+ary[i].name+"','"+ary[i].isParent+"','"+nRow_ID+"')\"><td class='"+icon+"'></td><td>"+ary[i].name+"</td></tr>");
		          }
		          $("#DeputyModal").modal("show");
		      }
		});
	}
}
function queryEmpAction(strID,con,nRow_ID){
	jQuery.ajax({
		url : "./org.do?method=query", 
		 type : 'post',
		 data : {root:10000000,query:con},
		 dataType : 'text',
		 success:function(data){
			 $(".add").remove("");
			 var cls="add";
			 var ary = eval(data);
			 for(i=ary.length-1;i>=0;i--){
	        	 if(ary[i].isParent) icon="dept";
	        	 else {
	        		 if(ary[i].iconSkin=="leader")icon="leader_ico";
		        	 else icon="user_ico";
	        		 }
	        	 $("#DeputyTable").after("<tr class='add'  onclick=Deputy_onClick('"+strID+"','"+ary[i].id+"','"+ary[i].name+"','"+ary[i].isParent+"','"+nRow_ID+"')><td class='"+icon+"'></td><td>"+ary[i].name+"</td></tr>");
	          }
			 
		 }
	});
}


function showTree2(object,strID){
	var node=document.getElementById(object.id)
	$(".add").remove("");
	var cls="add";
	jQuery.ajax({
		 url : "./org.do?method=load&root="+strID, 
		 type : 'get',
		 dataType : 'text',
		 success:function(data){
			 var ary = eval(data);
	          for(i=ary.length-1;i>=0;i--){
	        	  $("#DeputyTable").after("<tr class='"+cls+"' onclick=Deputy_onClick2('"+strID+"','"+ary[i].id+"','"+ary[i].name+"','"+ary[i].isParent+"','"+node.id+"')><td  class='user_ico'></td><td>"+ary[i].name+"</td></tr>");
	          }
	          $("#DeputyModal").modal("show");
	          
	          
	          $("#DeputyModal").on('shown.bs.modal', function () {
	        	  $("#deputyinput").focus().tooltip('show');
	          });
	          
	          $("#DeputyModal").keydown(function(event){
	              if(event.keyCode === 13){
	 	        	 var con = $("#deputyinput").val();
		        	 if(con!="")queryEmpAction(strID,con,node.id);
	              }
	          });
	          
	          $("#deputybtn").click(function(){
	        	 var con = $("#deputyinput").val();
	        	 if(con!="")queryEmpAction(strID,con,node.id);
	          });
	      }
	});
}

function Deputy_onClick2(strID,id,name,isParent,object){
	$(".add").remove("");
	$("#"+object).val(id);
	var name_ = object.replace("_FK","Name");
	$("#"+name_).val(name);
	$("#DeputyModal").modal("hide");
}

function queryEmpAction(strID,con,object){
	jQuery.ajax({
		url : "./org.do?method=query", 
		 type : 'post',
		 data : {root:strID,query:con},
		 dataType : 'text',
		 success:function(data){
			 $(".add").remove("");
			 var cls="add";
			 var ary = eval(data);
			 for(i=ary.length-1;i>=0;i--){
	        	 if(ary[i].isParent) icon="dept";
	        	 else {
	        		 if(ary[i].iconSkin=="leader")icon="leader_ico";
		        	 else icon="user_ico";
	        		 }
	        	 $("#DeputyTable").after("<tr class='add'  onclick=Deputy_onClick2('"+strID+"','"+ary[i].id+"','"+ary[i].name+"','"+ary[i].isParent+"','"+object+"')><td class='"+icon+"'></td><td>"+ary[i].name+"</td></tr>");
	          }
			 
		 }
	});
}