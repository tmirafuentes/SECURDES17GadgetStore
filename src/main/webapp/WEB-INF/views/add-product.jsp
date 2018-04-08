<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 3:30 PM
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
        <title>Admin | Troy's Toys</title>
        <script src="uikit.min.js"></script>
        <script src="uikit-icons.min.js"></script>
        <script src="jquery-3.3.1.min.js"></script>
    </head>
    <body>
        <!--Navbar-->
        <%@ include file="navbar.jsp" %>
        
        <!--Body-->
        <div class="uk-panel uk-panel-box uk-panel-space">
            <h1 class="uk-text-center">Add New Product</h1>
            <div class="uk-grid uk-grid-small">
                <div class="uk-panel uk-panel-box uk-text-center uk-container-center uk-width-1-3">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <form:form method="POST" modelAttribute="prodForm" class="uk-form uk-form-stacked">
                        <!-------------Photo-------------->
                        <!--<div class="uk-form-row">
                                <label for="image" class="uk-form-label">Photo</label>
                                <div class="uk-form-controls">
                                    <input type="file" accept="image/*" name="image" id="image">
                                </div>
                            </div> -->
                        <!-------------Brand-------------->
                        <spring:bind path="productBrand">
                            <div class="uk-form-row">
                                <form:label path="productBrand" for="brand" class="uk-form-label">Brand <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:select path="productBrand" id="pBrand" name="brand" class="uk-form-width-large">
                                        <c:forEach var="item" items="${prodBrands}">
                                            <form:option value="${item}">${item}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                        </spring:bind>
                        <!-------------Type-------------->
                        <spring:bind path="productType">
                            <div class="uk-form-row">
                                <form:label path="productType" for="productType" class="uk-form-label">Product Type <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:select path="productType" id="pType" name="productType" class="uk-form-width-large">
                                        <c:forEach var="item" items="${prodTypes}">
                                            <form:option value="${item}">${item}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                        </spring:bind>
                        <!-------------Name-------------->
                        <spring:bind path="productName">
                            <div class="uk-form-row">
                                <form:label path="productName" class="uk-form-label" for="productName">Product Name <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="text" path="productName" id="pName" name="productName" class="uk-width-1-1"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!-------------Price-------------->
                        <spring:bind path="productPrice">
                            <div class="uk-form-row">
                                <form:label path="productPrice" class="uk-form-label" for="productPrice">Product Price <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="text" path="productPrice" id="pPrice" name="productPrice" class="uk-width-1-1"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!-------------Quantity-------------->
                        <spring:bind path="productQuantity">
                            <div class="uk-form-row">
                                <form:label path="productQuantity" class="uk-form-label" for="productQuantity">Product Quantity <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:input type="number" class="uk-width-1-1" path="productQuantity" id="pQuantity" name="productQuantity"></form:input>
                                </div>
                            </div>
                        </spring:bind>
                        <!-----------Description------------->
                        <spring:bind path="productDescription">
                            <div class="uk-form-row">
                                <form:label path="productDescription" class="uk-form-label" for="productDescription">Product Description <span class="uk-text-danger">*</span></form:label>
                                <div class="uk-form-controls">
                                    <form:textarea path="productDescription" id="pDesc" class="uk-width-1-1" name="productDescription"></form:textarea>
                                </div>
                            </div>
                        </spring:bind>
                        <!-------------Buttons--------------->
                        <div class="uk-form-row">
                            <input type="submit" value="Save" class="uk-button uk-button-primary">
                        </div>
                    </form:form>
                </div>
            </div>        
        </div>
        
        <!--Navbar-->
        <%@ include file="footer.jsp" %>
            
    </body>
</html>
