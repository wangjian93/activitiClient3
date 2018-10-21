<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<body>
		<script type="text/javascript">
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
		iframe.src = 'http://10.20.2.243:8080/Arcadia/pages/web/proxy.jsp#homeReload';
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
</script>
	</body>
</html>
