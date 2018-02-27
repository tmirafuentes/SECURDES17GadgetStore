<%--
  Created by IntelliJ IDEA.
  User: Troy Mirafuentes
  Date: 2/25/2018
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Troy's Toys</title>
    <c:url value="/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<!--Navbar-->
<header>
    <div class="navbartop">
        <form class="search" role="search">
            <input type="text" placeholder="Search for a product...">
            <button type="submit"></button>
        </form>
        <a href="/signin">SIGN IN</a>
        <a href="/signup" id="signuplink">SIGN UP</a>
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

<div class="main">
    <!--Ad-->
    <div class="ad">

    </div>

    <!--What's New-->
    <div class="product-grid">
        <c:forEach items="${products}" var="item">
            <c:out value="${item.productName}"/>
            <c:out value="${item.productPrice}"/>
            <br>
        </c:forEach>
    </div>
    <!--Footer-->
    <footer>
        <p><a href="/about-us">ABOUT US</a>  |  <a href="/contact-us">CONTACT US</a>  |  <a href="/tos">TERMS OF SERVICE</a></p>
        <small>&copy; 2018 Minions nina Marnel at Courtney Inc.</small>
    </footer>
</div>
</body>
</html>
