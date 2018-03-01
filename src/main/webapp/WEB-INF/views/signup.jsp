<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 11:45 AM
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
            <h1>Sign Up</h1>
        </div>
        <div class="container">
            <div id="login">
                <form>
                    <div class="div-form">
                        <table>
                            <tr>
                                <td><label for="firstname" class="formlabel">First Name <span class="red-text">*</span></label></td>
                                <td><input type="text" id="firstname" name="firstname" /></td>
                            </tr>
                            <tr>
                                <td><label for="lastname" class="formlabel">Last Name <span class="red-text">*</span></label></td>
                                <td><input type="text" id="lastname" name="lastname" /></td>
                            </tr>
                            <tr>
                                <td><label for="birthdate" class="formlabel">Birthdate <span class="red-text">*</span></label></td>
                                <td><input type="date" id="birthdate" name="birthdate" /></td>
                            </tr>
                            <tr>
                                <td><label for="email" class="formlabel">Email <span class="red-text">*</span></label></td>
                                <td><input type="email" id="email" name="email" /></td>
                            </tr>
                            <tr>
                                <td><label for="password" class="formlabel">Password <span class="red-text">*</span></label></td>
                                <td><input type="password" id="password" name="password" /></td>
                            </tr>
                            <tr>
                                <td><label for="mailingaddress" class="formlabel">Mailing Address <span class="red-text">*</span></label></td>
                                <td><textarea id="mailingaddress" name="mailingaddress"></textarea></td>
                            </tr>
                            <tr>
                                <td><label for="mobilenum" class="formlabel">Mobile Number <span class="red-text">*</span></label></td>
                                <td><input type="text" name="mobilenum" id="mobilenum" /></td>
                            </tr>
                            <tr>
                                <td/>
                                <td><input type="submit" value="Sign up" class="bluebtn-allcaps"></td>
                            </tr>
                            <tr>
                                <td/>
                                <td>Have an account? <a href="signin">Sign in here.</a></td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
</html>
