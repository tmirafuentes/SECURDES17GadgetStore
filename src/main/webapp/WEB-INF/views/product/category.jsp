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
        
    <div class="uk-panel uk-panel-box-secondary uk-panel-space">
        <div class="uk-grid uk-grid-small">
            <!--Sidebar-->
            <div class="uk-width-1-5">
                <div class="uk-panel uk-panel-box uk-panel-box-primary">
                    <h3>Filters</h3>
                    <form id="formPrice" class="uk-form uk-form-stacked"  action="${contextPath}/filter"> <!--TODO: Need to add action-->
                        <div class="uk-form-row">
                            <label for="scrollMin" class="uk-form-label" id="priceLabel">Budget</label>
                            <div class="uk-form-controls">
                                <input type="number" id="scrollMin" name="scrollMin" min = "0" placeholder="min">
                                <input type="number" id="scrollMax" name= "scrollMax"min = "1" placeholder="max">
                            </div>
                        </div>
                        <div class="uk-form-row">
                            <label id="brandlabel" class="uk-form-label">Brand</label>
                            <div class="uk-form-controls">
                                <c:forEach items="${brands}" var="brand"> <!--TODO: Need to fix variables-->
                                    <input type="checkbox" name="<c:out value="${brand.brandName}"/>" value="<c:out value="${brand.brandName}"/>"/>
                                    <br>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="uk-form-row uk-text-right">
                            <button type="submit" id="submitPrice" class="uk-button uk-button-primary">FILTER</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <!--Main-->
            <div class="uk-panel uk-width-4-5">
                <div class="uk-panel uk-panel-box uk-panel-box-secondary">
                    <form class="uk-form">
                        <select>
                            <option>Lowest to Highest Price</option>
                            <option>Highest to Lowest Price</option>
                            <option>Oldest to Latest</option>
                            <option>Latest to Oldest</option>
                        </select>

                        <button class="uk-button">SORT</button> <!--TODO: Add sorting action-->
                    </form>
                    <br />
                    <ul class="uk-grid uk-grid-small uk-grid-width-small-1-2 uk-grid-width-medium-1-3 uk-grid-width-large-1-4">
                        <c:forEach items="${allProducts}" var="item">
                            <li><div class="uk-panel uk-panel-box uk-text-center">
                                <img src="https://s7d2.scene7.com/is/image/SamsungUS/Pdpkeyfeature-sm-t350nzaaxar-600x600-C1-062016?$product-details-jpg$" />
                                <h4><c:out value="${item.productName}"/></h4>
                                <h3><c:out value="${item.productPrice}"/></h3>
                                <form id="formUser"> <!--TODO: Need to add action-->
                                    <input type="text" value="${pageContext.request.userPrincipal.name}" hidden="true" name="customerName">
                                    <a href="/view-product?v=${item.linkId}" class="uk-button uk-button-primary" >VIEW DETAILS</a>
                                </form>
                            </div></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
                    
    <!--Footer-->
    <%@ include file="../user/footer.jsp" %>
</body>
</html>
