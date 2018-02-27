<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>

</head>
<body>
    <c:forEach items="${customers}" var="item">
        <c:out value="${item.firstName}"/>
        <c:out value="${item.lastName}"/>
    </c:forEach>
</body>
</html>