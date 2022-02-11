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
<title>Rest Product update</title>
</head>
<body style="padding: 15px;">
	<pre style="display: none;">
	product = ${ product }
	index = ${ index }
	action = ${ action }
	</pre>
	<form class="pure-form" method="post"
		action="/spring.mvc/mvc/product/rest/${ index }">
		<fieldset>
			<legend>Rest Product Update Form</legend>
			<input type="hidden" name="_method" id="_method" value="PUT"/>
			index：${ index }
			<p />
			<input type="text" placeholder="請輸入商品名稱" id="productName" name="productName" 
				   value="${ product.productName }" />
			<p />
			<input type="number" placeholder="請輸入商品數量" id="quantity" name="quantity" 
				   value="${ product.quantity }" />
			<p />
			<input type="number" placeholder="請輸入商品價格" id="price" name="price" 
				   value="${ product.price }" />
			<p />
			<button type="submit" class="pure-button pure-button-primary">Rest Update</button>
		</fieldset>
	</form>
	<hr />
	<form class="pure-form" method="post"
		action="/spring.mvc/mvc/product/rest/${ index }">
		<fieldset>
			<legend>Rest Product Delete Form</legend>
			<input type="hidden" name="_method" id="_method" value="DELETE"/>
			index：${ index }
			<p />
			商品名稱：${ product.productName }
			<p />
			商品數量：${ product.quantity }
			<p />
			商品價格：${ product.price }
			<p />
			<button type="submit" class="pure-button pure-button-primary">Rest Delete</button>
		</fieldset>
	</form>
	
</body>
</html>
