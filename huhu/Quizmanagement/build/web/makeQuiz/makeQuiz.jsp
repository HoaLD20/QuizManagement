<%-- 
    Document   : makeQuiz
    Created on : Oct 13, 2020, 3:22:54 PM
    Author     : stulab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = (String) request.getAttribute("message");
    String content = "";
    String opt1 = "";
    String opt2 = "";
    String opt3 = "";
    String opt4 = "";
    String[] answers = new String[4];
    if (message != null) {
        content = (String) request.getAttribute("content");
        opt1 = (String) request.getAttribute("opt1");
        opt2 = (String) request.getAttribute("opt2");
        opt3 = (String) request.getAttribute("opt3");
        opt4 = (String) request.getAttribute("opt4");
        String answer = (String) request.getAttribute("answer");
        for (int i = 0; i < answer.length(); i++) {
            char x = answer.charAt(i);
            int index = (int) x - 49;
            answers[index] = "checked";
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Quiz</title>
    </head>
    <body>

        <form action="MakeQuiz" method="post">
            <div class="container" style="padding-top: 60px">
                <div class="row">

                    <div class="col">                    
                        <div class="form-group">
                            <div class="p-3 mb-2 bg-success text-white" style="border-radius: 10px; width: 100px">Question:</div>                         
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="5" name="content" style="width: 450px"><%=content%></textarea>
                        </div>
                        <div class="form-group">
                            <div class="p-3 mb-2 bg-success text-white" style="border-radius: 10px; width: 100px">Answer(s):</div> <br/>
                            <input type="checkbox" name="ans1" <%=answers[0]%>> Option 1
                            <input type="checkbox" name="ans2" <%=answers[1]%>> Option 2
                            <input type="checkbox" name="ans3" <%=answers[2]%>> Option 3
                            <input type="checkbox" name="ans4" <%=answers[3]%>> Option 4
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-customized" value="Save">
                        </div>
                        <% if (message != null) {%>
                        <div class="alert alert-danger" style="width: 400px">
                            <span class="failed"><%=message%></span>
                        </div>
                        <% }%>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label>Option 1:</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="2" name="opt1" style="width: 450px"><%=opt1%></textarea>
                        </div>
                        <div class="form-group">
                            <label>Option 2:</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="2" name="opt2" style="width: 450px"><%=opt2%></textarea>
                        </div>
                        <div class="form-group">
                            <label>Option 3:</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="2" name="opt3" style="width: 450px"><%=opt3%></textarea>
                        </div>
                        <div class="form-group">
                            <label>Option 4:</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="2" name="opt4" style="width: 450px"><%=opt4%></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>