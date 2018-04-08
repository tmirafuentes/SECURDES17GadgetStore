<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 4/8/18
  Time: 5:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <c:url value="/css/uikit.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <meta charset="UTF-8">
</head>
<body>
    <nav class="uk-navbar">
        <a class="uk-navbar-brand" href="${contextPath}/admin">Troy's Toys</a>
        <ul class="uk-navbar-nav"> <!--TODO: Separate admin home and products -->
            <li><a href="${contextPath}/admin">Products</a></li>
            <li><a href="${contextPath}/admin-accounts">Accounts</a></li>
            <li><a href="${contextPath}/admin-trans">Transactions</a></li>
        </ul>

        <div class="uk-navbar-content uk-hidden-small">
            <form class="uk-form uk-margin-remove uk-display-inline-block">
                <input type="text" placeholder="Search products...">
                <button class="uk-button uk-button-primary">Submit</button>
            </form>
        </div>

        <div class="uk-navbar-content uk-navbar-flip  uk-hidden-small">
            <!--Log out-->
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <button class="uk-button" onclick="document.forms['logoutForm'].submit()">Logout</button>
        </div>
    </nav>
</body>
</html>
