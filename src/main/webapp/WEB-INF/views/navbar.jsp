<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 5:03 AM
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
        <a class="uk-navbar-brand" href="index.jsp">Troy's Toys</a>
        <ul class="uk-navbar-nav">
            <li><a href="/desktops">Desktops</a></li>
            <li><a href="/laptops">Laptops</a></li>
            <li><a href="/tablets">Tablets</a></li>
            <li><a href="/mobiles">Mobiles</a></li>
        </ul>

        <div class="uk-navbar-content uk-hidden-small">
            <form class="uk-form uk-margin-remove uk-display-inline-block">
                <input type="text" placeholder="Search products...">
                <button class="uk-button uk-button-primary">Submit</button>
            </form>
        </div>

        <div class="uk-navbar-content uk-navbar-flip  uk-hidden-small">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <!-- This is the container enabling the JavaScript -->
                    <div class="uk-button-dropdown" data-uk-dropdown>
                        <!-- This is the button toggling the dropdown -->
                        <button class="uk-button">${pageContext.request.userPrincipal.name}</button>
                        <!-- This is the dropdown -->
                        <div class="uk-dropdown uk-dropdown-small">
                            <ul class="uk-nav uk-nav-dropdown">
                                <li><a href="/purchases">Purchase History</a></li>
                                <li><a href="/accountSettings">Account Settings</a></li>
                                <li><form id="logoutForm" method="POST" action="${contextPath}/logout">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                    <a onclick="document.forms['logoutForm'].submit()">Logout</a></li>
                            </ul>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="uk-button-group">
                        <a class="uk-button" href="signin.html">Sign in</a>
                        <a class="uk-button uk-button-primary" href="signup.html">Sign up</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </nav>
</body>
</html>
