<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 6:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Troy's Toys</title>
    <c:url value="/resources/static/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="heading">
    <h1>Uh-oh! An error came up.</h1>
</div>
<div style="margin-left:20px; margin-right:20px; margin-bottom:20px;">
    <p>Sorry, we cannot find the page that you were looking for.
        Try again or <a href="/index" class="bluebtn-allcaps">GO BACK TO HOME</a></p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
