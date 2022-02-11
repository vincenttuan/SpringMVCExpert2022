<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>User From</title>
</head>
<body style="padding: 15px;">
	<spform:form class="pure-form" 
				 method="post" 
				 modelAttribute="user" 
				 action="/spring.mvc/mvc/session11/user/">
		
		姓名：<spform:input path="name" /><p />
		年齡：<spform:input path="age" /><p />
		生日：<spform:input path="birth" /><p />
		
		<input type="hidden" name="_method" id="_method" value="${ _method }"/>
		<button type="submit" class="pure-button pure-button-primary">${ submitButtonName }</button>
		
	</spform:form>
</body>
</html>