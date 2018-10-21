function Document_onLoad() {
	Document_OnData();
	$("#LastRow").val((document.getElementById("poItem").rows.length-1)/2);
	
	var textarea1 = document.getElementById('textarea1');
	var pre1 = document.getElementById('pre1');
	document.getElementById('pre1').innerText = document.getElementById('textarea1').value+"1";
	document.getElementById('textarea1').style.height = document.getElementById('pre1').offsetHeight + 'px';

	var textarea3 = document.getElementById('textarea3');
	var pre3 = document.getElementById('pre3');
	document.getElementById('pre3').innerText = document.getElementById('textarea3').value+"1";
	document.getElementById('textarea3').style.height = document.getElementById('pre3').offsetHeight + 'px';
	
	var textarea4 = document.getElementById('textarea4');
	var pre4 = document.getElementById('pre4');
	document.getElementById('pre4').innerText = document.getElementById('textarea4').value+"1";
	document.getElementById('textarea4').style.height = document.getElementById('pre4').offsetHeight + 'px';
	
	var textarea5 = document.getElementById('textarea5');
	var pre5 = document.getElementById('pre5');
	document.getElementById('pre5').innerText = document.getElementById('textarea5').value+"1";
	document.getElementById('textarea5').style.height = document.getElementById('pre5').offsetHeight + 'px';
	
	var textarea6 = document.getElementById('textarea6');
	var pre6 = document.getElementById('pre6');
	document.getElementById('pre6').innerText = document.getElementById('textarea6').value+"1";
	document.getElementById('textarea6').style.height = document.getElementById('pre6').offsetHeight + 'px';
	
	var textarea7 = document.getElementById('textarea7');
	var pre7 = document.getElementById('pre7');
	document.getElementById('pre7').innerText = document.getElementById('textarea7').value+"1";
	document.getElementById('textarea7').style.height = document.getElementById('pre7').offsetHeight + 'px';
	
	var textarea8 = document.getElementById('textarea8');
	var pre8 = document.getElementById('pre8');
	document.getElementById('pre8').innerText = document.getElementById('textarea8').value+"1";
	document.getElementById('textarea8').style.height = document.getElementById('pre8').offsetHeight + 'px';
	
	var textarea9 = document.getElementById('textarea9');
	var pre9 = document.getElementById('pre9');
	document.getElementById('pre9').innerText = document.getElementById('textarea9').value+"1";
	document.getElementById('textarea9').style.height = document.getElementById('pre9').offsetHeight + 'px';
	
	var textarea10 = document.getElementById('textarea10');
	var pre10 = document.getElementById('pre4');
	document.getElementById('pre10').innerText = document.getElementById('textarea10').value+"1";
	document.getElementById('textarea10').style.height = document.getElementById('pre10').offsetHeight + 'px';
	
	var textarea11 = document.getElementById('textarea11');
	var pre11 = document.getElementById('pre11');
	document.getElementById('pre11').innerText = document.getElementById('textarea11').value+"1";
	document.getElementById('textarea11').style.height = document.getElementById('pre11').offsetHeight + 'px';
	
	if(/msie/i.test(navigator.userAgent)) //ie浏览器
	{
			var textarea1 = document.getElementById('textarea1');
			var pre1 = document.getElementById('pre1');
				document.getElementById('textarea1').onpropertychange = function() {	
				document.getElementById('pre1').innerText = document.getElementById('textarea1').value+"1";
				document.getElementById('textarea1').style.height = document.getElementById('pre1').offsetHeight + 'px';
			}
				
			var textarea3 = document.getElementById('textarea3');
			var pre3 = document.getElementById('pre3');
				document.getElementById('textarea3').onpropertychange = function() {	
				document.getElementById('pre3').innerText = document.getElementById('textarea3').value+"1";
				document.getElementById('textarea3').style.height = document.getElementById('pre3').offsetHeight + 'px';
			}
				
			var textarea4 = document.getElementById('textarea4');
			var pre4 = document.getElementById('pre4');
				document.getElementById('textarea4').onpropertychange = function() {	
				document.getElementById('pre4').innerText = document.getElementById('textarea4').value+"1";
				document.getElementById('textarea4').style.height = document.getElementById('pre4').offsetHeight + 'px';
			}
			
			var textarea5 = document.getElementById('textarea5');
			var pre5 = document.getElementById('pre5');
				document.getElementById('textarea5').onpropertychange = function() {	
				document.getElementById('pre5').innerText = document.getElementById('textarea5').value+"1";
				document.getElementById('textarea5').style.height = document.getElementById('pre5').offsetHeight + 'px';
			}
				
			var textarea6 = document.getElementById('textarea6');
			var pre6 = document.getElementById('pre6');
				document.getElementById('textarea6').onpropertychange = function() {	
				document.getElementById('pre6').innerText = document.getElementById('textarea6').value+"1";
				document.getElementById('textarea6').style.height = document.getElementById('pre6').offsetHeight + 'px';
			}
					
			var textarea7 = document.getElementById('textarea7');
			var pre7 = document.getElementById('pre7');
				document.getElementById('textarea7').onpropertychange = function() {	
				document.getElementById('pre7').innerText = document.getElementById('textarea7').value+"1";
				document.getElementById('textarea7').style.height = document.getElementById('pre7').offsetHeight + 'px';
			}
				
			var textarea8 = document.getElementById('textarea8');
			var pre8 = document.getElementById('pre8');
				document.getElementById('textarea8').onpropertychange = function() {	
				document.getElementById('pre8').innerText = document.getElementById('textarea8').value+"1";
				document.getElementById('textarea8').style.height = document.getElementById('pre8').offsetHeight + 'px';
			}
				
			var textarea9 = document.getElementById('textarea9');
			var pre9 = document.getElementById('pre9');
				document.getElementById('textarea9').onpropertychange = function() {	
				document.getElementById('pre9').innerText = document.getElementById('textarea9').value+"1";
				document.getElementById('textarea9').style.height = document.getElementById('pre9').offsetHeight + 'px';
			}
					
			var textarea10 = document.getElementById('textarea10');
			var pre10 = document.getElementById('pre4');
				document.getElementById('textarea10').onpropertychange = function() {	
				document.getElementById('pre10').innerText = document.getElementById('textarea10').value+"1";
				document.getElementById('textarea10').style.height = document.getElementById('pre10').offsetHeight + 'px';
			}
				
			var textarea11 = document.getElementById('textarea11');
			var pre11 = document.getElementById('pre11');
				document.getElementById('textarea11').onpropertychange = function() {	
				document.getElementById('pre11').innerText = document.getElementById('textarea11').value+"1";
				document.getElementById('textarea11').style.height = document.getElementById('pre11').offsetHeight + 'px';
			}
	}
	else   //非ie浏览器，比如Firefox
	{
		var textarea1 = document.getElementById('textarea1');
		var pre1 = document.getElementById('pre1');
		textarea1.oninput = function() {
		    pre1.textContent = textarea1.value+"1";
		    textarea1.style.height = pre1.offsetHeight + 'px';
		}
		
		var textarea3 = document.getElementById('textarea3');
		var pre3 = document.getElementById('pre3');
		textarea3.oninput = function() {
		    pre3.textContent = textarea3.value+"1";
		    textarea3.style.height = pre3.offsetHeight + 'px';
		}
		
		var textarea4 = document.getElementById('textarea4');
		var pre4 = document.getElementById('pre4');
		textarea4.oninput = function() {
		    pre4.textContent = textarea4.value+"1";
		    textarea4.style.height = pre4.offsetHeight + 'px';
		}
		
		var textarea5 = document.getElementById('textarea5');
		var pre5 = document.getElementById('pre5');
		textarea5.oninput = function() {
		    pre5.textContent = textarea5.value+"1";
		    textarea5.style.height = pre5.offsetHeight + 'px';
		}
		
		var textarea6 = document.getElementById('textarea6');
		var pre6 = document.getElementById('pre6');
		textarea6.oninput = function() {
		    pre6.textContent = textarea6.value+"1";
		    textarea6.style.height = pre6.offsetHeight + 'px';
		}
		
		var textarea7 = document.getElementById('textarea7');
		var pre7 = document.getElementById('pre7');
		textarea7.oninput = function() {
		    pre7.textContent = textarea7.value+"1";
		    textarea7.style.height = pre7.offsetHeight + 'px';
		}
		
		var textarea8 = document.getElementById('textarea8');
		var pre8 = document.getElementById('pre8');
		textarea8.oninput = function() {
		    pre8.textContent = textarea8.value+"1";
		    textarea8.style.height = pre8.offsetHeight + 'px';
		}
		
		var textarea9 = document.getElementById('textarea9');
		var pre9 = document.getElementById('pre9');
		textarea9.oninput = function() {
		    pre9.textContent = textarea9.value+"1";
		    textarea9.style.height = pre9.offsetHeight + 'px';
		}
		
		var textarea10 = document.getElementById('textarea10');
		var pre10 = document.getElementById('pre10');
		textarea10.oninput = function() {
		    pre10.textContent = textarea10.value+"1";
		    textarea10.style.height = pre10.offsetHeight + 'px';
		}
		
		var textarea11 = document.getElementById('textarea11');
		var pre11 = document.getElementById('pre11');
		textarea11.oninput = function() {
		    pre11.textContent = textarea11.value+"1";
		    textarea11.style.height = pre11.offsetHeight + 'px';
		}
	}
}

