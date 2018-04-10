<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 4/9/18
  Time: 4:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <c:url value="/css/uikit.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <meta charset="UTF-8">
    <title>Password Reset</title>
    <script src="../uikit.min.js"></script>
    <script src="../uikit-icons.min.js"></script>
    <script src="../jquery-3.3.1.min.js"></script>
</head>
<body>
<!--Navbar-->
<jsp:include page="navbar.jsp" />

<!--Divider-->
<div class="uk-panel uk-panel-divider"></div>

<div class="uk-panel uk-panel-box-secondary uk-panel-space">
    <h1 class="uk-text-center">Password Reset</h1>
    <div class="uk-text-center uk-container-center uk-width-1-2 uk-alert uk-alert-success">
        Your password has been reset. <a href="/signin">Click here to sign in.</a>
    </div>
</div>

<!--Footer-->
<jsp:include page="footer.jsp" />
</body>
</html>