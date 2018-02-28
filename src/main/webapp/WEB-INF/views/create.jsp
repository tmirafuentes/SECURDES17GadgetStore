<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Create Account</title>
</head>
<body>
    <form:form method="POST" modelAttribute="custForm" class="form-signin">
        <h1>Create your account</h1>
        <spring:bind path="username">
            <div>
                <form:input type="text" path="username" placeholder="Username" autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div>
                <form:input type="password" path="password" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div>
                <form:input type="password" path="passwordConfirm" placeholder="Retype password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>

        <button type="submit">Submit</button>
    </form:form>
</body>
</html>
