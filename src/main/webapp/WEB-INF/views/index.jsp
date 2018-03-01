<%--
  Created by IntelliJ IDEA.
  User: Troy Mirafuentes
  Date: 2/25/2018
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Troy's Toys</title>
    <c:url value="/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<!--Navbar-->
<%@ include file="navbar.jsp" %>

<div class="main">
    <!--Ad
    <div class="ad">
        <a href="#" class="control_next">&gt;</a>
        <a href="#" class="control_prev">&lt;</a>
        <ul>
            <li style="background: url('https://pcx.com.ph/wp-content/uploads/2017/03/believe-in-love-again-grid-slider.png');" />
            <li style="background: url('https://pcx.com.ph/wp-content/uploads/2016/10/anti-piracy-grid-slider.jpg');" />
            <li style="background: url('https://pcx.com.ph/wp-content/uploads/2017/09/1100-x-200-365.jpg');" />
        </ul>
    </div>-->

    <!--What's New-->

    <div id="outputSection">
        <table id="outputTable">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
            </tr>
            <c:forEach items="${products}" var="item"> <!--TODO: Need to fix variables-->
                <tr>
                <td><c:out value="${item.productName}"/></td>
                <td><c:out value="${item.productPrice}"/></td>
                <td/>
                <td>
                    <form id="formPrice">
                        <button type="submit" class="bluebtn-allcaps">Buy</button>
                        <button type="submit" class="bluebtn-allcaps">View</button>
                    </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <%@ include file="footer.jsp" %>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script>
    jQuery(document).ready(function ($) {
        $('#checkbox').change(function(){
            setInterval(function () {
                moveRight();
            }, 3000);
        });
        var slideCount = $('#ad ul li').length;
        var slideWidth = $('#ad ul li').width();
        var slideHeight = $('#ad ul li').height();
        var adUlWidth = slideCount * slideWidth;
        $('#ad').css({ width: slideWidth, height: slideHeight });
        $('#ad ul').css({ width: adUlWidth, marginLeft: - slideWidth });
        $('#ad ul li:last-child').prependTo('#ad ul');
        function moveLeft() {
            $('#ad ul').animate({
                left: + slideWidth
            }, 200, function () {
                $('#ad ul li:last-child').prependTo('#ad ul');
                $('#ad ul').css('left', '');
            });
        };
        function moveRight() {
            $('#ad ul').animate({
                left: - slideWidth
            }, 200, function () {
                $('#ad ul li:first-child').appendTo('#ad ul');
                $('#ad ul').css('left', '');
            });
        };
        $('a.control_prev').click(function () {
            moveLeft();
        });
        $('a.control_next').click(function () {
            moveRight();
        });
    });
</script>
</html>
