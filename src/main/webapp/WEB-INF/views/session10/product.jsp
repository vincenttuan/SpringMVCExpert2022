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
	<form class="pure-form" method="post" action="/spring.mvc/mvc/product/${ action }">
	    <fieldset>
	        <legend>Product form</legend>
	        <input type="text" placeholder="請輸入商品名稱" id="productName" name="productName" /><p />
	        <input type="number" placeholder="請輸入商品數量" id="quantity" name="quantity" /><p />
	        <input type="number" placeholder="請輸入商品價格" id="price" name="price" /><p />
	        <button type="submit" class="pure-button pure-button-primary">${ action }</button>
	    </fieldset>
	</form>
</body>
</html>