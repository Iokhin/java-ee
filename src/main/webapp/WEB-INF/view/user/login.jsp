<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>LOGIN</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

</head>
<br>
<br>
<br>
<body class="text-center">
<form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
    <div>
        <img class="mb-4" src="${pageContext.request.contextPath}/resources/pictures/tm-symbol.jpg" alt="" width="72"
             height="72">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="inputLogin" class="sr-only">Login</label>
        <input name="login" type="text" id="inputLogin" class="form-control" placeholder="Login" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <button class="btn btn-md btn-outline-info btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; Ай-теко</p>
    </div>
</form>
</body>
</html>