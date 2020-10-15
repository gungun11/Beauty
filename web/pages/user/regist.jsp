<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>

	<%--
		静态包含 base标签,js文件,css样式
	--%>
	<%@ include file="/pages/commons/head.jsp"%>


	<script type="text/javascript">
		// 页面加载完成之后
		$(function () {
			// 验证用户名是否可用
			$("#username").blur(function () {
				/**
				 * 	在事件响应的function函数中有一个this对象,这个this对象是当前正在响应事件的dom对象.
				 */
				//1 先验证用户名是否合法
				//2 把用户名以Ajax请求的方式发送给服务器验证
				$.getJSON("${basePath}userServlet","action=ajaxExistsUsername&username=" + this.value,function (data) {
					if(data.existsUsername){
						$("span.errorMsg").text("用户名已存在！");
					} else {
						$("span.errorMsg").text("用户名可用！");
					}
				});
			});

			// 给验证码图片绑定单击事件
			$("#codeImg").click(function () {
				// 在事件响应的function函数中,有一个this对象.这个this是正在响应事件的dom对象
				/**
				 * src属性表示图片的路径
				 * 这个属性可读,可写
				 */
				this.src = "${basePath}kaptcha.jpg?d=" + Math.random();
			});

			// 【注册】的单击事件
			$("#sub_btn").click(function () {
				// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
				// 1 获取用户名输入框里的内容
				var usernameText = $("#username").val();
				// 2 创建用户名的正则表达式
				var usernamePatt = /^\w{5,12}$/;
				// 3 调用test() 去验证字符串是否匹配
				if (!usernamePatt.test(usernameText)) {
					// 4 提示用户结果（ 不合法的时候 ）
					$("span.errorMsg").text("用户名不合法！");
					return false;
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
				// 1 获取用户名输入框里的内容
				var passwordText = $("#password").val();
				// 2 创建用户名的正则表达式
				var passwordPatt = /^\w{5,12}$/;
				// 3 调用test() 去验证字符串是否匹配
				if (!passwordPatt.test(passwordText)) {
					// 4 提示用户结果（ 不合法的时候 ）
					$("span.errorMsg").text("密码不合法！");
					return false;
				}

				// 验证确认密码：和密码相同
				// 获取确认密码输入框里的内容
				var repwdText = $("#repwd").val();
				// 跟密码比较相同
				if (repwdText != passwordText) {
					// 不相同要提示用户
					$("span.errorMsg").text("确认密码和密码不一致！");
					return false;
				}

				// 邮箱验证：xxxxx@xxx.com
				//1 获取邮箱的内容
				var emailText = $("#email").val();
				//2 创建邮箱的正则表达式对象
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				//3 调用test方法验证
				if (!emailPatt.test(emailText)) {
					//4 提示用户
					$("span.errorMsg").text("邮箱格式不合法！");
					return false;
				}

				// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
				// 1 获取验证码输入框里的内容
				var code = $("#code").val();
				// alert("去空格前[" + code + "]");
				code = $.trim(code);
				// alert("去空格前[" + code + "]");

				// 2 比较有内容，不等于null，不等于空串，
				if (code == null || code == "") {
					// 3 提示用户
					$("span.errorMsg").text("请输入验证码！");
					return false;
				}

				$("span.errorMsg").text("");
			});
		});
	</script>

<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									<%--<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off"
										   tabindex="1" name="username" id="username"
										value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off"
										   tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off"
										   tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off"
										   tabindex="1" name="email" id="email"
										value="${requestScope.email}"
									/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 120px;" name="code" id="code"/>
									<img alt="" src="kaptcha.jpg" id="codeImg"
										 style="float: right; margin-right: 40px; width: 100px;height: 32px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>


		<%-- 静态包含页脚内容 --%>
		<%@ include file="/pages/commons/footer.jsp"%>




</body>
</html>