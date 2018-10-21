<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>登陆</title>
		<link rel="stylesheet" type="text/css"
			href="./styles/clear.css" />
		<link rel="stylesheet" type="text/css"
			href="./styles/login.css" />
		<style>
#login {
	margin-top: 250px;
}

#login .box-body ul li span {
	letter-spacing: 6px;
}
</style>
	</head>

	<body>
		<div id="login" class="box">
			<div class="box-head">
				<h3>
					用户登陆
					<span>User Login</span>
				</h3>
			</div>
			<div class="box-body">
				<form method="post">
					<ul>
						<li>
							<span>工号</span>：
							<input type="text" id="user_ID" name="user_ID"
								autocomplete="off" value="${requestScope.user_ID}" />
						</li>
						<li>
							<span>密码</span>：
							<input type="password" id="password" name="password" />
						</li>
						<li>
							<a href="javascript:void(0)" class="btn" onclick="commit()">登&nbsp;陆</a>
							<em id="error" class="error"> ${requestScope.error} </em>
						</li>
					</ul>
				</form>
			</div>
		</div>
		<script type="text/javascript">
function commit() {
	var x = validation();
	if (typeof (x) != "undefined")
		document.getElementById("error").innerHTML = x;
	else
		document.forms[0].submit();
}
function validation() {
	if (document.getElementById("user_ID").value == "")
		return "工号不能为空";
	if (document.getElementById("password").value == "")
		return "密码不能为空";
}
</script>
	</body>
</html>
