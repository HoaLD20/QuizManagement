<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Entity.QuizHistory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userName = (String) session.getAttribute("user");
    String email = (String) session.getAttribute("email");
    int type = Integer.parseInt(session.getAttribute("userType").toString());
    String userType = (type == 1) ? "Teacher" : "Student";
    List<QuizHistory> history = null;
    if (type == 1) {
        history = (List<QuizHistory>) request.getAttribute("quizHistory");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <title>User Detail</title>
    </head>
    <body>
        <div class="container" style="padding-top: 60px">
            <div class="row">
                <div class="col">
                    <div class="list-group">
                        <button type="button" class="list-group-item list-group-item-action active">
                            User Information
                        </button>
                        <button type="button" class="list-group-item list-group-item-action">Username: <%=userName%></button>
                        <button type="button" class="list-group-item list-group-item-action">Email: <%=email%></button>
                        <button type="button" class="list-group-item list-group-item-action" disabled>Type: <%=userType%></button>
                    </div>
                </div>
                <div class="col">
                    <%
                        if ((type == 1) && (history != null)) {
                            if (history.size() != 0) {
                    %>
                    <table class="table table-bordered">
                        <div class="alert alert-success" role="alert">
                            Quiz History
                        </div>
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">Number</th>
                                <th scope="col">Student</th>
                                <th scope="col">Total</th>
                                <th scope="col">Correct</th>
                                <th scope="col">Mark</th>
                                <th scope="col">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%int i = 0;%>
                            <% for (QuizHistory q : history) { %>
                            <%
                                double mark = (double) q.getCorrectAnswer() / (double) q.getNumOfQuiz();
                                mark *= 10;
                                NumberFormat formatter = new DecimalFormat("#0.00");
                            %>
                            <tr>
                                <th scope="row"><%=i+=1%></th>
                                <td><%=q.getStudentName()%></td>
                                <td><%=q.getNumOfQuiz()%></td>
                                <td><%=q.getCorrectAnswer()%></td>
                                <td><%=formatter.format(mark)%></td>
                                <!--<span class="label label-success">Passed</span>-->
                                <td><%=(mark > 4) ? "Passed" : "Failed"%></td>
                            </tr>
                            <% } %>
                            <% } %>
                        </tbody>
                    </table>
                    <% }
                        else if(type == 0){ %>
                        <div class="alert alert-dark" role="alert">
                            Login with teacher role to see history!
                        </div>
                       <% }


                        else { %>
                    <div class="alert alert-dark" role="alert">
                        No quiz history found
                    </div>
                    <% }%>
                </div>
            </div>
        </div>
    </body>
</html>