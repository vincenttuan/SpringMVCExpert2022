<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>Lotto</title>
</head>
<body style="padding: 15px;">
	<button type="button"
		onclick="window.location.href='/spring.mvc/mvc/lotto/get';"
		class="pure-button pure-button-primary">Lotto 539 電腦選號</button>
	<p />
	最新電腦選號：${ lotto }
	<p />
	統計每一個號碼出現過的次數：
	<button type="button"
		onclick="window.location.href='/spring.mvc/mvc/lotto/stat';"
		class="pure-button pure-button-primary">統計運算</button>
	<div>${ stat }</div>
	<p />
	歷史紀錄 III：
	<table class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>index</th>
				<th>號碼 1</th>
				<th>號碼 2</th>
				<th>號碼 3</th>
				<th>號碼 4</th>
				<th>號碼 5</th>
				<th>更新（Update）</th>
				<th>刪除（Delete）</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="status" var="lotto" items="${ lottos }">
				<tr>
					<td>${ status.index }</td>
					<c:forEach var="num" items="${ lotto }">
						<td>${ num }</td>
					</c:forEach>
					<td>
						<button type="button"
								onclick="window.location.href='/spring.mvc/mvc/lotto/update/${ status.index }';"
								class="pure-button pure-button-primary">更新</button>
					</td>
					<td>
						<button type="button"
								onclick="window.location.href='/spring.mvc/mvc/lotto/delete/${ status.index }';"
								class="pure-button pure-button-primary">刪除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p />
	歷史紀錄 II：
	<table class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>#</th>
				<th>號碼 1</th>
				<th>號碼 2</th>
				<th>號碼 3</th>
				<th>號碼 4</th>
				<th>號碼 5</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="status" var="lotto" items="${ lottos }">
				<tr>
					<td>${ status.index+1 }</td>
					<c:forEach var="num" items="${ lotto }">
						<td>${ num }</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p />
	歷史紀錄 I：
	<table class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>#</th>
				<th>Lotto</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="status" var="lotto" items="${ lottos }">
				<tr>
					<td>${ status.index+1 }</td>
					<td>${ lotto }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>