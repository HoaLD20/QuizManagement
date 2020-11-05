/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helper.DbConnectionHelp;
import Model.Entity.Question;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class QuestionModel {

    private Connection connection;
    private SimpleDateFormat formatter;

    public QuestionModel() {
        formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }

    public QuestionModel(Connection connection, SimpleDateFormat formatter) {
        this.connection = connection;
        this.formatter = formatter;
    }

    public List<Question> getQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DbConnectionHelp.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from question");
            while (resultSet.next()) {
                Question q = new Question(resultSet.getInt("id"), resultSet.getString("content"), resultSet.getString("answer"), formatter.parse(resultSet.getString("created")));
                q.addOption(resultSet.getString("op1"));
                q.addOption(resultSet.getString("op2"));
                q.addOption(resultSet.getString("op3"));
                q.addOption(resultSet.getString("op4"));
                questions.add(q);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if ((resultSet != null) && (!resultSet.isClosed())) {
                resultSet.close();
            }
            if ((statement != null) && (!statement.isClosed())) {
                statement.close();
            }
            DbConnectionHelp.closeConnection(connection);
        }
        return questions;
    }

//    public List<Question> getQuestionsInRange() throws Exception, ParseException, ClassNotFoundException {
//        List<Question> questions = new ArrayList<>();
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DbConnectionHelp.getConnection();
//            String query = "select * from question";
//            statement = connection.prepareStatement(query);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Question q = new Question(resultSet.getInt("id"), resultSet.getString("content"), resultSet.getString("answer"), formatter.parse(resultSet.getString("created")));
//                q.addOption(resultSet.getString("op1"));
//                q.addOption(resultSet.getString("op2"));
//                q.addOption(resultSet.getString("op3"));
//                q.addOption(resultSet.getString("op4"));
//                questions.add(q);
//            }
//        } catch (SQLException | ParseException ex) {
//            throw ex;
//        } finally {
//            if ((resultSet != null) && (!resultSet.isClosed())) {
//                resultSet.close();
//            }
//            if ((statement != null) && (!statement.isClosed())) {
//                statement.close();
//            }
//            DbConnectionHelp.closeConnection(connection);
//        }
//        return questions;
//    }

    public void addQueston(Question q) throws Exception {
        PreparedStatement statement = null;
        try {
            connection = DbConnectionHelp.getConnection();
            String query = "insert into question (content, op1, op2, op3, op4, answer) values(?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, q.getContent());
            statement.setString(2, q.getOption().get(0));
            statement.setString(3, q.getOption().get(1));
            statement.setString(4, q.getOption().get(2));
            statement.setString(5, q.getOption().get(3));
            statement.setString(6, q.getAnswer());
            statement.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if ((statement != null) && (!statement.isClosed())) {
                statement.close();
            }
            DbConnectionHelp.closeConnection(connection);
        }
    }

    public void deleteQueston(int id) throws Exception {
        PreparedStatement statement = null;
        try {
            connection = DbConnectionHelp.getConnection();
            String query = "delete from question where id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if ((statement != null) && (!statement.isClosed())) {
                statement.close();
            }
            DbConnectionHelp.closeConnection(connection);
        }
    }

    public Question findQuestionById(int id) throws Exception {
        Question returnValue = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnectionHelp.getConnection();
            statement = connection.prepareCall("SELECT * FROM question WHERE id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                returnValue = new Question(resultSet.getInt("id"), resultSet.getString("content"), resultSet.getString("answer"), formatter.parse(resultSet.getString("created")));
                returnValue.addOption(resultSet.getString("op1"));
                returnValue.addOption(resultSet.getString("op2"));
                returnValue.addOption(resultSet.getString("op3"));
                returnValue.addOption(resultSet.getString("op4"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if ((resultSet != null) && (!resultSet.isClosed())) {
                resultSet.close();
            }
            if ((statement != null) && (!statement.isClosed())) {
                statement.close();
            }
            DbConnectionHelp.closeConnection(connection);
        }
        return returnValue;
    }

    public int getQuestionSize() throws Exception {
        int returnValue = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnectionHelp.getConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) as \"size\" FROM question");
            resultSet = statement.executeQuery();
            resultSet.next();
            returnValue = resultSet.getInt("size");
           
        } catch (Exception ex) {
            throw ex;
        } finally {
            if ((resultSet != null) && (!resultSet.isClosed())) {
                resultSet.close();
            }
            if ((statement != null) && (!statement.isClosed())) {
                statement.close();
            }
            DbConnectionHelp.closeConnection(connection);
        }
        return returnValue;
    }
}
