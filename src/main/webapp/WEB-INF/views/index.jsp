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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Troy's Toys</title>
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
    <%@ include file="navbar.jsp" %>

    <!--What's New-->
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <form:form method="get">
            <h1>What's New?</h1>
            <c:choose>
                <c:when items="${empty products}">
                    <div class="uk-text-center uk-container-center uk-width-1-1 uk-alert uk-alert-danger">
                        No products to display.
                    </div>
                </c:when>
                <c:otherwise>
                    <ul class="uk-grid uk-grid-small uk-grid-width-small-1-2 uk-grid-width-medium-1-4 uk-grid-width-large-1-6">
                        <c:forEach items="${products}" var="item">
                            <li><div class="uk-panel uk-panel-box uk-text-center">
                                <img src="https://s7d2.scene7.com/is/image/SamsungUS/Pdpkeyfeature-sm-t350nzaaxar-600x600-C1-062016?$product-details-jpg$" />
                                <h4><c:out value="${item.productName}"/></h4>
                                <h5><c:out value="${item.productPrice}"/></h5>
                                <form id="formPrice">
                                    <input type="text" value="${item.productId}" hidden="true" name="prodId">
                                    <a href="/viewProduct?prodId=${item.productId}" class="uk-button uk-button-primary" >VIEW DETAILS</a>
                                </form>
                            </div></li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </form:form>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
