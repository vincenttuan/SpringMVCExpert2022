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
<title>Rest Success</title>
</head>
<body style="padding: 15px;">
	<form class="pure-form">
	    <fieldset>
	        <legend>Rest Success form</legend>
	        商品名稱：${ product.productName }<p />
	        商品數量：${ product.quantity }<p />
	        商品價格：${ product.price }<p />
	        <button type="button"
	        		onclick="window.location.href='/spring.mvc/mvc/product/rest/';" 
	        		class="pure-button pure-button-primary">Rest Back</button>
	    </fieldset>
	</form>
</body>
</html>