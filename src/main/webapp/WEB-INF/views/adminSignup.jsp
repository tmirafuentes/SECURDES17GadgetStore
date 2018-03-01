<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin | Troy's Toys</title>
    <c:url value="/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <link href="https://github.com/theleagueof/league-spartan/blob/master/_webfonts/stylesheet.css" rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <div class="heading">
        <h1>Admin Sign Up</h1>
    </div>
    <div class="container">
        <div id="login">
            <form:form method="POST" modelAttribute="adminForm" class="form-signup"> <!--TODO: create AdminController-->
                <div class="div-form">
                    <table>
                        <tr>
                            <spring:bind path="firstName">
                                <td><form:label for="firstnameAdmin" class="formlabel" path="firstName">First Name</form:label></td>
                                <td>
                                    <form:input type="text" path="firstName" id="firstnameAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="lastName">
                                <td><form:label for="lastNameAdmin" class="formlabel" path="lastName">Last Name</form:label></td>
                                <td>
                                    <form:input type="text" path="lastName" id="lastnameAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="email">
                                <td><form:label for="emailAdmin" class="formlabel" path="email">Email</form:label></td>
                                <td>
                                    <form:input type="email" path="email" id="emailAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="username">
                                <td><form:label for="emailAdmin" class="formlabel" path="username">Username</form:label></td>
                                <td>
                                    <form:input type="text" path="username" id="username" value="Admin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="password">
                                <td><form:label path="password" class="formlabel">Password</form:label></td>
                                <td>
                                    <form:input type="password" path="password" id="passwordAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="passwordConfirm">
                                <td><form:label path="passwordConfirm" class="formlabel">Retype Password</form:label></td>
                                <td>
                                    <form:input type="password" path="passwordConfirm" id="passwordConfirmAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button type="submit" class="bluebtn-allcaps">Save</button></td>
                        </tr>
                    </table>
                </div>
            </form:form>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
