<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta charset="UTF-8">
<title>Index</title>
<style type="text/css">
.link {
  color: blue;
  text-decoration: underline;
  cursor: pointer;
}
.link:hover {
  color: red;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

function abc() {
	$("#div").toggle();
	
	var strURL="/mvc-app/api/call?id=4,5,6";
	$.ajax({
        type: "GET",
        url : strURL,
        success : function(response) {
        	$("#h1").html("");
        	$("#h2").html("");
        	$("#h1").append("This value by using Azax API calling:");
        	$("#h2").append(response);
        },
        error: function(error) {
        	alert("Somthing went wrong!!");
        }
	});
}

var id = "";
var name = "";
var city = "";
function updateData(obj) {
	id = obj.htmlFor;
	name = $("#hName" + id).val();
	city = $("#hCity" + id).val();
	
	$("#name").val(name);
	$("#city").val(city);
	$("#submitBtnId").val("UPDATE");
}

function submitUpdate() {
	if($("#submitBtnId").val() == "SUBMIT") {
		$("#formSubmitId").submit();
	}
		
	if($("#submitBtnId").val() == "UPDATE") {
		name = $("#name").val();
		city = $("#city").val();
		
		var strURL="updateData.dispatch?id=" + id + "&name=" + name + "&city=" + city;
		$.ajax({
	        type: "POST",
	        url : strURL,
	        success : function(msg) {
	        	alert('"' + msg + '"' + ' updated successfully!');
	        	location.reload();
	        },
	        error: function(error) {
	        	alert("Somthing went wrong!!");
	        }
		});
	}
}

function getData() {
	if($("#id").val() == "") {
		alert("Please enter Id first!");
		return false;
	}
	$("#formGetModel").submit();
}

function getDataList() {
	$("#formGetModelList").submit();
}

function deleteData(obj) {
	var id = obj.htmlFor;
	var strURL="deleteData.dispatch?id=" + id;
	
	$.ajax({
        type: "POST",
        url : strURL,
        success : function(msg) {
        	alert('"' + msg + '"' + ' deleted successfully!');
        	location.reload();
        },
        error: function(error) {
        	alert("Somthing went wrong!!");
        }
	});
}
</script>
</head>

<body>
	
	<h1>This value is displayed through Spring:</h1>
	<h1>${data}</h1>	<%-- This is the data from Spring system. Spring will bring the data and bind it here.--%>
	<br/>
	
	<input type="button" value = "Azax API Call" onclick="abc()">
	<div id="div">
		<h1 id = "h1"></h1>
		<h1 id = "h2"></h1>
	</div>
	<br/><br/>
	
	<form:form action="setMyModel.dispatch" method="POST" modelAttribute="myModel" id="formSubmitId">
		<label><spring:message code="label.column.name" /></label>:&nbsp<form:input type="text" id="name" path="name"></form:input><br/><br/>
		<label><spring:message code="label.column.city" /></label>:&nbsp<form:input type="text" id="city" path="city"></form:input><br/><br/>
		<input type="button" value="SUBMIT" id="submitBtnId" onclick = "submitUpdate()">
	</form:form>
	<br/>
	
	<form:form action="getMyModel.dispatch" method="GET" modelAttribute="myModel" id="formGetModel">
		<label><spring:message code="label.column.id" /></label>:&nbsp<form:input type="text" id="id" path="id"></form:input><br/><br/>
		<input type="button" value="GET DATA" onclick = "getData()">
		<c:if test="${not empty getModel.employee.name}">
			<h1>Showing the result here:</h1><br/>
			<h2>Name:</h2> <h2 style="font-weight: 33"><label>${getModel.employee.name}</label></h2><br/>
			<h2>City:</h2> <h2 style="font-weight: 33"><label>${getModel.employee.city}</label></h2><br/>
		</c:if>
	</form:form><br/>
	
	<form:form action="getMyModelList.dispatch" method="GET" modelAttribute="myModel" id="formGetModelList">
		<input type="button" value="GET LIST" onclick = "getDataList()"><br/><br/>
		<div>
			<c:if test="${not empty getModelList.employeeList}">
				<table style="border: 2px solid black;">
					<tr>
						<th align="center" style="border: 2px solid black;"><b><spring:message code="label.column.sr" /></b></th>
						<th align="center" style="border: 2px solid black;"><b><spring:message code="label.column.id" /></b></th>
						<th align="center" style="border: 2px solid black;"><b><spring:message code="label.column.name" /></b></th>
						<th align="center" style="border: 2px solid black;"><b><spring:message code="label.column.city" /></b></th>
						<th align="center" style="border: 2px solid black;"><b><spring:message code="label.column.delete" /></b></th>
						<th align="center" style="border: 2px solid black;"><b><spring:message code="label.column.update" /></b></th>
					</tr>
					<c:forEach items="${getModelList.employeeList}" var="data" varStatus="status">
						<tr>
							<td align="center" style="border: 1px solid black;">${status.index + 1}</td>
							<td align="center" style="border: 1px solid black;">${data.id}</td>
							<td align="center" style="border: 1px solid black;">${data.name}</td>
							<td align="center" style="border: 1px solid black;">${data.city}</td>
							<td align="center" style="border: 1px solid black;">
								<label class="link" for="${data.id}" onclick="deleteData(this)">Delete</label>
							</td>
							<td align="center" style="border: 1px solid black;">
								<label class="link" for="${data.id}" onclick="updateData(this)">Update</label>
								<input type="hidden" id="hName${data.id}" value="${data.name}">
								<input type="hidden" id="hCity${data.id}" value="${data.city}">
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</form:form>
	
</body>

</html>
