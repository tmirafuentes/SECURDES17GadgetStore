<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Troy's Toys</title>
    <c:url value="/resources/static/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="heading">
        <h1>Purchase History</h1>
    </div>
    <div class="container">
        <table>
            <tr>
                <th>Timestamp</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Total Amount</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${transactions}" var="transaction">
                <tr>
                    <td><c:out value="${transaction.timestamp}"/></td>
                    <td><c:out value="${transaction.product.productName}"/></td>
                    <td><c:out value="${transaction.quantity}"/></td>
                    <td><c:out value="${transaction.totalAmount}"/></td>
                    <td><c:out value="${transaction.status}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
