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
	<title>Employee Form</title>
	<style type="text/css">
		.error {
			color: #FF0000;
		}
	</style>
</head>
<body style="padding: 15px;">
	<spform:form class="pure-form" 
				 method="post" 
				 modelAttribute="employee" 
				 action="${ pageContext.request.contextPath }/mvc/session15/employee/">
		<fieldset>
			<legend>Employee form</legend>
			<input type="hidden" name="_method" id="_method" value="${ _method }"/>
			編號：<spform:input path="eid" readonly="true" /><p />
			姓名：<spform:input path="ename" />
				 <spform:errors path="ename" cssClass="error" /><p />
			薪資：<spform:input path="salary" />
				 <spform:errors path="salary" cssClass="error" /><p />
			<button type="submit" class="pure-button pure-button-primary">${ action }</button><p />
			<spform:errors path="*" cssClass="error" />
		</fieldset>
	</spform:form>
	${ employees }
</body>
</html>