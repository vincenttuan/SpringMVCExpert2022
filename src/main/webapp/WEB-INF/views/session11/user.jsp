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
	<script>
		function deleteUser(index) {
			// Create an XMLHttpRequest object
			const xhttp = new XMLHttpRequest();

			// Define a callback function
			xhttp.onload = function() {
			  window.location.href = '/spring.mvc/mvc/session11/user/';
			}

			// Send a request
			xhttp.open("DELETE", "/spring.mvc/mvc/session11/user/" + index);
			xhttp.send();
		}
	</script>
</head>
<body style="padding: 15px;">
	<spform:form class="pure-form" 
				 method="post" 
				 modelAttribute="user" 
				 action="/spring.mvc/mvc/session11/user/${ index }">
		<fieldset>
			<legend>User form</legend>
			姓名：<spform:input path="name" /><p />
			年齡：<spform:input path="age" /><p />
			生日：<spform:input type="date" path="birth" /><p />
			學歷：<spform:select path="education">
					<spform:option value="">請選擇</spform:option>
					<spform:option value="國中">國中</spform:option>
					<spform:option value="高中">高中</spform:option>
					<spform:option value="大學">大學</spform:option>
					<spform:option value="研究所">研究所</spform:option>
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
		</fieldset>
	</spform:form>
	<form class="pure-form">
		<fieldset>
			<legend>User List</legend>
			<table class="pure-table pure-table-bordered">
				<thead>
					<tr>
						<th>index</th><th>姓名</th><th>年齡</th><th>生日</th>
						<th>學歷</th><th>性別</th><th>興趣</th><th>履歷</th>
						<th>編輯</th><th>刪除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach varStatus="status" var="user" items="${ users }">
					<tr>
						<td>${ status.index }</td><td>${ user.name }</td><td>${ user.age }</td><td>${ user.birth }</td>
						<td>${ user.education }</td><td>${ user.sex }</td>
						<td>
							<c:forEach var="interest" items="${ user.interest }">
								${ interest }
							</c:forEach>
						</td>
						<td>${ user.resume }</td>
						<td>
							<button type="button"
									onclick="window.location.href='/spring.mvc/mvc/session11/user/${ status.index }';"
									class="pure-button pure-button-primary">編輯</button>
						</td>
						<td>
							<button type="button"
									onclick="deleteUser(${ status.index });"
									class="pure-button pure-button-primary">刪除</button>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>		
			
		</fieldset>	
	</form>
</body>
</html>