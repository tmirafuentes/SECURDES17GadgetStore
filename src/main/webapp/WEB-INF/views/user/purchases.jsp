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
    <script src="../uikit.min.js"></script>
    <script src="../uikit-icons.min.js"></script>
    <script src="../jquery-3.3.1.min.js"></script>
</head>
<body>
    <!--Navbar-->
    <%@ include file="navbar.jsp" %>
        
    <!--Divider-->
    <div class="uk-panel uk-panel-divider"></div>
    
    <!--Transactions-->
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <div class="uk-grid uk-grid-small">
            <!--Sidebar-->
            <div class="uk-width-1-5">
                <div class="uk-panel uk-panel-box uk-panel-box-primary">
                    <ul class="uk-nav uk-nav-side">
                        <li><a href="/account">Account Settings</a></li>
                        <li class="uk-active"><a href="/purchases">Purchase History</a></li>
                    </ul>
                </div>
            </div>
            
            <div class="uk-panel uk-width-4-5">
                <div class="uk-panel uk-panel-box uk-panel-box-secondary">
                    <h1>Purchase History</h1>
                    <table class="uk-table">
                        <tr>
                            <th>Timestamp</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${purchases}" var="transaction">
                            <tr>
                            <td><c:out value="${purchases.timestamp}"/></td>
                            <td><c:out value="${purchases.product.productName}"/></td>
                            <td><c:out value="${purchases.quantity}"/></td>
                            <td><c:out value="${purchases.totalAmount}"/></td>
                            <td><c:out value="${purchases.status}" /></td>
                        </tr>
                        </c:forEach>
                </table>
                
                </div>
            </div>
        </div>
    </div>
        
    <!--Footer-->
    <%@ include file="footer.jsp" %>
</body>
</html>
