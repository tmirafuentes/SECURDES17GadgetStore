<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 9:45 PM
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
    <title>Admin | Troy's Toys</title>
    <script src="../uikit.min.js"></script>
    <script src="../uikit-icons.min.js"></script>
    <script src="../jquery-3.3.1.min.js"></script>
</head>
<body>

    <!--Navbar-->
    <%@ include file="../user/navbar.jsp" %>
    
    <!--Body-->
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <h1>Transactions</h1>
        <form method="POST" action="${contextPath}/cancelTransaction">
            <table class="uk-table">
                <tr>
                    <th>Trans ID</th>
                    <th>Timestamp</th>
                    <th>Prod ID</th>
                    <th>Cust ID</th>
                    <th>Qty</th>
                    <th>Total Amt</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                <c:forEach items="${transactions}" var="transaction">
                    <tr>
                        <td><c:out value="${transaction.transactionId}"/></td>
                        <td><c:out value="${transaction.timestamp}"/></td>
                        <td><c:out value="${transaction.product.productId}"/></td>
                        <td><c:out value="${transaction.user.userId}"></c:out></td>
                        <td><c:out value="${transaction.quantity}"/></td>
                        <td><c:out value="${transaction.totalAmount}"/></td>
                        <td><c:out value="${transaction.status}"/></td>
                        <c:choose>
                            <c:when test="${transaction.status}">
                                <button type="submit" class="uk-button uk-button-danger">CANCEL</button>
                            </c:when>
                            <c:otherwise> <!--Disabled when canceled already-->
                                <button type="submit" class="uk-button uk-button-danger" disabled>CANCEL</button>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
        
    <!--Footer-->
    <%@ include file="../user/footer.jsp" %>
</body>
</html>
