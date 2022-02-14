<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
	<meta charset="UTF-8">
	<title>Exam 考試註冊系統</title>
</head>
<body style="padding: 15px;">
	<table>
		<tr>
			<!-- Exam 考試註冊-表單 -->
			<td valign="top" title="Exam 考試註冊-表單">
				<spform:form class="pure-form" 
							 modelAttribute="exam"
							 method="post"
							 action="${ pageContext.request.contextPath }/mvc/exam/${ action }">
					<fieldset>
						<legend>Exam 考試註冊-表單</legend>
						學號：<spform:input path="id" /><p />
						科目：<spform:select path="exam">
							 	<spform:option value="">請選擇</spform:option>
							 	<spform:option value="808">JavaSE 8 OCP I 808</spform:option>
							 	<spform:option value="809">JavaSE 8 OCP II 809</spform:option>
							 	<spform:option value="819">JavaSE 11 OCP 819</spform:option>
							 	<spform:option value="900">JavaEE 7 OCP 900</spform:option>
							 </spform:select><p />
						時段：<spform:checkbox path="slot" value="A" /> 上午(A)
							 <spform:checkbox path="slot" value="B" /> 下午(B)
							 <spform:checkbox path="slot" value="C" /> 晚上(C) <p />
						繳費：<spform:radiobutton path="pay" value="true" /> 已繳
							 <spform:radiobutton path="pay" value="false" /> 未繳 <p />
						備註：<spform:textarea path="note" /> <p />
						<button type="submit" class="pure-button pure-button-primary">
							${ action }
						</button>
					</fieldset>
				</spform:form>
			</td>
			<!-- Exam 考試註冊-清單 -->
			<td valign="top" title="Exam 考試註冊-清單">
				<form class="pure-form">
					<fieldset>
						<legend>Exam 考試註冊-清單</legend>
						<table class="pure-table pure-table-bordered">
							<thead>
								<tr>
									<th>學號<br>id</th>
									<th>科目<br>exam</th>
									<th>時段<br>slot</th>
									<th>繳費<br>pay</th>
									<th>備註<br>note</th>
									<th>編輯<br>edit</th>
									<th>刪除<br>delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="exam" items="${ exams }">
								<tr>
									<td>${ exam.id }</td>
									<td>${ exam.exam }</td>
									<td>${ exam.slot }</td>
									<td>${ exam.pay }</td>
									<td>${ exam.note }</td>
									<td>
										<button type="button"
												onclick="edit(${ exam.id });"
												class="pure-button pure-button-primary">
											編輯
										</button>
									</td>
									<td>
										<button type="button"
												onclick="remove(${ exam.id });"
												class="pure-button pure-button-primary">
											刪除
										</button>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</fieldset>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>