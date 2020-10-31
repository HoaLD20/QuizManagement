<%-- 
    Document   : registerForm
    Created on : Oct 13, 2020, 2:51:18 PM
    Author     : stulab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String messageClass = (String) request.getAttribute("messageClass");
    String message = (String) request.getAttribute("message");
    String userName = "";
    int type = 0;
    String email = "";
    if (message != null) {
        userName = (String) request.getAttribute("savedUserName");
        email = (String) request.getAttribute("savedEmail");
        type = Integer.parseInt(request.getAttribute("savedType").toString());
    }
%>
<!DOCTYPE html>
<html>
    <head>

       

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <title>Register</title>
    </head>
    <body>
        <form action="#" method="post" class="RFjuSb bxPAYd k6Zj8d">
            <div class="container-fluid bg-light py-3">
                <div class="row">
                    <div class="col-md-6 mx-auto">
                        <div class="card card-body" style="padding: 100px">
                            <h3 class="text-center mb-4">Sign-up</h3>
                            <fieldset>
                                <div class="form-group has-success">
                                    <input class="form-control input-lg" placeholder="Username" value="<%=userName%>" name="username" type="text">
                                </div>
                                <div class="form-group has-success">
                                    <input class="form-control input-lg" placeholder="Password" name="password" value="" type="password" >
                                </div>
                                <div class="form-group has-success">
                                    <input class="form-control input-lg" placeholder="Confirm Password" name="repassword" value="" type="password">
                                    <span id='message'></span>
                                </div>
                                <div class="form-group">
                                    <select class="form-control input-lg" name="type" value="<%=type%>">                           
                                        <option value="0" <%=(type == 0) ? "selected" : ""%>>Student</option>
                                        <option value="1" <%=(type == 1) ? "selected" : ""%>>Teacher</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input class="form-control input-lg" placeholder="Email" name="email" type="email" value="<%=email%>" >
                                </div>
                                <input class="btn btn-lg btn-primary btn-block" value="Sign Me Up" type="submit" value="Register">
                                <br>
                                <% if ((message != null) && (!message.equals(""))) {%>
                                <div class="alert alert-danger">
                                    <span class="<%=messageClass%>"><%=message%></span>
                                </div>
                                <% }%>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>