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
<title>Product</title>
</head>
<body style="padding: 15px;">
	<form class="pure-form" method="post"
		action="/spring.mvc/mvc/product/${ action }">
		<fieldset>
			<legend>Product form</legend>
			<input type="text" placeholder="請輸入商品名稱" id="productName"
				name="productName" />
			<p />
			<input type="number" placeholder="請輸入商品數量" id="quantity"
				name="quantity" />
			<p />
			<input type="number" placeholder="請輸入商品價格" id="price" name="price" />
			<p />
			<button type="submit" class="pure-button pure-button-primary">${ action }</button>
		</fieldset>
	</form>
	<form class="pure-form">
		<fieldset>
			<legend>Product list</legend>
			<table class="pure-table pure-table-bordered">
				<thead>
					<tr>
						<th>序號<br />index</th>
						<th>商品名稱<br />productName</th>
						<th>商品數量<br />quantity</th>
						<th>商品價格<br />price</th>
						<th>修改<br />update</th>
						<th>刪除<br />delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach varStatus="status" var="product" items="${ products }">
						<tr>
							<td>${ status.index }</td>
							<td>${ product.productName }</td>
							<td>${ product.quantity }</td>
							<td>${ product.price }</td>
							<td>
								<button type="button"
									onclick="window.location.href='/spring.mvc/mvc/product/get/${ status.index }';"
									class="pure-button pure-button-primary">修改</button>
							</td>
							<td>
								<button type="button"
									onclick="window.location.href='/spring.mvc/mvc/product/delete/${ status.index }';"
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