function validateForm() {
	var opcode = document.getElementById("opcode").value;
	//表单状态
	var nid = Input_getValue("node");
	//任务节点
	var token = Input_getValue("token"); 
	if (nid == "020") {
		if ($("#msg").html() != "" ) {
			return "请填写正确的PO单号！";
		}
	}
	if (token == "IEC100") {
		if (!$("#logiticsConfirm1").is(":checked") && !$("#logiticsConfirm2").is(":checked")) {
			return "你还未进行确认操作";
		}
	}
	if (token == "IEC200") {
		if (!$("#erConfirm").is(":checked")) {
			return "你还未进行确认操作";
		}
	}
}	

//从后太往表格塞入数据
function Order_addItem(	
		itemLine, 
		material, 
		materialDescription,
		storageLocation, 
		orderQty, 
		measureUnit,
		equipment,
		dateOfDelivery
		) {	
	var id = $("#LastRow").val();
	var strTable_ID = "FurItem";
	$("#poItem").append("<tr style='display: none;'>"
	+"<td ><input id='itemLine"+id+"' name='itemLine"+id+"'  type='text' readonly='' value='"+itemLine+"'></td>"
	+"<td ><input id='material"+id+"' name='material"+id+"' type='text' readonly='' value='"+material+"'></td>"
	+"<td ><input id='materialDescription"+id+"' name='materialDescription"+id+"' type='text' readonly='' value='"+materialDescription+"'></td>"
	+"<td ><input id='storageLocation"+id+"' name='storageLocation"+id+"' type='text' readonly='' value='"+storageLocation+"'></td>"
	+"<td ><input id='orderQty"+id+"' name='orderQty"+id+"' type='text' readonly='' value='"+orderQty+"'></td>"
	+"<td ><input id='measureUnit"+id+"' name='measureUnit"+id+"' type='text' readonly='' value='"+measureUnit+"'></td>"
	+"<td ><input id='equipment"+id+"' name='equipment"+id+"' type='text' readonly='' value='"+equipment+"'></td>"
	+"<td ><input id='dateOfDelivery"+id+"' name='dateOfDelivery"+id+"' type='text' readonly='' value='"+dateOfDelivery+"'></td>"
	+"</tr>");
	$("#poItem").append("<tr>"
	+"<td>"+itemLine+"</td>"
	+"<td>"+material+"</td>"
	+"<td>"+materialDescription+"</td>"
	+"<td>"+storageLocation+"</td>"
	+"<td>"+orderQty+"</td>"
	+"<td>"+measureUnit+"</td>"
	+"<td>"+equipment+"</td>"
	+"<td>"+dateOfDelivery+"</td>"
	+"</tr>");
	$("#LastRow").val((document.getElementById("poItem").rows.length-1)/2);
}

