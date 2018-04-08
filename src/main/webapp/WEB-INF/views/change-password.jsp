<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        
        <!--Change password-->
        <div class="uk-panel uk-panel-box-secondary uk-panel-space">
            <h1>Sign In</h1>
            <div class="uk-grid uk-grid-small">
                <div class="uk-panel uk-panel-box uk-text-center uk-container-center uk-width-1-3">
                    <form method="POST" action="${contextPath}/signin" class="uk-form uk-form-stacked">
                        
                        <!--Current Password-->
                        <div class="uk-form-row">
                            <label class="uk-form-label" for="newpassword" >Current Password</label>
                            <div class="uk-form-controls">
                                <input type="password" name="currpassword" id="currpassword" autofocus="true" class="uk-width-1-1"/>
                            </div>
                        </div>
                        
                        <!--New Password-->
                        <div class="uk-form-row">
                            <label class="uk-form-label" for="password">New Password</label>
                            <div class="uk-form-controls">
                                <input type="password" name="newpassword" id="newpassword" class="uk-width-1-1"/>
                            </div>
                        </div>
                        
                        <!--Confirm New Password-->
                        <div class="uk-form-row">
                            <label class="uk-form-label" for="confirmpassword">Confirm New Password</label>
                            <div class="uk-form-controls">
                                <input type="password" name="confirmpassword" id="confirmpassword" class="uk-width-1-1"/>
                            </div>
                        </div>
                        
                        <!--Save-->
                        <div class="uk-form-row">
                            <button type="submit" class="uk-button uk-button-primary">Save</button>
                        </div>
                        
                    </form>
                </div>
            </div>
        </div>
        
        <!--Footer-->
        <jsp:include page="footer.jsp" />

    </body>
</html>
