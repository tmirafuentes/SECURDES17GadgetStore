<%--
  Created by IntelliJ IDEA.
  User: muonsei
  Date: 2/28/18
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="adminHStyle.css">
</head>
    <body>
    <p id="titleH">ADMIN FUNCTIONS</p>
    <button id="btnAdminAdd">Add Item</button>
    <div id="adminSection">

        <table id="productList">
            <th id="pID">Product ID</th> <th id="pName">Name</th><th id="pPrice">Price</th><th id="pQuan">Quantity</th><th id="pBrand">Brand</th><th id="pDesc">Description</th>
            <!––Adds a button sa dulo ng table for editing-->
        </table>
    </div>
    </body>
</html>
