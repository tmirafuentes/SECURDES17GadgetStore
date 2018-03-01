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
    <meta charset="UTF-8">
    <title>Admin | Troy's Toys</title>
    <c:url value="/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <div id="adminBody">
        <p id="titleH">ADMIN FUNCTIONS</p>
        <form method="POST" action="${contextPath}/addProduct">
            <button type="submit" id="btnAdminAdd">Add Item</button>
        </form>
        <div id="adminSection">
            <form method="POST" action="${contextPath}/editProduct">
            <table id="productList">
                <tr>
                    <th id="pID">Product ID</th>
                    <th id="pName">Name</th>
                    <th id="pPrice">Price</th>
                    <th id="pQuan">Quantity</th>
                    <th id="pBrand">Brand</th>
                    <th id="pDesc">Description</th>
                    <th id="pEdit">Edit</th>
                </tr>
                <c:forEach items="${products}" var="item">
                    <tr>
                        <td><c:out value="${item.productId}"/></td>
                        <td><c:out value="${item.productName}"/></td>
                        <td><c:out value="${item.productPrice}"/></td>
                        <td><c:out value="${item.productQuantity}"/></td>
                        <td><c:out value="${item.productDescription}"/></td>
                        <td><c:out value="${item.productBrand.brandName}"/></td>
                        <td><c:out value="${item.productType}"/></td>
                        <td>
                            <input type="text" value="${item}" hidden="true">
                            <button type="submit" class="bluebtn-allcaps">Edit</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </form>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>

