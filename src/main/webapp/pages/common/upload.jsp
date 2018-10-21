<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<body onload="load()">
		<input type="hidden" id="error" value="${requestScope.error}" />
		<form id="uploadForm" method="post" enctype="multipart/form-data"
			action="upload.do">
			<input type="file" id="upload" multiple name="upload"
				onchange="uploadFile()" />
			<input type="hidden" id="order" name="order" />
		</form>
	</body>
	<script type="text/javascript">
	function load() {
		var error = document.getElementById("error").value;
		if (error && error != "")
			alert(error);
		window.parent.refreshAttachPanel();
	}
	function uploadFile() {
		document.getElementById("uploadForm").submit();
	}
</script>
</html>
