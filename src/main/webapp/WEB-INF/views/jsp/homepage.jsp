<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<a class="navbar-brand" href="#">Spring MVC 4 Demo App</a>
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
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<main role="main" class="container">

	<div class="starter-template">
		<h1>Spring Demo Web App</h1>
		<p class="lead">Welcome to Demo App</p>
		<h4>${hostname}</h4>
	</div>

	</main>
	<!-- /.container -->

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
</body>
</html>