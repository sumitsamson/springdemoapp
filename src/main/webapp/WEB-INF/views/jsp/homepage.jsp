<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Spartan Utilities Home Page</title>

<!-- Bootstrap core and Custom styles CSS -->
<link rel="stylesheet"
	href="<c:url value="/static/css2/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/static/css2/starter-template.css" />">

</head>
<body>

	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="#">Spring MVC 4</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link disabled"
					href="#">Home</a></li>
			</ul>

			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="text"
					placeholder="localhost" aria-label="check_ping" id="host_name">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
					id="btn_ping">Ping</button>

			</form>


		</div>
	</nav>

	<main role="main">
	<div class="jumbotron">
		<h1>Spring Demo Web App</h1>
		<p>This is a sample application build using Spring MVC + Maven +
			Mysql</p>
		<div class="row">
			<div class="col-md-8">
				<table class="table">
					<tbody>
						<tr>
							<th>Host Details</th>
							<td class="alert alert-info">${hostname}</td>
						</tr>
						<tr>
							<th>Database Connection Details</th>
							<c:choose>
								<c:when test="${not empty db_exception}">
									<td class="alert alert-danger">${db_exception}</
									<td>
								</c:when>
								<c:otherwise>
									<td class="alert alert-info">${db_url}</td>>
							</c:otherwise>
							</c:choose>

						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-4">
				<div class="alert alert-info alert-dismissible" id="ping_response"
					style="display: none"></div>
			</div>
		</div>

	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h4>Employee List</h4>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>No</th>
							<th>Employee ID</th>
							<th>Name</th>
							<th>Designation</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${listEmployees}"
							varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${employee.empId}</td>
								<td>${employee.empName}</td>
								<td>${employee.designation}</td>
								<td>
									<button id="editEmpBtn" type="button" class="btn btn-info btn-xs emp"
										data-toggle="modal" data-target="#editdeleteEmpModal"
										data-value="edit" data-id="${employee.empId}" >Edit</button>
									<button id="deleteEmpBtn" type="button" class="btn btn-danger btn-xs emp"
										data-toggle="modal" data-target="#editdeleteEmpModal"
										data-value="del" data-id="${employee.empId}" >Delete</button>
								</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-4">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#saveEmpModal">Add New Employee</button>
			</div>
		</div>
	</div>
	</main>
	<!-- Employee List end -->

	<!-- Add new employee Modal -->
	<div id="saveEmpModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Save Employee</h4>
				</div>
				<div class="modal-body">
					<form:form id="employeeDetails" class="needs-validation"
						method="post" modelAttribute="employee" action="saveOrUpdate">
						<table>
							<tr>
								<td>Employee Name</td>
								<td><form:input path="empName" /></td>
							</tr>
							<tr>
								<td>Designation</td>
								<td><form:input path="designation" /></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="Save" class="btn btn-primary"></td>
							</tr>

						</table>

					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Edit/Delete Employee Modal -->

	<div id="editdeleteEmpModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 id="modal_title" class="modal-title">Edit Employee</h4>
				</div>
				<div class="modal-body">
					<form:form id="employeeDetails2" method="post"
						modelAttribute="employee" action="">
						<table>
							<tr>
								<td>Employee Name</td>
								<td><form:input path="empName" name="empName" />
								 <form:hidden path="empId" name="empId"/>								
								</td>
							</tr>
							<tr>
								<td>Designation</td>
								<td><form:input path="designation" name="designation" /></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									id="editdeleteBtn" value="Edit" class="btn btn-primary"></td>
							</tr>

						</table>

					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>



	<!-- Edit/Delete Employee Modal ends -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- <script type="text/javascript"	src="<c:url value="/static/js2/jquery-slim.min.js" />"></script>  -->
	<script type="text/javascript"
		src="<c:url value="/static/js2/bootstrap.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/static/js2/popper.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/static/js2/holder.min.js" />"></script>
	<script type="text/javascript">
		<c:url var="pinghost" value="/ping"/>
		

		$(document).ready(
				function() {
                      
					var employeeList = JSON.parse('${listEditDelete}');
					$("#btn_ping").click(
							function(e) {
								e.preventDefault();
								var hostname = $("#host_name").val();
								var html = 'Waitng Response from host '
										+ hostname + ' ....'
								$('#ping_response').html(html);

								$("#ping_response").show();

								$.ajax({
									url : "${pinghost}",
									type : "POST",
									data : {
										hostname : hostname
									},
									dataType : 'text',
									success : function(data) {
										html = ''
										html += '<strong>Ping Response from '
												+ hostname + ' : </strong> ';
										html += data;
										$('#ping_response').html(html);

									},
									error : function(jqXHR, textStatus) {
										console.log("Request failed: "
												+ textStatus);

									}
								});

							});

					$('.emp').on('click', function(e) {
						 // Get the record's ID via attribute
						  var id = $(this).attr('data-id');		
						  var operation = $(this).attr('data-value');
										
						  var name = '';
						  var designation = '';
						  var employeeId = -1;
						  $.each(employeeList, function(index, value) {
								 employeeId = value.empId;
							    	if (id == employeeId) {
										name = value.empName;
										designation = value.designation;
										return true;
										}
							});
													
		   				// alert("Your values are :" + id + " " + operation +" "+name+" "+designation);
						 $('#employeeDetails2')
						  .find('[name="empId"]').val(employeeId).end()
						  .find('[name="empName"]').val(name).end()
						  .find('[name="designation"]').val(designation).end();
						  
					if ("edit" == operation) {
												
							$("#modal_title").text("Edit Employee ");
							$("#editdeleteBtn").val("Edit");
							$("#editdeleteBtn").addClass("btn-primary");
							$("#editdeleteBtn").removeClass("btn-danger");
							$("#employeeDetails2").attr('action', 'saveOrUpdate');

						} else if ("del" == operation) {
							
							$("#modal_title").text("Delete Employee ");
							$("#editdeleteBtn").val("Delete");
							$("#editdeleteBtn").removeClass("btn-primary");
							$("#editdeleteBtn").addClass("btn-danger");
							$("#employeeDetails2").attr('action', 'deleteEmployee');							
						} else {
							alert("Something went wrong..");
						}	  
						  
					})
				});
	</script>
</body>
</html>