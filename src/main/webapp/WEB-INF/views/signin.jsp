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
    <c:url value="/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <link href="https://github.com/theleagueof/league-spartan/blob/master/_webfonts/stylesheet.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Sign In</title>
</head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="heading">
            <h1>Sign In</h1>
        </div>
        <div class="container">
            <div id="login">
                <form method="POST" action="${contextPath}/signin" class="form-signin">
                    <div class="div-form">
                        <table>
                            <tr>
                                <td colspan='2'>${message}</td>
                            </tr>
                            <tr>
                                <td><label for="username" class="formlabel">Username</label></td>
                                <td><input type="text" name="username" id="username" autofocus="true" /></td>
                            </tr>
                            <tr>
                                <td><label for="password" class="formlabel">Password</label></td>
                                <td><input type="password" name="password" id="password" /></td>
                            </tr>
                            <tr>
                                <td colspan='2'>${error}</td>
                            </tr>
                            <tr>
                                <td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></td>
                                <td><a href="forgot.jsp">Forgot password?</a></td>
                            </tr>
                            <tr>
                                <td />
                                <td><button type="submit" class="bluebtn-allcaps">Log In</button></td>
                            </tr>
                            <tr>
                                <td />
                                <td>No account? <a href="signup.jsp">Sign up here.</a></td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
