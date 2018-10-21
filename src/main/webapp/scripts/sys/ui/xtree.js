var XTreeFactory = {
	trees : {},
	size : 0,
	maxElementsInMemory : 100,
	memoryStoreEvictionPolicy : "FIFO",
	makeTree : function(id, title, ok, settings) {
		if (this.trees[id])
			return this.trees[id];
		else {
			var tree = new XTree(id, title, ok, settings);
			this.trees[id] = tree;
			this.size++;
			return tree;
		}
	},
	getTree : function(id) {
		return this.trees[id];
	}
};
function XTree(id, title, ok, settings) {
	this.id = id;
	this.title = title ? title : "无标题窗口";
	this.ok = ok ? ok : function() {
		return "请定义事件！";
	};
	this.nodes = [];
	if (settings) {
		if (settings.url && settings.root) {
			this.root = settings.root;
			this.url = settings.url;
		} else {
			this.root = "10000000";
			this.url = "org.do";
		}
		this.multi=false; //指定单选
		this.onlySelf = false; //指定能选择自己
		if(typeof(settings.multi) !="undefined")
		this.multi = settings.multi;
		if(typeof(settings.onlySelf) !="undefined")
		this.onlySelf = settings.onlySelf;
		this.displayItems = settings.displayItems ? settings.displayItems
				: [ "id","name" ];
		this.comment = settings.comment;
	} else {
		this.root = "10000000";
		this.url = "org.do";
		this.multi = false;//指定单选
		this.onlySelf = false; //指定能选择自己
		this.displayItems = [ "id", "name", "ename" ];
		this.comment = false;
	}
}
XTree.prototype.hide = function() {
	this.pop.hide();
}
XTree.prototype.show = function() {
	var panel = document.getElementById(this.id);
	if (!panel) {
		panel = XQuery.makeElement("div", null, {
			"id" : this.id
		});
		panel.style.width = "500px";
		panel.style.height = this.comment ? "440px" : "320px";
		var position = XQuery.makeElement("div", "tree_panel");
		position.style.width = panel.style.width;
		position.style.height = panel.style.height;
		panel.appendChild(position);

		if (this.comment) {
			var commentPanel = XQuery.makeElement("div", "tree_comment", {
				"innerHTML" : "<label>Comment:</label>"
			});
			var textarea = XQuery.makeElement("textarea", null, {
				"id" : this.id + "_comment"
			});
			commentPanel.appendChild(textarea);
			position.appendChild(commentPanel);
		}

		var x = this;
		var left = XQuery.makeElement("div", "left");
		var search = XQuery.makeElement("div", "search");
		var searchpanel = XQuery.makeElement("div", "search_panel");
		var searchContent = XQuery.makeElement("input", null, {
			"type" : "text",
			"id" : this.id + "_search",
			"autocomplete" : "off",
			"onkeydown" : function(event) {
				if (window.event != null) {
					event = window.event;
				}
				if (event.keyCode == 13) {
					this.parentNode.childNodes[1].click();
					return;
				}
			},
			"onkeyup" : function(event) {
				if (window.event != null) {
					event = window.event;
				}
				if (event.keyCode == 8) {
					if (this.value == "") {
						this.parentNode.childNodes[1].click();
						return;
					}
				}
			}
		});
		var searchButton = XQuery.makeElement("a", null, {
			"href" : "javascript:void(0);",
			"title" : "查询",
			"hideFocus" : "true",
			"onclick" : function() {
			var query = document.getElementById(x.id + "_search").value;
			if (query && query != "") {
				document.getElementById(x.id + "_tree").style.display = "none";
				var queryTree = document.getElementById(x.id + "_query");
				queryTree.innerHTML = "查询中。。。";
				queryTree.style.display = "";
				$.post(x.url + "?method=query", {
					"query" : query,
					"root" : x.root
				}, function(data) {
					for( var i=0; i<data.length;i++ ){
						data[i].name = data[i].id + " "+data[i].name;
					}
					if (data == "")
						queryTree.innerHTML = "查找到：<br>没有符合条件的人。";
					else {
						queryTree.innerHTML = "";
						$.fn.zTree.init($("#" + x.id + "_query"), {
							view : {
								selectedMulti : false,
								autoCancelSelected : false,
								showTitle : false,
								dblClickExpand : false,
								showLine : false
							},
							callback : {
								onClick : selectNode
							}
						}, data);
					}
				}, "json");
			} else {
				document.getElementById(x.id + "_tree").style.display = "";
				document.getElementById(x.id + "_query").style.display = "none";
			}
			}
		});
		searchpanel.appendChild(searchContent);
		searchpanel.appendChild(searchButton);
		search.appendChild(searchpanel);
		left.appendChild(search);
		var ztree = XQuery.makeElement("div", "ztree_panel");
		var ul = XQuery.makeElement("ul", "ztree", {
			"id" : this.id + "_tree"
		});
		var ulquery = XQuery.makeElement("ul", "ztree", {
			"id" : this.id + "_query"
		});
		ulquery.style.display = "none";
		ztree.appendChild(ul);
		ztree.appendChild(ulquery);
		left.appendChild(ztree);
		position.appendChild(left);
		var center = XQuery.makeElement("div", "center");
		var imgpanel = XQuery.makeElement("div", "center_arrow");
		var img = XQuery.makeElement("img", null, {
			"src" : "./scripts/sys/ui/css/images/arrow_left.gif"
		});
		imgpanel.appendChild(img);
		center.appendChild(imgpanel);
		position.appendChild(center);
		var right = XQuery.makeElement("div", "right");
		var result = XQuery.makeElement("div", "result_panel", {
			"id" : this.id + "_result"
		});
		right.appendChild(result);
		position.appendChild(right);
		panel.style.display = "none";
		document.body.appendChild(panel);
		this.treeObj = $.fn.zTree.init($("#" + this.id + "_tree"), {
			view : {
				selectedMulti : false,
				autoCancelSelected : false,
				showTitle : false,
				dblClickExpand : false
			},
			async : {
				autoParam : [ "id" ],
				enable : true,
				url : this.url + "?method=load",
				otherParam : [ "root", this.root ]
			},
			callback : {
				onClick : selectNode
			}
		});

	}
	if (!this.pop) {
		this.pop = new Pop(this.id, this.title, {
			"basicOperate" : true,
			"ok" : this.ok
		});
	}
	this.pop.show();
	function selectNode(event, treeId, treeNode) {
		if(treeNode.name.indexOf(" ") >0)
		treeNode.name = treeNode.name.split(" ")[1];
		if(x.onlySelf && uid==treeNode.id){
			alert("无法选择自己！");
			return;
		}else if (treeNode.isParent) {
			x.treeObj.expandNode(treeNode, !treeNode.open);
		} else {
			if (!x.multi) {
				x.nodes = [];
				x.nodes.push(treeNode);
			} else {
				var add = true;
				for ( var i = 0; i < x.nodes.length; i++) {
					if (x.nodes[i].id == treeNode.id) {
						x.nodes.splice(i, 1);
						add = false;
						break;
					}
				}
				if (add)
					x.nodes.push(treeNode);
			}
			var result = document.getElementById(x.id + "_result");
			result.innerHTML = "";
			for ( var i = 0; i < x.nodes.length; i++) {
				var node = x.nodes[i];
				var display = [];
				for ( var j = 0; j < x.displayItems.length; j++) {			
//					var val_ = node[x.displayItems[j]];
//					if(x.displayItems[j] == "name"){
//						if(val_.indexOf(" ") >0){
//							val_ = val_.split(" ")[1];
//						}
//					}
//					display.push(val_);
					display.push(node[x.displayItems[j]]);
				}
				display = display.toString().replace(/,/g, " ");
				var a = XQuery.makeElement("a", null, {
					"id" : node.id + "_" + x.id,
					"title" : display,
					"href" : "javascript:void(0);",
					"innerHTML" : "<input  type=\"button\">" + display,
					"onclick" : function() {
						var z = this.id.split("_")[0];
						for ( var k = 0; k < x.nodes.length; k++) {
							if (x.nodes[k].id == z) {
								x.nodes.splice(k, 1);
								break;
							}
						}
						result.removeChild(this);
					}
				});
				result.appendChild(a);
			}
		}
	}
}


