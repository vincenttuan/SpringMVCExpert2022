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
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
	    function drawChart() {
	    	salary();
	    	jobs();
	    }
	    
	    function salary() {
	    	var data = google.visualization.arrayToDataTable([
		        ['ename', 'salary'],
		        <c:forEach var="emp" items="${ employees }">
		        ['${ emp.ename }', ${emp.salary}],
		        </c:forEach>
			]);
		
			var options = {
				title: 'Salary'
			};
			
			// 長條圖(縱)
			var columnChart = new google.visualization.ColumnChart(document.getElementById('column_chart'));
			columnChart.draw(data, options);
			// 圓餅圖
			var pieChart = new google.visualization.PieChart(document.getElementById('pie_chart'));
			pieChart.draw(data, options);
	    }
	    
	    function jobs() {
	    	
	    	var data = google.visualization.arrayToDataTable([
		        ['ename', 'salary'],
		        <c:forEach var="emp" items="${ employees }">
		        	// 判斷工作數量是否  > 0
		        	<c:if test="${ fn:length(emp.jobs) > 0 }">
		        		// 判斷第一筆工作的 jid 不可以是 null
				        <c:if test="${ emp.jobs[0].jid != null }">
				        	['${ emp.ename }', ${ fn:length(emp.jobs) }],
				        </c:if>
				        <c:if test="${ emp.jobs[0].jid == null }">
				        	['${ emp.ename }', 0],
				        </c:if>	
			        </c:if>
		        </c:forEach>
			]);
		
			var options = {
				title: 'Jobs'
			};
		
			var chart = new google.visualization.LineChart(document.getElementById('line_chart'));
			chart.draw(data, options);	    	
	    }
	</script>
</head>
<body style="padding: 15px;">

	<table border="0">
		<tr>
			<!-- Employee Form -->
			<td valign="top">
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
			</td>
			<!-- Salary Column Chart -->
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>Salary Column Chart</legend>
						<div id="column_chart" style="width: 400px; height: 250px"></div>
					</fieldset>
				</form>
			</td>
			<!-- Salary Pie Chart -->
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>Salary Pie Chart</legend>
						<div id="pie_chart" style="width: 400px; height: 250px"></div>
					</fieldset>
				</form>
			</td>
			<!-- Jobs Line Chart -->
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>Jobs Line Chart</legend>
						<div id="line_chart" style="width: 400px; height: 250px"></div>
					</fieldset>
				</form>
			</td>
		</tr>
		<tr>
			<!-- Employee List -->
			<td valign="top" colspan="4">
				<form class="pure-form">
					<fieldset>
						<legend>Employee List</legend>
						<table class="pure-table pure-table-bordered" width="100%">
							<thead>
								<tr>
									<th>編號</th><th>姓名</th><th>薪資</th><th>工作</th><th>建立時間</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="emp" items="${ employees }">
									<tr>
										<td>${ emp.eid }</td>
										<td>${ emp.ename }</td>
										<td>${ emp.salary }</td>
										<td>
											${ emp.jobs[0].jid != null }
											<c:forEach var="job" items="${ emp.jobs }">
												${ job.jname }
											</c:forEach>
										</td>
										<td>${ emp.createtime }</td>
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