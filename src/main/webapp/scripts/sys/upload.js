function onUpload() {
	var isNewOrder = document.getElementById("isNewOrder").value;
	if (isNewOrder) {
		if (isNewOrder == "old") {
			var order = document.getElementById("orderNumber").value;
			if (order) {
				var framedoc = document.getElementById("uploadFrame").contentWindow.document;
				if (framedoc) {
					framedoc.getElementById("order").value = order;
					framedoc.getElementById("upload").click();
				} else
					alert("Error!");
			} else
				alert("Error!");
		} else
			alert("系统检测到您的签核单还没保存，为了保证您的数据不丢失，请先保存签核单再上传附件！");
	} else
		alert("Error!");
}
function refreshAttachPanel() {
	$.post("attach.do", {
		order : document.getElementById("orderNumber").value
	}, function(data) {
		if (data != "") {
			var panel = document.getElementById("attachPanel");
			if (panel) {
				panel.innerHTML = "";
				for ( var i = 0; i < data.length; i++) {
					var div = XQuery.makeElement("div", "attachRow", {
						"id" : "attach_" + data[i].id
					});
					var iconSpan = XQuery.makeElement("span", "attachIcon");
					var attachIcon = XQuery.makeElement("a", null, {
						"href" : "download.do?id=" + data[i].id,
						"target" : "_blank"
					});
					iconSpan.appendChild(attachIcon);
					div.appendChild(iconSpan);
					var nameSpan = XQuery.makeElement("span");
					var attachName = XQuery.makeElement("a", null, {
						"href" : "download.do?id=" + data[i].id,
						"innerHTML" : data[i].name,
						"target" : "_blank"
					});
					nameSpan.appendChild(attachName);
					div.appendChild(nameSpan);

					var sizeSpan = XQuery.makeElement("span", "attachSize", {
						"innerHTML" : "(" + data[i].size + ")"
					});
					div.appendChild(sizeSpan);

					var rightSpan = XQuery.makeElement("span", "attachRight", {
						"innerHTML" : data[i].user
					});

					var currentUser = document.getElementById("currentUser").value;
					var token = document.getElementById("token").value;
					if(token != null && token != "" && currentUser != null &&
							data[i].user.indexOf(currentUser) > 0){
						var attachDelete = XQuery.makeElement("a", null, {
							"href" : "javascript:void(0);",
							"onclick" : function(event) {
								var obj;
								if (window.event != null) {
									obj = window.event.srcElement;
								} else
									var obj = event.target;
								deleteAttach(obj.parentNode.parentNode);
							}
						});
						rightSpan.appendChild(attachDelete);
					}else{
						var fillDelete = XQuery.makeElement("div", "fillDelete", {
							"innerHTML" : "&nbsp;"
						});
						rightSpan.appendChild(fillDelete);
					}
					
					div.appendChild(rightSpan);

					panel.appendChild(div);
				}
			} else
				alert("Error!");
		}
	}, "json");
}
function deleteAttach(x) {
	if (x.id) {
		var id = parseInt(x.id.replace("attach_", ""));
		if (!isNaN(parseInt(id))) {
			$.post("delete.do", {
				id : id
			}, function(data) {
				if (data != "") {
					if (data == "success") {
						x.parentNode.removeChild(x);
					} else
						alert(data);
				}
			}, "text");
		} else
			alert("Error!");
	} else
		alert("Error!");
}