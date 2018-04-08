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
        <c:url value="/css/uikit.css" var="jstlCss" />
        <link rel="stylesheet" type="text/css" href="${jstlCss}">
        <meta charset="UTF-8">
        <title>Admin | Troy's Toys</title>
        <script src="uikit.min.js"></script>
        <script src="uikit-icons.min.js"></script>
        <script src="jquery-3.3.1.min.js"></script>
    </head>
    <body>
        <!--Navbar-->
        <%@ include file="navbar.jsp" %>
        
        <!--Body-->
        <div class="uk-panel uk-panel-box uk-panel-space">
            <h1 class="uk-text-center">Admin Sign Up</h1>
            <div class="uk-grid uk-grid-small">
                <div class="uk-panel uk-panel-box uk-text-center uk-container-center uk-width-1-3">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <form:form method="POST" modelAttribute="prodForm" class="uk-form uk-form-stacked">
                        <!----------First Name----------->
                        <spring:bind path="firstName">
                            <div class="uk-form-row">
                                <form:label path="firstName" class="uk-form-label" for="firstnameAdmin">First Name <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="text" path="firstName" id="firstnameAdmin" name="firstnameAdmin" class="uk-width-1-1"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!----------Last Name----------->
                        <spring:bind path="lastName">
                            <div class="uk-form-row">
                                <form:label path="lastName" class="uk-form-label" for="lastnameAdmin">Last Name <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="text" path="lastName" id="lastnameAdmin" name="lastnameAdmin" class="uk-width-1-1"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!------------Email------------->
                        <spring:bind path="email">
                            <div class="uk-form-row">
                                <form:label path="email" class="uk-form-label" for="emailAdmin">Email <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="email" path="email" id="emailAdmin" name="emailAdmin" class="uk-width-1-1"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!----------Username------------>
                        <spring:bind path="username">
                            <div class="uk-form-row">
                                <form:label path="username" class="uk-form-label" for="username">Last Name <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="text" path="username" id="username" name="username" value="Admin" class="uk-width-1-1"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!----------Password------------>
                        <spring:bind path="password">
                            <div class="uk-form-row">
                                <form:label path="password" class="uk-form-label" for="password">Last Name <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="password" path="password" id="passwordAdmin" name="password" class="uk-width-1-1"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!-------Password Confirm------->
                        <spring:bind path="passwordConfirm">
                            <div class="uk-form-row">
                                <form:label path="passwordConfirm" class="uk-form-label" for="passwordConfirm">Last Name <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="password" path="passwordConfirm" id="passwordConfirmAdmin" name="passwordConfirm" class="uk-width-1-1"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!-------Password Confirm------->
                        <div class="uk-form-row">
                            <button type="submit" class="uk-button uk-button-primary">Save</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        
        <!--Footer-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
