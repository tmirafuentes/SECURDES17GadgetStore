<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Troy's Toys</title>
    <c:url value="/css/index.css" var="jstlCss" />
    <link rel="stylesheet" type="text/css" href="${jstlCss}">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
    <link href="https://github.com/theleagueof/league-spartan/blob/master/_webfonts/stylesheet.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
    <div id="overallSection">
        <div id="filteringSection">
            <div id="priceFilter">
                <form id="formPrice"> <!--TODO: Need to add action-->
                    <p id="priceLabel">Budget:</p>
                    <input type="number" id="scrollMin" min = "0" placeholder="min">
                    <input type="number" id="scrollMax" min = "1" placeholder="max">
                    <p id="brandLabel">Specific Brands:</p>
                    <div id="brandFilter">
                        <c:forEach items="${brands}" var="brand"> <!--TODO: Need to fix variables-->
                            <input type="checkbox" name="<c:out value="${brand.brandName}"/>" value="<c:out value="${brand.brandName}"/>"/>
                            <br>
                        </c:forEach>
                    </div>
                    <button type="submit" id="submitPrice">Submit</button>
                </form>
            </div>
        </div>
        <div id="outputSection">
            <table id="outputTable">
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${products}" var="item"> <!--TODO: Need to fix variables-->
                <tr>
                    <td><c:out value="${product.productName}"/></td>
                    <td><c:out value="${product.productPrice}"/></td>
                    <td>
                        <form id="formPrice"> <!--TODO: Need to add action-->
                            <button type="submit" class="bluebtn-allcaps">Buy</button>
                            <button type="submit" class="bluebtn-allcaps">View</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
