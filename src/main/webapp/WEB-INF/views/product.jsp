<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 6:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Troy's Toys</title>
    <c:url value="/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <link href="https://github.com/theleagueof/league-spartan/blob/master/_webfonts/stylesheet.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div id="holderProd">
    <div id="picProd">
        <img src="sample.gif" class="imgProd">
    </div>
    <div id="details">
        <p class="pName"><!--RETRIEVE PRODUCT NAME--></p>
        <p class="pPrice"><!--RETRIEVE PRODUCT PRICE--></p>
        <p class="pDescription"><!--RETRIEVE PRODUCT DESCRIPTION--></p>
        <form method="POST" action="${contextPath}/buyProduct">
            <div id="tranProd">
                <input type="number" id="scrollQuantity" value=1>
                <button type="submit" id="btnAddCart" class="bluebtn-allcaps-large">BUY NOW</button>
            </div>
        </form>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
