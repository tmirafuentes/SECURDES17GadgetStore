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
    <c:url value="/resources/static/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <link href="https://github.com/theleagueof/league-spartan/blob/master/_webfonts/stylesheet.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Admin | Troy's Toys</title>
</head>
<body>
    <div id="adminBody">
        <p id="titleH">ADMIN Transaction Functions</p>
        <div id="adminSection">
            <form method="POST" action="${contextPath}/cancelTransaction">
                <table id="transactionList">
                    <tr>
                        <th id="tID">Transaction ID</th>
                        <th id="pID">Product ID</th>
                        <th id="cID">Customer ID</th>
                        <th id="tAmt">Total Amount</th>
                        <th id="tStamp">Time Stamp</th>
                        <th id="tStat">Status</th>
                    </tr>
                    <c:forEach items="${transactions}" var="transaction">
                        <tr>
                            <td><c:out value="${transaction.transactionId}"/></td>
                            <td><c:out value="${transaction.timestamp}"/></td>
                            <td><c:out value="${transaction.product.productId}"/></td>
                            <td><c:out value="${transaction.product.productName}"/></td>
                            <td><c:out value="${transaction.quantity}"/></td>
                            <td><c:out value="${transaction.totalAmount}"/></td>
                            <td><c:out value="${transaction.status}" /></td>
                            <td><button type="submit" class="bluebtn-allcaps">Cancel</button></td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
