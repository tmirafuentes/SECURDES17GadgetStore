<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 11:45 AM
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
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="heading">
            <h1>Sign Up to Troy's Toys</h1>
        </div>
        <div class="container">
            <div id="login">
                <form:form method="POST" modelAttribute="custForm" class="form-signup">
                    <div class="div-form">
                        <table>
                            <tr>
                                <spring:bind path="firstName">
                                    <td><form:label for="firstname" class="formlabel">First Name</form:label></td>
                                    <td>
                                        <form:input type="text" path="firstName" id="firstname"></form:input>
                                    </td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <spring:bind path="lastName">
                                    <td><form:label for="lastName" class="formlabel">Last Name</form:label></td>
                                    <td>
                                        <form:input type="text" path="lastName" id="lastname"></form:input>
                                    </td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <spring:bind path="birthdate">
                                    <td><form:label for="birthdate" class="formlabel">Birthdate</form:label></td>
                                    <td>
                                        <form:input type="date" path="birthdate" id="birthdate"></form:input>
                                    </td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <spring:bind path="email">
                                    <td><form:label for="email" class="formlabel">Email</form:label></td>
                                    <td>
                                        <form:input type="email" path="email" id="email"></form:input>
                                    </td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <td><label class="formlabel">Password</label></td>
                                <td><a href="/changepassword.html">Change password...</a></td>
                            </tr>
                            <tr>
                                <spring:bind path="mailAddress">
                                    <td><form:label for="mailAddress" class="formlabel">Mailing Address</form:label></td>
                                    <td>
                                        <form:textarea path="mailAddress" id="mailingaddress"></form:textarea>
                                    </td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <spring:bind path="mobileNumber">
                                    <td><form:label for="mobileNumber" class="formlabel">Mobile Number</form:label></td>
                                    <td>
                                        <form:input type="text" path="mobileNumber" id="mobilenum"></form:input>
                                    </td>
                              </spring:bind>
                            </tr>
                            <tr>
                                <td/>
                                <td><button type="submit" class="bluebtn-allcaps">Save</td>
                            </tr>
                        </table>
                    </div>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
</html>
