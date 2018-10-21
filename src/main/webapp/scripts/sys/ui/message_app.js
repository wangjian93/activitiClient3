var MessageBoxFactory = {
	boxes : {},
	size : 0,
	maxElementsInMemory : 100,
	memoryStoreEvictionPolicy : "FIFO",
	makeMessageBox : function(id, title, ok) {
		if (this.boxes[id])
			return this.boxes[id];
		else {
			var box = new MessageBox(id, title, ok);
			this.boxes[id] = box;
			this.size++;
			return box;
		}
	},
	getMessageBox : function(id) {
		return this.boxes[id];
	}
}
function MessageBox(id, title, ok) {
	this.id = id;
	this.title = title ? title : "无标题窗口";
	this.ok = ok ? ok : function() {
		return "请定义事件！"
	};
}
MessageBox.prototype.hide = function() {
	this.pop.hide();
}
MessageBox.prototype.show = function() {
	var message = document.getElementById(this.id);
	if (!message) {
		var div = XQuery.makeElement("div", "col-sm-8 message message_div", {
			"id" : this.id,
			"data-role" : "content"
		});
		var textarea = XQuery.makeElement("textarea", "textareacss", {
			"id" : this.id + "_message"
		});
		div.appendChild(textarea);
		var width = $(window).width();
//		var width2 = window.screen.width;
		if( width == 0 ){
			div.style.width = "500px";
		} else if( width > 520 ) {
			div.style.width = "500px";
		} else if ( width > 400 ) {
			div.style.width = "380px";
		} else if ( width > 320 ) {
			div.style.width = "300px";
		} else {
			div.style.width = "200px";
		}
		
		div.style.height = "200px";
		div.style.display = "none";
		
		textarea.style.width = "90%";
		textarea.style.height = "100%";
		
		document.body.appendChild(div);
	}
	if (!this.pop) {
		this.pop = new Pop(this.id, this.title, {
			"basicOperate" : true,
			"ok" : this.ok
		});
	}
	this.pop.show();
}
MessageBox.prototype.getMessage = function() {
	var message = document.getElementById(this.id + "_message");
	if (message)
		return message.value;
}