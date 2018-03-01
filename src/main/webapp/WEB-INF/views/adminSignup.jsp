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
    <c:url value="/resources/static/css/index.css" var="jstlCss" />
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
            <form:form method="POST" class="form-signup"> <!--TODO: create AdminController-->
                <div class="div-form">
                    <table>
                        <tr>
                            <spring:bind path="firstNameAdmin">
                                <td><form:label for="firstnameAdmin" class="formlabel" path="firstNameAdmin">First Name</form:label></td>
                                <td>
                                    <form:input type="text" path="firstNameAdmin" id="firstnameAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="lastNameAdmin">
                                <td><form:label for="lastNameAdmin" class="formlabel" path="lastNameAdmin">Last Name</form:label></td>
                                <td>
                                    <form:input type="text" path="lastNameAdmin" id="lastnameAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="emailAdmin">
                                <td><form:label for="emailAdmin" class="formlabel" path="emailAdmin">Email</form:label></td>
                                <td>
                                    <form:input type="email" path="emailAdmin" id="emailAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="passwordAdmin">
                                <td><form:label path="passwordAdmin" class="formlabel">Password</form:label></td>
                                <td>
                                    <form:input type="password" path="passwordAdmin" id="passwordAdmin"></form:input>
                                </td>
                            </spring:bind>
                        </tr>
                        <tr>
                            <spring:bind path="passwordConfirmAdmin">
                                <td><form:label path="passwordConfirmAdmin" class="formlabel">Retype Password</form:label></td>
                                <td>
                                    <form:input type="password" path="passwordConfirmAdmin" id="passwordConfirmAdmin"></form:input>
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
