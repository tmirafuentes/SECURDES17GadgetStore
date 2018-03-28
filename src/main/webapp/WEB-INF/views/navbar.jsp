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
<!--
<head>
    <link rel="stylesheet" type="text/css" href="index.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <meta charset="UTF-8">
</head>-->
<body>
<!--Navbar-->
<header>
    <div class="navbartop">
        <table id="navTable">
            <tr>
                <td><p id="logo"> Troy's Toys</p></td>
                <td>
                    <form class="search" role="search" id="searchPart">
                        <input type="text" placeholder="Search for a product..." id="searchBar">
                    </form>
                </td>
                <td id="dropdownHolder">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <div class="dropdown">
                                <button class="dropbtn">${pageContext.request.userPrincipal.name}</button>
                                <div class="dropdown-content">
                                    <a href="/purchases">Purchase History</a>
                                    <a href="/accountSettings">Account Settings</a>
                                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                    <a onclick="document.forms['logoutForm'].submit()">Logout</a>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="/signin" id="signin">SIGN IN</a>
                            <a href="/signup" id="signuplink">SIGN UP</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </div>
    <nav>
        <ul>
            <li><a href="/desktops">DESKTOPS</a></li>
            <li><a href="/laptops">LAPTOPS</a></li>
            <li><a href="/tablets">TABLETS</a></li>
            <li><a href="/mobiles">MOBILES</a></li>
        </ul>
    </nav>
</header>
</body>
</html>
