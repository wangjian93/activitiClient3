<meta content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0" name="viewport">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib uri="/WEB-INF/tld/hr.tld" prefix="hr" %>
<%@ taglib uri="/WEB-INF/tld/html.tld" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="model" value="${requestScope.model}" />
<c:set var="bReadonly" value="${requestScope.bReadOnly}" />
<c:set var="error" value="${requestScope.error}" />
<c:set var="dept_ID" value="${requestScope.department_ID}" />
<c:set var="attspFlag" value="${requestScope.attspFlag}" />
<c:set var="attFlag" value="${requestScope.attFlag}" />
<c:set var="otherFlag" value="${requestScope.otherFlag}" />
<c:set var="home" value="${pageContext.request.contextPath}"/>
<c:set var="token" value="${requestScope.token}" />
<c:if test="${not empty bReadonly}">
    <script>
        var g_ReadOnly = (${bReadonly});
        var uid = '<%=request.getAttribute("uid")%>';
    </script>
</c:if>

<script type="text/javascript" src="./scripts/sys/xquery.js"></script>
<script type="text/javascript" src="./scripts/sys/upload.js"></script>
<script type="text/javascript" src="./scripts/sys/ui/pop.js"></script>
<script type="text/javascript" src="./scripts/sys/ui/message.js"></script>
<script type="text/javascript" src="./scripts/sys/ui/xtree.js"></script>
<link rel="stylesheet" type="text/css" href="./scripts/sys/ui/css/pop.css" />
<link rel="stylesheet" type="text/css" href="./scripts/sys/ui/css/tree.css" />
<link rel="stylesheet" type="text/css" href="./scripts/sys/ui/css/message.css" />
<link rel="stylesheet" type="text/css" href="./scripts/sys/ui/css/ztree-ex.css" />
<script type="text/javascript" src="./scripts/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="./scripts/jquery/ui/jquery-ui-1.8.23.custom.min.js"></script>
<link rel="stylesheet" type="text/css" href="./scripts/jquery/ui/css/smoothness/jquery-ui-1.8.23.custom.css" />
<script type="text/javascript" src="./scripts/jquery/ztree/jquery.ztree.core-3.5_001.min.js"></script>
<link rel="stylesheet" type="text/css" href="./scripts/jquery/ztree/css/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="./styles/clear.css" />
<link rel="stylesheet" type="text/css" href="./ext-3.3/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css"  href="./styles/Spinner.css"/>
<script type="text/javascript" src="./ext-3.3/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="./ext-3.3/ext-all.js"></script>
<script type="text/javascript" src="./scripts/sys/base.js"></script>
<link rel="stylesheet" type="text/css" href="./styles/order.css" />

<script type="text/javascript" src="./pages/calendar/calendar.js"></script>
<link rel="stylesheet" href="./pages/calendar/calendar.css" type="text/css" />
<script type="text/javascript" src="./scripts/jquery/ui/DatePicker.js"></script>
<script language="javascript" type="text/javascript" src="./scripts/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="./scripts/My97DatePicker/WdatePicker.js"></script>
<script src="./scripts/bootstrap/js/bootstrap.min.js"></script>