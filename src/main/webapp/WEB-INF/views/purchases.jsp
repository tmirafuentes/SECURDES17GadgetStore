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
    <c:url value="/css/uikit.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <meta charset="UTF-8">
    <title>Purchase History</title>
    <script src="uikit.min.js"></script>
    <script src="uikit-icons.min.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
    <!--Navbar-->
    <%@ include file="navbar.jsp" %>
    
    <!--Transactions-->
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
            <h1>Purchase History</h1>
            <table class="uk-table">
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
        
    <!--Footer-->
    <%@ include file="footer.jsp" %>
</body>
</html>
