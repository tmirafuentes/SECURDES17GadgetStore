<html>
    <head>
        <title>Bad Sign Up</title>
        <!-- <link rel="stylesheet" type="text/css" href="style/additionalStyle.css"> -->
        <c:url value="/resources/static/css/index.css" var="jstlCss" />
        <link rel="stylesheet" type="text/css" href="${jstlCss}">
    </head>
    <body>
    	<div class="holdingDiv">
    		<p class = "openingText"> Uhhhhh Something Went Wrong </p>
        	<hr class = "breakLine">
	         <h1 class = "secondaryText" th:text="${param.message[0]}">Error Message</h1>
			    <a class = "" h:href="@{/registration.html}" th:text="#{label.form.loginSignUp}">click here to signup again</a>
		</div>
    </head>
    <body>
    </body>
</html>