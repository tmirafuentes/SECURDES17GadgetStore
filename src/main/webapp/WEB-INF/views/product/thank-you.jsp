<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 3/1/18
  Time: 10:57 PM
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
    <script src="../uikit.min.js"></script>
    <script src="../uikit-icons.min.js"></script>
    <script src="../jquery-3.3.1.min.js"></script>
</head>
<body>
    <!--Navbar-->
    <%@ include file="../user/navbar.jsp" %>
        
    <!-- Product Details -->
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <h1 class="uk-text-center">Confirm Purchasing Details</h1>
        <div class="uk-text-center uk-container-center uk-width-1-2 uk-alert uk-alert-success">
            Purchase successful. Thank you for your purchase! Please click "Continue Shopping" to continue.
        </div>
            <div class="uk-grid uk-grid-small">
                <div class="uk-panel uk-panel-box uk-text-left uk-container-center uk-width-1-2">
                    <div class="uk-text-center">
                        <img id="productPicConfirmation" src="https://s7d2.scene7.com/is/image/SamsungUS/Pdpkeyfeature-sm-t350nzaaxar-600x600-C1-062016?$product-details-jpg$" width="400px">
                    </div>
                    <table class="uk-table">
                        <tr>
                            <td>Product Name</td>
                            <td>${product.productName}</td>
                        </tr>
                        <tr>
                            <td>Specifications</td>
                            <td>${product.productDescription}</td>
                        </tr>
                        <tr>
                            <td>Price</td>
                            <td>PHP ${product.productPrice}</td>
                        </tr>
                        <tr>
                            <td>Quantity</td>
                            <td>${quantity}</td>
                        </tr>
                        <tr>
                            <td>Total</td>
                            <td>${total}</td>
                        </tr>
                        <tr>
                            <td>Current Mailing Address</td>
                            <td>${user.mailAddress}</td>
                        </tr>
                    </table>
                    <div class="uk-text-right">
                        <a href="index" class="uk-button uk-button-primary">CONTINUE SHOPPING</a>
                    </div>
                </div>
            </div>
    </div>

    <!--Footer-->
    <%@ include file="../user/footer.jsp" %>
</body>
</html>

