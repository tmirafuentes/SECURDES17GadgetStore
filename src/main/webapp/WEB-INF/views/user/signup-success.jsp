<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
<head>
    <c:url value="/css/uikit.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <meta charset="UTF-8">
    <title>Troy's Toys</title>
    <script src="../uikit.min.js"></script>
    <script src="../uikit-icons.min.js"></script>
    <script src="../jquery-3.3.1.min.js"></script>
</head>
<body>
    <!--Navbar-->
    <%@ include file="navbar.jsp" %>

    <!--Sign Up Success-->
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <h1 class="uk-text-center">Sign up</h1>
        <div class="uk-text-center uk-container-center uk-width-1-3 uk-alert uk-alert-success">
            You signed up successfully. <a href="signin">Click here to sign in.</a>
        </div>
    </div>
    
    <!--Footer-->
    <%@ include file="footer.jsp" %>
</body>
</html>