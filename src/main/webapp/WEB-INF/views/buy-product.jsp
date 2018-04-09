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
    <script src="uikit.min.js"></script>
    <script src="uikit-icons.min.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
    <!--Navbar-->
    <%@ include file="navbar.jsp" %>
        
    <!-- Product Details -->
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <h1 class="uk-text-center">Confirm Purchasing Details</h1>
        <form:form method="POST" action="${contextPath}/buyProduct">
            <div class="uk-grid uk-grid-small">
                <div class="uk-panel uk-panel-box uk-text-left uk-container-center uk-width-1-2">
                    <div class="uk-text-center">
                        <img id="productPicConfirmation" src="https://s7d2.scene7.com/is/image/SamsungUS/Pdpkeyfeature-sm-t350nzaaxar-600x600-C1-062016?$product-details-jpg$" width="400px">
                    </div>
                    <table class="uk-table">
                        <tr>
                            <td>Product Name</td>
                            <td>${indiProd.productName}</td>
                        </tr>
                        <tr>
                            <td>Price</td>
                            <td>PHP ${indiProd.productPrice}</td>
                        </tr>
                        <tr>
                            <td>Specifications</td>
                            <td>${indiProd.productDescription}</td>
                        </tr>
                        <tr>
                            <td>Quantity</td>
                            <td>${prodQty}</td>
                        </tr>
                        <tr>
                            <td>Current Mailing Address</td>
                            <td>${currCust.mailAddress}</td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <br />
            <div class="uk-panel uk-panel-box uk-container-center uk-width-1-2">
                <form class="uk-form uk-form-stacked">
                    <div class="uk-form-row">
                        <label class="uk-form-label" for="password">Enter your password to continue purchase</label>
                        <div class="uk-form-controls">
                            <input type="password" name="password" id="password" class="uk-width-1-1" />
                        </div>
                    </div>
                    <div class="uk-form-row uk-text-right">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="text" value="${indiProd.productId}" hidden="true" name="prodId">
                        <input type="text" value="${currCust.username}" hidden="true" name="custName">
                        <input type="number" id="scrollQuantity" hidden value="${prodQty}" name="prodQty"><button type="submit" class="uk-button uk-button-primary">CONFIRM</button>
                    </div>
                </form>
            </div>
        </form:form>
    </div>

    <!--Footer-->
    <%@ include file="footer.jsp" %>
</body>
</html>