//输入po单号通过ajax从后台获取po表信息
function getPurchaseOrder(){
	var purchaseOrder = $("#purchaseOrder").val();
	$.ajax({
		type:"post",
		url:"IEC.do?m=getPoInformation",
		data:{  "purchaseOrder" : purchaseOrder   },
		success:function(data){
			var D = eval("(" + data + ")");
			if(D.empty == null){
				$("#msg").text("");
//				document.getElementById('msg').innerHtml = "该PO单号不存在";
				$("#vendor").val(D.vendor);
				$("#address").val(D.address);
				$("#zipCode").val(D.zipCode);
				$("#deliveryTerm").val(D.deliveryTerm);
				$("#vendorContactExt").val(D.vendorContactExt);
				$("#warrantyTerm").val(D.warrantyTerm);
				$("#vendorFax").val(D.vendorFax);
				$("#vendorContact").val(D.vendorContact);
				$("#deliveryPlace").val(D.deliveryPlace);
				$("#goodsReceiveContact").val(D.goodsReceiveContact);
				$("#contactExt").val(D.contactExt);
				$("#dateOfOrder").val(D.dateOfOrder);
				$("#requisitioner").val(D.requisitioner);
				//删除原来的表格中的明细项
				for(i = document.getElementById("poItem").rows.length-1;i>0;i--){
					document.getElementById("poItem").deleteRow(i);
					$("#LastRow").val(0);
				}
	           //从后太往表格塞入数据
				for(var i=0; i<D.poItem.length; i++){
					Order_addItem(	
							D.poItem[i].itemLine, 
							D.poItem[i].material, 
							D.poItem[i].materialDescription,
							D.poItem[i].storageLocation, 
							D.poItem[i].orderQty, 
							D.poItem[i].measureUnit,
							D.poItem[i].equipment,
							D.poItem[i].dateOfDelivery
							) ;
				}
			} else {
				$("#msg").text("该PO单号不存在");
//				document.getElementById('msg').innerHtml = "该PO单号不存在";
				$("#vendor").val(D.vendor);
				$("#address").val(D.address);
				$("#zipCode").val(D.zipCode);
				$("#deliveryTerm").val(D.deliveryTerm);
				$("#vendorContactExt").val(D.vendorContactExt);
				$("#warrantyTerm").val(D.warrantyTerm);
				$("#vendorFax").val(D.vendorFax);
				$("#vendorContact").val(D.vendorContact);
				$("#deliveryPlace").val(D.deliveryPlace);
				$("#goodsReceiveContact").val(D.goodsReceiveContact);
				$("#contactExt").val(D.contactExt);
				$("#dateOfOrder").val(D.dateOfOrder);
				$("#requisitioner").val(D.requisitioner);
				//删除原来的表格中的明细项
				for(i = document.getElementById("poItem").rows.length-1;i>0;i--){
					document.getElementById("poItem").deleteRow(i);
					$("#LastRow").val(0);
				}
			}
			
		},
		error:function(data){ alert("操作失败！");}
		}); 
}