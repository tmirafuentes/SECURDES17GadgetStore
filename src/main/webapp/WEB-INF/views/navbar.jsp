<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 5:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!--
<head>
    <link rel="stylesheet" type="text/css" href="index.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <meta charset="UTF-8">
</head>-->
<body>
<!--Navbar-->
<header>
    <div class="navbartop">
<<<<<<< HEAD
        <p id="logo"> Troy's Toys</p>
        <form class="search" role="search" id="searchPart">
            <input type="text" placeholder="Search for a product..." id="searchBar">
        </form>
        <div id="signs">
            <a href="/signin" id="signin">SIGN IN</a>
            <a href="/signup" id="signuplink">SIGN UP</a>
        </div>
=======
        <table id="navTable">
            <tr>
                <td><p id="logo"> Troy's Toys</p></td>
                <td>
                    <form class="search" role="search" id="searchPart">
                        <input type="text" placeholder="Search for a product..." id="searchBar">
                    </form>
                </td>
                <td id="dropdownHolder">
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <!-- <td id = "dropdownHolder"> -->
                        <div class="dropdown">
                            <button class="dropbtn">${pageContext.request.userPrincipal.name}</button>
                            <div class="dropdown-content">
                                <a href="/purchases">Purchase History</a>
                                <a href="/accountSettings">Account Settings</a>
                                <a onclick="document.forms['logoutForm'].submit()">Logout</a>
                            </div>
                        </div>
                    <!--</td>-->
                    </c:when>
                    <c:otherwise>
                        <a href="/signin" id="signin">SIGN IN</a>
                        <a href="/signup" id="signuplink">SIGN UP</a>
                    </c:otherwise>
                </td>
            </tr>
        </table>
>>>>>>> b76f5769d3e8abb72aecf433aaa48c32ec3ca7dd
    </div>
    <nav>
        <ul>
            <li><a href="/desktops">DESKTOPS</a></li>
            <li><a href="/laptops">LAPTOPS</a></li>
            <li><a href="/tablets">TABLETS</a></li>
            <li><a href="/mobiles">MOBILES</a></li>
        </ul>
    </nav>
</header>
</body>
</html>
