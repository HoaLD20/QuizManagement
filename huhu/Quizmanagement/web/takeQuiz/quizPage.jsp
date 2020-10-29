<%-- 
    Document   : quizPage
    Created on : Oct 20, 2020, 3:42:30 PM
    Author     : stulab
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Entity.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userName = (String) session.getAttribute("user");
    List<Question> questions = (List<Question>) request.getAttribute("quizData");
%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            this.timeRemaining = 60;
            this.timeDisplay = document.getElementById("timeDisplay");
            this.questionPos = document.getElementById("qustionPos");
            this.currentQuiz = 0;
            this.currentDiv = null;
            this.testing = 0;
            this.numOfQuiz = 0;

            var nextQuestion = function () {
                currentQuiz = (currentQuiz + 1) % numOfQuiz;
                if (this.currentDiv !== null)
                    this.currentDiv.classList.add("hidden");
                currentDiv = document.getElementById("q" + currentQuiz);
                currentDiv.classList.remove("hidden");
                questionPos.textContent = "Question: " + (currentQuiz + 1) + "/" + numOfQuiz;
            }

            var quizStart = function () {
                testing = 1;
                nextQuestion();
                setInterval(function () {
                    timeRemaining--;
                    updateTime();
                    if (timeRemaining < 0) {
                        testing = 0;
                        document.getElementById("quizForm").submit();
                    }
                }, 1000);
            }

            var updateTime = function () {
                var time = Math.floor(timeRemaining / 60);
                var sec = timeRemaining % 60;
                timeDisplay.textContent = time + ":" + sec;
            }

            window.onbeforeunload = function () {
                if (testing !== 0)
                    return "Are you sure?";
            }

            var setNumOfQuiz = function (n) {
                numOfQuiz = n;
                this.timeRemaining = 60;
                this.timeDisplay = document.getElementById("timeDisplay");
                this.questionPos = document.getElementById("qustionPos");
                this.currentQuiz = 0;
                this.currentDiv = null;
                this.testing = 0;
                this.timeRemaining = 60;
            }
        </script>
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resource/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Quiz</title>
    </head>
    <body>
        <div id="quizContainer">
            <% if (!questions.isEmpty()) {%>
            <h3>Welcome <span class="userName"><%=userName%></span></h3>
            <h4 id="TimeSpan">Time remaining: <span id="timeDisplay">00:00</span></h4>
            <h5 id="qustionPos">Question: 0/0</h5>
            <form id="quizForm" action="TakeQuiz" method="post">
                <input type="hidden" name="req" value="quizResult">
                <input type="hidden" name="numOfQuiz" value="<%=questions.size()%>">
                <%
                    for (int i = 0; i < questions.size(); i++) {
                        Question q = questions.get(i);
                %>
                <div id="q<%=i%>" class="hidden">
                    <input type="hidden" name="q<%=i%>" value="<%=q.getId()%>"
                           <p><%=q.getContent()%></p>
                    <ul>
                        <%
                            for (int j = 0; j < q.getOption().size(); j++) {
                                String ans = q.getOption().get(j);
                        %>
                        <input type="checkbox" name="ans<%=i%>-<%=j%>">
                        <li><%=ans%></li><br>
                            <% } %>
                    </ul>
                </div>
                <% } %>
                <input type="submit" value="Finish">    
                <button id="next" type="button" onclick="nextQuestion()">Next</button>
            </form>
            <% } else { %>
            <h3>No quiz found</h3>
            <% }%>
        </div>
        <script>
            setNumOfQuiz(<%=questions.size()%>);
            quizStart();
        </script>
    </body>
</html>