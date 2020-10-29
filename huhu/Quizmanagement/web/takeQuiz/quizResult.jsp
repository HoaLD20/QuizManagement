<%-- 
    Document   : quizResult
    Created on : Oct 27, 2020, 3:44:23 PM
    Author     : stulab
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int numOfQuiz = Integer.parseInt(request.getAttribute("numOfQuiz").toString());
    String result = (String) request.getAttribute("result");
    String percent = (String) request.getAttribute("percent");
    String status = (String) request.getAttribute("status");
    String className = (String) request.getAttribute("className");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="resource/css/common.css">
        <title>JSP Page</title>
    </head>
    <body>

        <div class="container" style="padding-top: 60px">
            <div class="card">
                <div class="card-header">
                    Result
                </div>
                <div class="card-body">
                    <p>Your score: 
                        <span class="bold <%=className%>">
                            <%=result%> (<%=percent%>%) - <%=status%>
                        </span>
                    </p>
                    <form action="TakeQuiz" method="post">
                        <input type="hidden" name="req" value="quizReq">
                        <input id="resultText" type="hidden" name="numOfQuiz" value="<%=numOfQuiz%>">
                        Take another quiz 
                        <input type="submit" class="btn btn-primary" value="Start">
                    </form>
                </div>
            </div>
        </div>





    </body>
</html>