/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helper.DbConnectionHelp;
import Model.Entity.QuizHistory;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class QuizHistoryModel {

    private Connection connection;
    public void addEntry(int id, int numOfQuiz, int correctAnswer) throws Exception {
        PreparedStatement statement = null;
        try {
            connection = DbConnectionHelp.getConnection();
            statement = connection.prepareStatement("Insert into quizHistory (studentId, numOfQuiz, correctAnswer) values (?,?,?)");
            statement.setInt(1, id);
            statement.setInt(2, numOfQuiz);
            statement.setInt(3, correctAnswer);
            statement.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            DbConnectionHelp.closeConnection(connection);
            if ((statement != null) && (!statement.isClosed())) {
                statement.close();
            }
        }
    }

    public List<QuizHistory> getAllEntry() throws Exception {
        List<QuizHistory> history = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnectionHelp.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select quizHistory.*, users.username from quizHistory inner join users on quizHistory.studentId = users.id;");
            while (resultSet.next()) {
                history.add(new QuizHistory(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getInt("numOfQuiz"), resultSet.getInt("correctAnswer")));
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            DbConnectionHelp.closeConnection(connection);
            if ((resultSet != null) && (!resultSet.isClosed())) {
                resultSet.close();
            }
            if ((statement != null) && (!statement.isClosed())) {
                statement.close();
            }
        }
        return history;
    }
}
