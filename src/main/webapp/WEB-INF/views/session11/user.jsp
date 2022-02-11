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
		生日：<spform:input type="date" path="birth" /><p />
		學歷：<spform:select path="education">
				<spform:option value="">請選擇</spform:option>
				<spform:option value="國中">請選擇</spform:option>
				<spform:option value="高中">請選擇</spform:option>
				<spform:option value="大學">請選擇</spform:option>
				<spform:option value="研究所">請選擇</spform:option>
			 </spform:select><p />
		性別：<spform:radiobutton path="sex" value="男" />男
			 <spform:radiobutton path="sex" value="女" />女<p />
		興趣：<spform:checkbox path="interest" value="爬山" />爬山
			 <spform:checkbox path="interest" value="看書" />看書
			 <spform:checkbox path="interest" value="打球" />打球
			 <spform:checkbox path="interest" value="飛控" />飛控<p />
		履歷：<spform:textarea path="resume" /><p />
		<input type="hidden" name="_method" id="_method" value="${ _method }"/>
		<button type="submit" class="pure-button pure-button-primary">${ submitButtonName }</button>
		
	</spform:form>
</body>
</html>