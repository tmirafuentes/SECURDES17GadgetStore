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
        <c:url value="/resources/static/css/index.css" var="jstlCss" />
        <link rel="stylesheet" type="text/css" href="${jstlCss}">
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
        <link href="https://github.com/theleagueof/league-spartan/blob/master/_webfonts/stylesheet.css" rel="stylesheet">
        <meta charset="UTF-8">

        <title>Add Product</title>
    </head>
    <body>
        <div class="heading">
            <h1>Add New Product</h1>
        </div>
        <div class="container">
            <div id="login">
                <form:form method="POST" modelAttribute="prodForm" class="form-addProd">
                    <div class="div-form">
                        <table>
                            <tr>
                                <td><label for="image" class="formlabel">Photo</label></td>
                                <td><input type="file" accept="image/*" name="image" id="image"></td>
                            </tr>
                            <tr>
                                <td><label for="pBrand" class="formlabel">Brand <span class="red-text">*</span></label></td>
                                <td>
                                    <select name="pBrand" id="pBrand">
                                        <option>Asus</option>
                                        <option>Acer</option>
                                        <!--Fetch data here-->
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <spring:bind path="productType">
                                    <td><form:label for="productType" class="formlabel">Type <span class="red-text">*</span></label></td>
                                    <td>
                                        <form:select path="productType" id="pType">
                                            <option>Desktop</option>
                                            <option>Laptop</option>
                                            <option>Tablet</option>
                                            <option>Mobile</option>
                                            <!--Fetch data here-->
                                        </form:select>
                                    </td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <spring:bind path="productName">
                                    <td><form:label for="productName" class="formlabel">Product Name 
                                        <span class="red-text">*</span></form:label></td>
                                    <td><form:input type="text" path="productName" id="pName"></form:input></td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <spring:bind path="productPrice">
                                    <td><form:label for="productPrice" class="formlabel">Price 
                                        <span class="red-text">*</span></form:label></td>
                                    <td><form:input type="text" path="productPrice" id="pPrice"></form:input></td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <spring:bind path="productQuantity">
                                    <td><form:label for="productQuantity" class="formlabel">Quantity 
                                        <span class="red-text">*</span></form:label></td>
                                    <td><form:input type="number" path="productQuantity" id="pQuantity"></form:input></td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <spring:bind path="productDescription">
                                    <td><form:label for="productDescription" class="formlabel">Description/Specifcations 
                                        <span class="red-text">*</span></form:label></td>
                                    <td><form:textarea path="productDescription" id="pDesc"></form:textarea></td>
                                </spring:bind>
                            </tr>
                            <tr>
                                <td/>
                                <td><input type="submit" value="Save" class="bluebtn-allcaps" id="btn-admin-save"></td>
                            </tr>
                        </table>
                    </div>
                </form:form>
            </div>
        </div>
    </body>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
</html>
