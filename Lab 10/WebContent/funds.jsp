<%@page import="com.fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Funding Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/item.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Funding Bodies Management</h1>
				<form id="formItem" name="formItem">
					Funder Name: <input id="funderName" name="funderName" type="text"
						class="form-control form-control-sm"> <br> Funder
					Address: <input id="funderAddress" name="funderAddress" type="text"
						class="form-control form-control-sm"> <br> Company
					Name: <input id="companyName" name="companyName" type="text"
						class="form-control form-control-sm"> <br> Company
					Address: <input id="companyAddress" name="companyAddress"
						type="text" class="form-control form-control-sm"> <br>
					Funder Email: <input id="funderEmail" name="funderEmail"
						type="text" class="form-control form-control-sm"> <br>
					Funder Phone: <input id="funderPhone" name="funderPhone"
						type="text" class="form-control form-control-sm"> <br>
					Fund Amount: <input id="fundAmount" name="fundAmount" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidFundIDSave" name="hidFundIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divFundsGrid">
					<%
					fund fundObj = new fund();
					out.print(fundObj.readFunds());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>