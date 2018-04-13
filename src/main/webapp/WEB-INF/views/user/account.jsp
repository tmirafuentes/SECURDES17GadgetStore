<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 12:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <c:url value="/css/uikit.css" var="jstlCss" />
        <link rel="stylesheet" type="text/css" href="${jstlCss}">
        <meta charset="UTF-8">
        <title>Account Settings</title>
        <script src="../uikit.min.js"></script>
        <script src="../uikit-icons.min.js"></script>
        <script src="../jquery-3.3.1.min.js"></script>
    </head>
    <body>
        <!--Navbar-->
        <jsp:include page="navbar.jsp" />
        
        <!--Divider-->
        <div class="uk-panel uk-panel-divider"></div>
        
        <div class="uk-panel uk-panel-box-secondary uk-panel-space">
            <div class="uk-grid uk-grid-small">
                <!--Sidebar-->
                <div class="uk-width-1-5">
                    <div class="uk-panel uk-panel-box uk-panel-box-primary">
                        <ul class="uk-nav uk-nav-side">
                            <li class="uk-active"><a href="/account">Account Settings</a></li>
                            <li><a href="/purchases">Purchase History</a></li>
                        </ul>
                    </div>
                </div>

                <!--Sign up-->
                <div class="uk-panel uk-width-4-5">
                    <div class="uk-panel uk-panel-box uk-panel-box-secondary">
                        <h1>Account Settings</h1>
                        <form:form method="POST" modelAttribute="userForm" class="uk-form uk-form-stacked">
                            <!--First Name-->
                            <div class="uk-form-row">
                                <spring:bind path="firstName">
                                    <form:label class="uk-form-label" for="firstname" path="firstName">First Name*</form:label>
                                    <div class="uk-form-controls">
                                        <form:input type="text" path="firstName" id="firstName" class="uk-width-1-1"></form:input>
                                        <form:errors path="firstName"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>
                            <!--Last Name-->
                            <div class="uk-form-row">
                                <spring:bind path="lastName">
                                    <form:label class="uk-form-label" for="lastName" path="lastName">Last Name*</form:label>
                                    <div class="uk-form-controls">
                                        <form:input type="text" path="lastName" id="lastName" class="uk-width-1-1"></form:input>
                                        <form:errors path="lastName"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>
                            <!--Birthdate-->
                            <div class="uk-form-row">
                                <spring:bind path="birthdate">
                                    <form:label class="uk-form-label" for="birthdate" path="birthdate">Birthdate*</form:label>
                                    <div class="uk-form-controls">
                                        <form:input type="text" class="uk-width-1-1" id="birthdate" path="birthdate" placeholder="mm-dd-yyyy" value=""></form:input>
                                        <form:errors path="birthdate"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>
                            <!--Mailing Address-->
                            <div class="uk-form-row">
                                <spring:bind path="mailAddress">
                                    <form:label class="uk-form-label" for="mailAddress" path="mailAddress">Mailing Address</form:label>
                                    <div class="uk-form-controls">
                                        <form:textarea class="uk-width-1-1" path="mailAddress" id="mailAddress"></form:textarea>
                                        <form:errors path="mailAddress"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>
                            <!--Mobile Number-->
                            <div class="uk-form-row">
                                <spring:bind path="mobileNumber">
                                    <form:label class="uk-form-label" for="mobileNumber" path="mobileNumber">Mobile Number*</form:label>
                                    <div class="uk-form-controls">
                                        <form:input type="text" class="uk-width-1-1" id="mobileNumber" path="mobileNumber"></form:input>
                                        <form:errors path="mobileNumber"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>
                            <!--Email-->
                            <div class="uk-form-row">
                                <spring:bind path="email">
                                    <form:label class="uk-form-label" for="email" path="email">Email*</form:label>
                                    <div class="uk-form-controls">
                                        <form:input type="email" class="uk-width-1-1" id="email" path="email"></form:input>
                                        <form:errors path="email"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>
                            <!--Username-->
                            <div class="uk-form-row">
                                <spring:bind path="username">
                                    <form:label class="uk-form-label" for="username" path="username">Username*</form:label>
                                    <div class="uk-form-controls">
                                        <form:input type="text" class="uk-width-1-1" id="username" path="username"></form:input>
                                        <form:errors path="username"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>
                            <!--Password-->
                            <div class="uk-form-row">
                                <spring:bind path="password">
                                    <a href="/change-password" class="uk-button">Change Password</a>
                                </spring:bind>
                            </div>
                            <!--Sign up button-->
                            <div class="uk-form-row">
                                <button type="submit" class="uk-button uk-button-primary">Save</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        
        <!--Footer-->
        <jsp:include page="footer.jsp" />
    </body>
</html>