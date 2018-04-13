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
    <c:url value="/css/uikit.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <meta charset="UTF-8">
    <title>Troy's Toys</title>
    <script src="uikit.min.js"></script>
    <script src="uikit-icons.min.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
<!--Navbar-->
<%@ include file="user/navbar.jsp" %>

<div class="uk-panel uk-panel-box-secondary uk-panel-space">
    <div class="uk-alert uk-alert-large">
        <h1>Uh-oh! An error came up.</h1>
        <p>Sorry, we cannot find the page that you were looking for. Try again or <a href="index" class="uk-button uk-button-primary">GO BACK TO HOME</a></p>
    </div>
</div>

<!--Footer-->
<%@ include file="user/footer.jsp" %>
</body>
</html>