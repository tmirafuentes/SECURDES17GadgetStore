<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 5:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <c:url value="/css/uikit.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <meta charset="UTF-8">
</head>
<body>
    <nav class="uk-navbar">
        <a class="uk-navbar-brand" href="/index">Troy's Toys</a>
        <ul class="uk-navbar-nav">
            <li><a href="/desktops">Desktops</a></li>
            <li><a href="/laptops">Laptops</a></li>
            <li><a href="/tablets">Tablets</a></li>
            <li><a href="/mobiles">Mobiles</a></li>
        </ul>

        <div class="uk-navbar-content uk-hidden-small">
            <form method="GET" action="${contextPath}/search" class="uk-form uk-margin-remove uk-display-inline-block">
                <input id="search" name="searchString" type="text" placeholder="Search products...">
                <button type="submit" class="uk-button uk-button-primary">Submit</button>
            </form>
        </div>

        <div class="uk-navbar-content uk-navbar-flip  uk-hidden-small">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <div class="uk-button-group">
                        <a class="uk-button" href="/account">${pageContext.request.userPrincipal.name}</a> <!--leads to account settings-->
                        <form id="logoutForm" method="POST" action="${contextPath}/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <a class="uk-button uk-button-danger" onclick="document.forms['logoutForm'].submit()">Logout</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="uk-button-group">
                        <a class="uk-button" href="/signin">Sign in</a>
                        <a class="uk-button uk-button-primary" href="/signup">Sign up</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </nav>
</body>
</html>