XTree.prototype.deptShow = function() {
	var panel = document.getElementById(this.id);
	if (!panel) {
		panel = XQuery.makeElement("div", null, {
			"id" : this.id
		});
		panel.style.width = "500px";
		panel.style.height = this.comment ? "440px" : "320px";
		var position = XQuery.makeElement("div", "tree_panel");
		position.style.width = panel.style.width;
		position.style.height = panel.style.height;
		panel.appendChild(position);

		if (this.comment) {
			var commentPanel = XQuery.makeElement("div", "tree_comment", {
				"innerHTML" : "<label>Comment:</label>"
			});
			var textarea = XQuery.makeElement("textarea", null, {
				"id" : this.id + "_comment"
			});
			commentPanel.appendChild(textarea);
			position.appendChild(commentPanel);
		}

		var x = this;
		var left = XQuery.makeElement("div", "left");
		var search = XQuery.makeElement("div", "search");
		var searchpanel = XQuery.makeElement("div", "search_panel");
		var searchContent = XQuery.makeElement("input", null, {
			"type" : "text",
			"id" : this.id + "_search",
			"autocomplete" : "off",
			"onkeydown" : function(event) {
				if (window.event != null) {
					event = window.event;
				}
				if (event.keyCode == 13) {
					this.parentNode.childNodes[1].click();
					return;
				}
			},
			"onkeyup" : function(event) {
				if (window.event != null) {
					event = window.event;
				}
				if (event.keyCode == 8) {
					if (this.value == "") {
						this.parentNode.childNodes[1].click();
						return;
					}
				}
			}
		});
		var searchButton = XQuery.makeElement("a", null, {
			"href" : "javascript:void(0);",
			"title" : "查询",
			"hideFocus" : "true",
			"onclick" : function() {
			var query = document.getElementById(x.id + "_search").value;
			if (query && query != "") {
				document.getElementById(x.id + "_tree").style.display = "none";
				var queryTree = document.getElementById(x.id + "_query")
				queryTree.innerHTML = "查询中。。。";
				queryTree.style.display = "";
				$.post(x.url + "?method=query", {
					"query" : query,
					"root" : x.root
				}, function(data) {
					if (data == "")
						queryTree.innerHTML = "查找到：<br>没有符合条件的人。";
					else {
						queryTree.innerHTML = "";
						$.fn.zTree.init($("#" + x.id + "_query"), {
							view : {
								selectedMulti : false,
								autoCancelSelected : false,
								showTitle : false,
								dblClickExpand : false,
								showLine : false
							},
							callback : {
								onClick : selectDept
							}
						}, data);
					}
				}, "json");
			} else {
				document.getElementById(x.id + "_tree").style.display = "";
				document.getElementById(x.id + "_query").style.display = "none";
			}
			}
		});
		searchpanel.appendChild(searchContent);
		searchpanel.appendChild(searchButton);
		search.appendChild(searchpanel);
		left.appendChild(search);
		var ztree = XQuery.makeElement("div", "ztree_panel");
		var ul = XQuery.makeElement("ul", "ztree", {
			"id" : this.id + "_tree"
		});
		var ulquery = XQuery.makeElement("ul", "ztree", {
			"id" : this.id + "_query"
		});
		ulquery.style.display = "none";
		ztree.appendChild(ul);
		ztree.appendChild(ulquery);
		left.appendChild(ztree);
		position.appendChild(left);
		var center = XQuery.makeElement("div", "center");
		var imgpanel = XQuery.makeElement("div", "center_arrow");
		var img = XQuery.makeElement("img", null, {
			"src" : "./scripts/sys/ui/css/images/arrow_left.gif"
		});
		imgpanel.appendChild(img);
		center.appendChild(imgpanel);
		position.appendChild(center);
		var right = XQuery.makeElement("div", "right");
		var result = XQuery.makeElement("div", "result_panel", {
			"id" : this.id + "_result"
		});
		right.appendChild(result);
		position.appendChild(right);
		panel.style.display = "none";
		document.body.appendChild(panel);
		this.treeObj = $.fn.zTree.init($("#" + this.id + "_tree"), {
			view : {
				selectedMulti : false,
				autoCancelSelected : false,
				showTitle : false,
				dblClickExpand : false
			},
			async : {
				autoParam : [ "id" ],
				enable : true,
				url : this.url + "?method=load",
				otherParam : [ "root", this.root ]
			},
			callback : {
				onClick : selectDept
			}
		});

	}
	if (!this.pop) {
		this.pop = new Pop(this.id, this.title, {
			"basicOperate" : true,
			"ok" : this.ok
		});
	}
	this.pop.show();
	function selectDept(event, treeId, treeNode) {
		
			if (!x.multi) {
				x.nodes = [];
				x.nodes.push(treeNode);
			} else {
				var add = true;
				for ( var i = 0; i < x.nodes.length; i++) {
					if (x.nodes[i].id == treeNode.id) {
						x.nodes.splice(i, 1);
						add = false;
						break;
					}
				}
				if (add)
					x.nodes.push(treeNode);
			}
			var result = document.getElementById(x.id + "_result");
			result.innerHTML = "";
			for ( var i = 0; i < x.nodes.length; i++) {
				var node = x.nodes[i];
				var display = [];
				for ( var j = 0; j < x.displayItems.length; j++) {
					display.push(node[x.displayItems[j]]);
				}
				display = display.toString().replace(/,/g, " ");
				var a = XQuery.makeElement("a", null, {
					"id" : node.id + "_" + x.id,
					"title" : display,
					"href" : "javascript:void(0);",
					"innerHTML" : "<input  type=\"button\">" + display,
					"onclick" : function() {
						var z = this.id.split("_")[0];
						for ( var k = 0; k < x.nodes.length; k++) {
							if (x.nodes[k].id == z) {
								x.nodes.splice(k, 1);
								break;
							}
						}
						result.removeChild(this);
					}
				});
				result.appendChild(a);
			}
	}
}


XTree.prototype.initSelectNodes = function(nodes) {
	this.nodes = nodes;
	var x = this;
	var result = document.getElementById(this.id + "_result");
	result.innerHTML = "";
	for ( var i = 0; i < this.nodes.length; i++) {
		var node = this.nodes[i];
		var display = [];
		for ( var j = 0; j < this.displayItems.length; j++) {
			display.push(node[this.displayItems[j]]);
		}
		display = display.toString().replace(/,/g, " ");
		var a = XQuery.makeElement("a", null, {
			"id" : node.id + "_" + this.id,
			"title" : display,
			"href" : "javascript:void(0);",
			"innerHTML" : "<input  type=\"button\">" + display,
			"onclick" : function() {
				var z = this.id.split("_")[0];
				for ( var k = 0; k < x.nodes.length; k++) {
					if (x.nodes[k].id == z) {
						x.nodes.splice(k, 1);
						break;
					}
				}
				result.removeChild(this);
			}
		});
		result.appendChild(a);
	}
}
XTree.prototype.getSelectedNodes = function() {
	return this.nodes.slice(0);
}
XTree.prototype.getComment = function() {
	var comment = document.getElementById(this.id + "_comment");
	if (comment)
		return comment.value;
}