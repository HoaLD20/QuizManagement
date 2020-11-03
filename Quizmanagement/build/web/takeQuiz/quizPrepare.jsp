<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userName = (String) session.getAttribute("user");
    String message = (String) request.getAttribute("message");
    int savedNumOfQuiz = 0;
    try{
    if (message != null) {
        savedNumOfQuiz = Integer.parseInt(request.getAttribute("savedNumOfQuiz").toString());
    }
    }catch(Exception e){
        System.out.println("empty input in quizprepare.jsp");
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
        <div class="container" style="padding-top: 60px">
            <div class="card">
                <div class="card-header">
                    Welcome <span class="userName"><%=userName%> !
                </div>
                <div class="card-body">
                    <form action="TakeQuiz" method="post">
                        <input type="hidden" name="req" value="quizReq">
                        <br>Enter number of question: 
                        <input type="number" value="<%=(message != null) ? savedNumOfQuiz : ""%>" name="numOfQuiz">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Start">
                        <br/>
                        <br/>
                        <% if (message != null) {%>
                        <div class="alert alert-danger" style="width: 350px">
                            <span class="failed"><%=message%></span>
                        </div>
                        <% }%>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>