<%-- 
    Document   : quizPrepare
    Created on : Oct 20, 2020, 3:44:04 PM
    Author     : stulab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userName = (String) session.getAttribute("user");
    String message = (String) request.getAttribute("message");
    int savedNumOfQuiz = 0;
    if (message != null) {
        savedNumOfQuiz = Integer.parseInt(request.getAttribute("savedNumOfQuiz").toString());
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <title>Online Quiz</title>
    </head>
    <body>
        <h3>Welcome <span class="userName"><%=userName%></span></h3>
        <% if (message != null) {%>
        <span class="failed"><%=message%></span>
        <% }%>
        <form action="TakeQuiz" method="post">
            <input type="hidden" name="req" value="quizReq">
            <br>Enter number of question: <br>
            <input type="number" value="<%=(message != null) ? savedNumOfQuiz : "" %>" name="numOfQuiz">
            <input type="submit" value="Start">
        </form>
    </body>
</html>