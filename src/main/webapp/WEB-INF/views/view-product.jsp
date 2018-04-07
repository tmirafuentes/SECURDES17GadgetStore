<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 6:28 PM
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
    <title>Troy's Toys</title>
    <script src="uikit.min.js"></script>
    <script src="uikit-icons.min.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
    <!--Navbar-->
    <%@ include file="navbar.jsp" %>
    
    <!--Product Details-->
    <div class="content">
        <div class="product-description">
            <table class="product-details">
                <form:form method="POST" action="${contextPath}/confirmation">
                    <tr>
                        <td><img src="https://s7d2.scene7.com/is/image/SamsungUS/Pdpkeyfeature-sm-t350nzaaxar-600x600-C1-062016?$product-details-jpg$" width="400px"/></td>
                        <td>
                            <h2>${indiProd.productName}</h2>
                            <h3>${indiProd.productPrice}</h3>
                            <p>${indiProd.productDescription}</p>
                            <input type="number" value="${indiProd.productId}" hidden="true" name="prodId">
                            <p>Quantity: <input type="number" id="scrollQuantity" value=1 name="prodQty"></p>
                            <button type='submit' class="uk-button uk-button-primary uk-button-large">BUY NOW</button>
                        </td>    
                    </tr>
                </form:form>
            </table>
        </div>
    </div>
    
    <!--Footer-->    
    <%@ include file="footer.jsp" %>
</body>
</html>