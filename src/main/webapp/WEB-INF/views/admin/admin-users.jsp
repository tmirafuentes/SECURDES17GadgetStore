<!-- ADMIN DISPLAY ALL ACCOUNTS-->
<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 4/8/18
  Time: 13:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <%@ include file="admin-navbar.jsp" %>

    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <h1>Accounts</h1>
        <table class="uk-table">
            <tr>
                <th>Username</th>
                <th>Last Sign in</th>
                <th>Status</th>
                <th>Enable/Disable Account</th>
            </tr>
            <c:forEach items="${users}" var="user" varStatus="index">
                <tr> <!--TODO: Add functionality-->
                    <td><c:out value="${user.username}"/></td>
                    <td><c:choose><c:when test="${empty logins.get(index.index)} ">
                        Hasn't logged in
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate value="${logins.get(index.index).time}" pattern="yyyy-MM-dd HH-mm-ss" />
                        </c:otherwise>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${user.enabled}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Deactivated
                        </c:otherwise>
                    </c:choose></td>
                    <td>
                        <c:choose>
                            <c:when test="${not user.enabled}">
                                <a href="${contextPath}/admin/accountEnable?a=${user.linkId}" class="uk-button uk-button-success">Enable</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${contextPath}/admin/accountDisable?a=${user.linkId}" class="uk-button uk-button-danger">Disable</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
        

    <!--Footer-->
    <%@ include file="admin-footer.jsp" %>
</body>
</html>

