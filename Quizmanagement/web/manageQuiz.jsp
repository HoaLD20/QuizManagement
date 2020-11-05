<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Entity.Question"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Question> questions = (List<Question>) request.getAttribute("quizData");
    int numOfQuiz = Integer.parseInt(request.getAttribute("numOfQuiz").toString());
    int numOfPage = Integer.parseInt(request.getAttribute("numOfPage").toString());
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <script>

            function delQuestion( id) {
                result = confirm("Are you sure to delete this question ?");
                if (result)
                    window.location.replace("ManageQuiz?del=" + id);
            }
        </script>
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resource/js/manageQuiz.js"></script>
        <title>Manage Quiz</title>
    </head>
    <body>
        <div class="container" style="padding-top: 60px">
            <% if ((questions != null) && (!questions.isEmpty())) {%>
            <div class="alert alert-success" role="alert" style="width: 220px">
                Number of question: <%=numOfQuiz%>
            </div>

            <table class="table table-bordered">         
                <thead class="thead-light">
                    <tr>
                        <th style="width: 5%" scope="col">Number</th>
                        <th style="width: 30%" scope="col">Question</th>
                        <th style="width: 10%" scope="col">Date Created</th>
                        <th style="width: 5%" scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%int a = 0;%>
                    <% for (Question q : questions) {%>
                    <tr>
                        <th scope="row"><%=a += 1%></th>
                        <td><%=q.getContent()%></td>
                        <td><%=formatter.format(q.getCreated())%></td>
                        <td><a class="btn btn-primary btn-customized" href="#" onclick="delQuestion('<%=q.getId()%>')" role="button" aria-pressed="true">Delete</a></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else { %>
            <h5>No question available</h5>
            <% }%>
        </div>
    </body>
</html>