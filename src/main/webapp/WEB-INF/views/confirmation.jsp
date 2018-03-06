<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>Troy's Toys</title>
    <c:url value="/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <link href="https://github.com/theleagueof/league-spartan/blob/master/_webfonts/stylesheet.css" rel="stylesheet">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <h1 id= "headConfirm">Confirm Purchasing Details</h1>

    <form:form method="POST" action="${contextPath}/buyProduct">
    <div id="holdingAllCon">
        <div id="imageConfirm">
            <img id="productPicConfirmation">
        </div>
        <div id="confirmHolder"> <!--TODO: Fix variables-->
            <p id ="productNameConfirmation">${indiProd.productName}</p>
            <p class="phraseConfirm">Pesos:</p>
            <p id ="productPriceConfirmation">${indiProd.productPrice}</p>
            <p class="phraseConfirm">Quantity:</p>
            <p id ="productQuantityConfirmation">${prodQty}</p>
            <p class="phraseConfirm">Current mailing address:</p>
            <p id ="customerMailing">${currCust.mailAddress}</p>
            <div id="buttonsConfirm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="text" value="${indiProd.productId}" hidden="true" name="prodId">
                <input type="text" value="${currCust.username}" hidden="true" name="custName">
                <input type="number" id="scrollQuantity" hidden value="${prodQty}" name="prodQty">
                <button type="submit" id="btnConfirmPurchase">Confirm</button>
            </div>
        </div>
    </div>
    </form:form>

    <%@ include file="footer.jsp" %>
</body>
</html>
