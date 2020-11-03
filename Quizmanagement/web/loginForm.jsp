<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = (String) request.getAttribute("m");
    String userName = "";
    if (message != null) {
        userName = (String) request.getAttribute("savedUserName");
    }
%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <title>Login</title>
    </head>
    <body >
        <form action="Login" method="post" style="padding-top: 130px; padding-left: 180px">
            <input type="hidden" name="request" value="register">
            <div class="container h-100">
                <div class="col-10 col-md-8 col-lg-6">
                    <h1>Login to Online Quiz</h1>
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input value="<%=userName%>" name="username" type="text" class="form-control username" id="username" placeholder="Username"style="width: 350px">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input name="password" type="password" class="form-control password" id="password" placeholder="Password" name="password" style="width: 350px">
                    </div>
                    <button type="submit" class="btn btn-primary btn-customized">Login</button>
                    <a class="btn btn-primary btn-customized" href="Register" style="margin-left: 20px" role="button" aria-pressed="true">Register</a>
                    <br>
                    <br>
                    <% if ((message != null) && (message.equals("fail"))) { %>
                    <div class="alert alert-danger" style="width: 400px">
                        <span class="failed">Username or password is incorrect, please try again!</span>
                    </div>
                    <% }%> 
                </div>
            </div>
        </form>
    </body>
</html>
