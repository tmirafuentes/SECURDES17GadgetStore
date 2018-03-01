<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
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
            <h1>Sign In</h1>
        </div>
        <div class="container">
            <div id="login">
                <form>
                    <div class="div-form">
                        <table>
                            <tr>
                                <td><label for="email" class="formlabel">Email</label></td>
                                <td><input type="email" name="email" id="email" /></td>
                            </tr>
                            <tr>
                                <td><label for="password" class="formlabel">Password</label></td>
                                <td><input type="password" name="password" id="password"/></td>
                            </tr>
                            <tr>
                                <td />
                                <td><a href="forgot.jsp">Forgot password?</a></td>
                            </tr>
                            <tr>
                                <td />
                                <td><input type="submit" value="Sign in" class="bluebtn-allcaps"></td>
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
