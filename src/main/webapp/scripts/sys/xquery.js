var XQuery = {
	XFactory : {
		factorys : {},
		put : function(id, factory) {
			this.factorys[id] = factory;
		},
		get : function(id) {
			return this.factorys[id];
		},
		init : function(settings) {
			if (typeof (settings) == "undefined") {
				settings = {
					XPanel : XPanelFactory,
					XTree : XTreeFactory,
					XWindow : XWindowFactory,
					XGrid : XGridFactory
				};
			}
			for ( var i in settings) {
				this.put(i, settings[i]);
			}
		}
	},
	make : function(settings) {
		var xtype = settings.xtype;
		if (!xtype)
			return;
		var factory = this.XFactory.get(xtype);
		if (!factory)
			return;
		return factory.make(settings);
	},
	getCmp : function(id, xtype) {
		if (typeof (xtype) != "undefined") {
			var factory = this.XFactory.get(xtype);
			if (factory)
				return factory.get(id);
		} else {
			for ( var i in this.XFactory.factorys) {
				var factory = this.XFactory.factorys[i];
				var cmp = factory.get(id);
				if (cmp)
					return cmp;
			}
		}
		return;
	},
	getBrowser : function() {
		ua = window.navigator.userAgent.toLowerCase();
		var match = /(chrome)[ \/]([\w.]+)/.exec(ua)
				|| /(webkit)[ \/]([\w.]+)/.exec(ua)
				|| /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(ua)
				|| /(msie) ([\w.]+)/.exec(ua) || ua.indexOf("compatible") < 0
				&& /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(ua) || [];
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	},
	makeElement : function(type, className, properties) {
		if (type) {
			var element = document.createElement(type);
			if (element) {
				if (className)
					element.className = className;
				if (properties) {
					for ( var x in properties) {
						element[x] = properties[x];
					}
				}
				return element;
			}
		}
	},
	makeHiddenInput : function(id, name, value) {
		return this.makeElement("input", "", {
			"type" : "hidden",
			"id" : id,
			"name" : name,
			"value" : value
		});
	},
	getDate : function(dateString) {
		if (!dateString)
			return new Date();
		else {
			var converted = Date.parse(dateString);
			if (!isNaN(converted)) {
				return new Date(converted);
			}
			var delimiter = dateString.indexOf("-") != -1 ? "-" : "/";
			var data = dateString.split(delimiter);
			if (data.length == 3 && !isNaN(data[0]) && !isNaN(data[1])
					&& !isNaN(data[2])) {
				var year = data[0];
				var month = data[1];
				var day = data[2];
				if (year >= 1970 && year <= 2100 && month > 0 && month < 13
						&& day > 0 && day <= 31) {
					if (((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30)
							|| ((month == 1 || month == 3 || month == 5
									|| month == 7 || month == 8 || month == 10 || month == 12) && day <= 31)
							|| (month == 2 && (day <= 28 || (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && day <= 29)))) {
						var myDate = new Date();
						myDate.setFullYear(year, month - 1, day);
						return myDate;
					}
				}
			}
		}
	},
	getYear : function(date) {
		if (!date)
			return (new Date()).getYear() + 1900;
		if (date instanceof Date)
			return date.getYear() + 1900;
	},
	getMonth : function(date) {
		if (!date)
			return (new Date()).getMonth() + 1;
		if (date instanceof Date)
			return date.getMonth() + 1;
	},
	getDay : function(date) {
		if (!date)
			return (new Date()).getDate();
		if (date instanceof Date)
			return date.getDate();
	},
	getDayOfWeek : function(date) {
		if (!date)
			return (new Date()).getDay() + 1;
		if (date instanceof Date)
			return date.getDay() + 1;
	},
	getDaysOfMonth : function(date) {
		if (!date)
			return this.getDaysOfMonth(new Date());
		if (date instanceof Date) {
			var month = this.getMonth(date);
			if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12)
				return 31;
			else if (month == 4 || month == 6 || month == 9 || month == 11)
				return 30;
			else {
				var year = this.getYear(date);
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
					return 29;
				else
					return 28;
			}
		}
	}
};