<%-- 
    Document   : index
    Created on : Oct 6, 2020, 1:04:06 PM
    Author     : stulab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String includePage = (String) request.getAttribute("page");
    String user = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Quiz</title>
    </head>
    <body>


        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Online Quiz</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="Index">Home <span class="sr-only"></span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="TakeQuiz">Take Quiz</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="MakeQuiz">Make Quiz</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ManageQuiz">Manage Quiz</a>
                    </li>
                    <%
                        if (user != null) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="Logout">Logout</a>
                    </li>
                    <%}%>
                </ul>
            </div>
        </nav>
        <div id="main-panel">
            <div id="main-content">
                <jsp:include page="<%= includePage%>" />
            </div>
        </div>
    </body>
</html>