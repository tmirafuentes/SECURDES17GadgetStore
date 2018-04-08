<!-- ADMIN DISPLAY ALL PRODUCTS-->
<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <%@ include file="admin-navbar.jsp" %>

    <!--Body-->
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <h1>Products</h1>
        <a href="${contextPath}/addProduct" class="uk-button uk-button-success uk-button-large">+ ADD NEW PRODUCT</a>
        <form:form method="GET">
            <table class="uk-table">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Qty</th>
                    <!--<th>Brand</th>--><!--TODO: Fix brand-->
                    <th>Description</th>
                    <th>Type</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${products}" var="item">
                    <tr>
                        <td><c:out value="${item.productId}"/></td>
                        <td><c:out value="${item.productName}"/></td>
                        <td><c:out value="${item.productPrice}"/></td>
                        <td><c:out value="${item.productQuantity}"/></td>
                        <!--<td><c:out value="${item.productBrand}"/></td>--><!--TODO: Fix brand-->
                        <td><c:out value="${item.productDescription}"/></td>
                        <td><c:out value="${item.productType}"/></td>
                        <td><a href="/deleteProduct?prodId=${item.productId}" class="uk-button uk-button-danger">Delete</a></td>
                        <td><input type="text" value="${item.productId}" hidden="true" name="prodId"><a href="/editProduct?prodId=${item.productId}" class="uk-button">Edit</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form:form>
    </div>

    <!--Footer-->
    <%@ include file="admin-footer.jsp" %>
</body>
</html>

