function Pop(id, title, settings) {
	this.id = id;
	this.title = title ? title : "无标题窗口";
	if (settings) {
		this.fixed = settings.fixed;
		this.basicOperate = settings.basicOperate;
		if (this.basicOperate) {
			this.ok = settings.ok ? settings.ok : function() {
				return "请定义事件！"
			};
		}
	} else {
		this.fixed = false;
		this.basicOperate = false;
	}
}
Pop.prototype.hide = function() {
	var outer = document.getElementById(this.id + "_pop");
	if (outer)
		outer.style.display = "none";
	var mask = document.getElementById(this.id + "_mask");
	if (mask)
		mask.style.display = "none";
}
Pop.prototype.show = function() {
	var pop = document.getElementById(this.id);
	if (pop) {
		var outer = document.getElementById(this.id + "_pop");
		if (outer) {
			outer.style.display = "";
		} else {
			var xc = this;
			outer = XQuery.makeElement("div", "pop", {
				"id" : this.id + "_pop"
			});
			if (pop.style.width)
				outer.style.width = pop.style.width;
			else
				outer.style.width = "180px";
			if (pop.style.height)
				outer.style.height = pop.style.height;
			else
				outer.style.height = "180px";
			var head = XQuery.makeElement("div", "pop_title", {
				"innerHTML" : this.title,
				"onselectstart" : "return false;"
			});
			var close = XQuery.makeElement("a", "pop_title_close", {
				"href" : "javascript:void(0);",
				"title" : "关闭",
				"hideFocus" : "true",
				"onclick" : function() {
					xc.hide();
				}
			});
			head.appendChild(close);
			head.style.height = "25px";
			outer.appendChild(head);
			outer.style.height = parseInt(outer.style.height) + 25 + "px";
			if (!this.fixed) {
				head.onmousedown = function(event) {
					var x, y, left, top;
					if (window.event != null) {
						event = window.event;
					}
					if (event.button == 0 || event.button == 1) {
						outer.style.opacity = 0.6;
						outer.style.filter = "Alpha(opacity=60)";
						x = event.screenX;
						y = event.screenY;
						left = parseInt(outer.style.left);
						top = parseInt(outer.style.top);
						if (outer.setCapture) {
							outer.setCapture();
						} else if (window.addEventListener) {
							window.addEventListener("mousemove",
									document.onmousemove, false);
							window.addEventListener("mouseup",
									document.onmouseup, false);
						}
						document.onmousemove = function(evt) {
							if (window.event != null) {
								evt = window.event;
							}
							if (evt.button == 0 || evt.button == 1) {
								var currentLeft = left + evt.screenX - x;
								if (currentLeft <= 0) {
									outer.style.left = 0;
								} else {
									if (currentLeft >= (document.documentElement.clientWidth - parseInt(outer.style.width))) {
										outer.style.left = (document.documentElement.clientWidth - parseInt(outer.style.width))
												+ "px";
									} else {
										outer.style.left = currentLeft + "px";
									}
								}
								var currentTop = top + evt.screenY - y;
								if (currentTop <= 0) {
									outer.style.top = 0;
								} else {
									if (currentTop >= (document.documentElement.clientHeight - parseInt(outer.style.height))) {
										outer.style.top = (document.documentElement.clientHeight - parseInt(outer.style.height))
												+ "px";
									} else {
										outer.style.top = currentTop + "px";
									}
								}
							}
						};
						document.onmouseup = function() {
							if (outer.releaseCapture) {
								outer.releaseCapture(true);
							} else if (window.removeEventListener) {
								window.removeEventListener("mousemove",
										document.onmousemove);
								window.removeEventListener("mousemove",
										document.onmouseup);
							}
							outer.style.opacity = 1;
							outer.style.filter = "Alpha(opacity=100)";
							document.onmousemove = null;
							document.onmouseup = null;
						};
					}
				};
			}
			var main = XQuery.makeElement("div", null);
			main.appendChild(pop);
			outer.appendChild(main);
			if (this.basicOperate) {
				var operate = XQuery.makeElement("div", "pop_operate");
				var ok = XQuery.makeElement("a", null, {
					"href" : "javascript:void(0);",
					"innerHTML" : "确定",
					"title" : "确定",
					"hideFocus" : "true",
					"onclick" : function() {
						if (xc.ok) {
							var x = xc.ok();
							if (x) {
								alert(x);
							} else {
								xc.hide();
							}
						} else
							xc.hide();
					}
				});
				var cancle = XQuery.makeElement("a", null, {
					"href" : "javascript:void(0);",
					"title" : "取消",
					"innerHTML" : "取消",
					"hideFocus" : "true",
					"onclick" : function() {
						xc.hide();
					}
				});
				var operatePanel = XQuery.makeElement("div",
						"pop_operatePanel", {
							"hidefocus" : "true"
						});
				operatePanel.appendChild(ok);
				operatePanel.appendChild(cancle);
				operate.appendChild(operatePanel);
				operate.style.height = "32px";
				outer.appendChild(operate);
				outer.style.height = parseInt(outer.style.height) + 32 + "px";
			}
			outer.style.display = "none";
			document.body.appendChild(outer);
			if (pop.style.display = "none")
				pop.style.display = "";
		}
		var mask = document.getElementById(this.id + "_mask");
		if (!mask) {
			var browser = XQuery.getBrowser();
			if (browser.browser == "msie" && browser.version == "6.0") {
				mask = XQuery.makeElement("iframe", "mask", {
					"src" : "pages/common/background.html",
					"id" : this.id + "_mask"
				});
			} else {
				mask = XQuery.makeElement("div", "mask", {
					"id" : this.id + "_mask"
				});
			}
			mask.style.display = "none";
			document.body.appendChild(mask);
		}
		var width = Math.max(document.documentElement.scrollWidth,
				document.documentElement.clientWidth);
		var height = Math.max(document.documentElement.scrollHeight,
				document.documentElement.clientHeight);
		mask.style.width = width + "px";
		mask.style.height = height + "px";
		mask.style.display = "";
		outer.style.top = (document.documentElement.clientHeight - parseInt(outer.style.height))
				/ 2 + "px";
//		outer.style.left = (document.documentElement.clientWidth - parseInt(outer.style.width))
//		/ 2 + "px";
		if(document.documentElement.clientWidth - parseInt(outer.style.width)>0){
			outer.style.left = (document.documentElement.clientWidth - parseInt(outer.style.width))
					/ 2 + "px";	
		}else{
			outer.style.left = (parseInt(outer.style.width) - document.documentElement.clientWidth)
					/ 2 + "px";
		}
		outer.style.display = "";
	} else
		alert("No element!");
}
