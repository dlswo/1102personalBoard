<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!-- All the files that are required -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/assets/css/login.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<title>Login Page</title>
</head>
<body>	
<div class="text-center" style="padding:50px 0">
	<div class="logo">로그인</div>
	<!-- Main Form -->
	<div class="login-form-1">
		<form id="login-form" class="text-left" action="/login" method="post">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<label for="username" class="sr-only">Username</label>
						<input type="text" class="form-control" id="username" name="username" placeholder="username" value="injae">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">Password</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="password" value="injae">
					</div>
					<div class="form-group login-group-checkbox">
						<input type="checkbox" id="remember" name="remember-me">
						<label for="remember">자동로그인</label>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>
				<button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
			</div>
			<div class="etc-login-form">
				<p>신규 유저입니까? <a href="/signUp">회원가입을 하세요</a></p>
			</div>
		</form>
	</div>
	<!-- end:Main Form -->
</div>

<script type="text/javascript" src="/resources/assets/js/login.js"></script>
</body>
</html>