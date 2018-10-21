<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<body onload="load()">
		<input type="hidden" id="error" value="${requestScope.error}" />
		<input type="hidden" id="fileName" value="${requestScope.fileName}" />
		<input type="hidden" id="row2" value="${requestScope.row}" />
		<form id="plUploadForm" method="post" enctype="multipart/form-data"
			action="plUpload.do">
			<input type="file" id="upload" multiple name="upload"
				onchange="uploadFile()" />
			<input type="hidden" id="order" name="order" />
			<input type="hidden" id="material" name="material" />
			<input type="hidden" id="factory" name="factory" />
			<input type="hidden" id="type" name="type" />
			<input type="hidden" id="specified" name="specified" />
			<input type="hidden" id="row" name="row" />
		</form>
	</body>
	<script type="text/javascript">
	function load() {
		var error = document.getElementById("error").value;
		if (error && error != "")
			alert(error);
		
		window.parent.setValue(document.getElementById("fileName").value,document.getElementById("row2").value);
		//window.parent.refreshAttachPanel();
	}
	function uploadFile() {
		document.getElementById("plUploadForm").submit();
	}
</script>
</html>
