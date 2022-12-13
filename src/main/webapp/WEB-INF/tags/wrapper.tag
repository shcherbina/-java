<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
    <title>library</title>
    
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
    </head>
    <body>
    
<nav class="l#bcaaa4 brown lighten-3" role="navigation">
	<div class="nav-wrapper container">
		<a href="#" class="brand-logo">Logo</a>
		<ul class="right">
			<li><a href="index.jsp">Библиотека</a></li>
			<li><a href="e.jsp">Ввод данных</a></li>
			<li class="active"><a onclick="sendHTTPDelete('/login')">Logout</a></li>
		</ul>
	</div>
</nav>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<jsp:doBody />
		<!-- Page body will be here -->
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
