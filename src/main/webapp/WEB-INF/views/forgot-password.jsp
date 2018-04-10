<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 4/9/18
  Time: 4:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <c:url value="/css/uikit.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <meta charset="UTF-8">
    <title>Sign In</title>
    <script src="uikit.min.js"></script>
    <script src="uikit-icons.min.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
<!--Navbar-->
<jsp:include page="navbar.jsp" />

<!--Divider-->
<div class="uk-panel uk-panel-divider"></div>

<div class="uk-panel uk-panel-box-secondary uk-panel-space">
    <h1 class="uk-text-center">Password Reset</h1>
    <p class="uk-text-center">Please enter your email address to receive a password reset link.</p>
    <div class="uk-grid uk-grid-small">
        <div class="uk-panel uk-panel-box uk-text-center uk-container-center uk-width-1-3">
            <div class="uk-grid uk-grid-small">
                <div class="uk-panel uk-panel-box uk-text-center uk-container-center uk-width-1-3">
                    <form method="POST" class="uk-form uk-form-stacked">
                        <div class="uk-form-row">
                            <label class="uk-form-label" for="form-h-it">Email</label>
                            <div class="uk-form-controls">
                                <input type="email" class="uk-width-1-1"> <!--TODO: Add functionality-->
                            </div>
                        </div>
                        <div class="uk-form-row">
                            <a href="#" class="uk-button uk-button-primary">SEND EMAIL</a> <!--TODO: Add functionality-->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!--Footer-->
<jsp:include page="footer.jsp" />
</body>
</html>