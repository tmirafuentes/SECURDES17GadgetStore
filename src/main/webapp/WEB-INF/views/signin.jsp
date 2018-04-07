<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 12:20 PM
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
            <h1>Sign In</h1>
            <div class="uk-grid uk-grid-small">
                <div class="uk-panel uk-panel-box uk-text-center uk-container-center uk-width-1-3">
                    <form method="POST" action="${contextPath}/signin" class="uk-form uk-form-stacked">
                        
                        <!--Message-->
                        <div class="uk-form-row">
                            ${message}
                        </div>
                        
                        <!--Username-->
                        <div class="uk-form-row">
                            <label class="uk-form-label" for="username">Username</label>
                            <div class="uk-form-controls">
                                <input type="text" name="username" id="username" autofocus="true" class="uk-width-1-1"/>
                            </div>
                        </div>
                        
                        <!--Password-->
                        <div class="uk-form-row">
                            <label class="uk-form-label" for="password">Password</label>
                            <div class="uk-form-controls">
                                <input type="password" name="password" id="password" class="uk-width-1-1" />
                            </div>
                        </div>
                        
                        <!--Error message-->
                        <div class="uk-form-row">
                            ${error}
                        </div>
                        
                        <!--Forgot password-->
                        <div class="uk-form-row">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <a href="forgot.jsp">Forgot password?</a>
                        </div>
                        
                        <!--Sign in button-->
                        <div class="uk-form-row">
                            <button type="button" class="uk-button uk-button-primary">Sign in</button>
                        </div>
                        
                        <!--Sign up link-->
                        <div class="uk-form-row">
                            No account? <a href="signup.jsp">Sign up here.</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
