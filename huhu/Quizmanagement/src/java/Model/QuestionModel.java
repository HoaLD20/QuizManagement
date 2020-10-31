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
 * @author stulab
 */
public class QuestionModel {

    private Connection connection;
    private SimpleDateFormat formatter;

    /**
     * set default day created
     */
    public QuestionModel() {
        formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * constructor
     *
     * @param connection
     * @param formatter
     */
    public QuestionModel(Connection connection, SimpleDateFormat formatter) {
        this.connection = connection;
        this.formatter = formatter;
    }

    /**
     * get all question
     *
     * @return
     * @throws Exception
     */
    public List<Question> getQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DbConnectionHelp.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from question");

            while (resultSet.next()) {
                /**
                 *
                 *
                 * getNString to getString
                 *
                 */
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

    /**
     * get question in range a to b
     *
     * @param from
     * @param to
     * @return
     * @throws Exception
     * @throws ParseException
     * @throws ClassNotFoundException
     */
    public List<Question> getQuestionsInRange(int from, int to) throws Exception, ParseException, ClassNotFoundException {
        List<Question> questions = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnectionHelp.getConnection();
//            String query = "Select * from (select ROW_NUMBER() Over (order by id) AS rowNum, * From question) AS ahihi where rowNum >= ? and rowNum < ?;";
            String query = "select * from question limit ?,? ";
            statement = connection.prepareStatement(query);
            statement.setInt(1, from - 1);
            statement.setInt(2, to);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                /**
                 *
                 *
                 * Nstring to getstring
                 *
                 *
                 */

                Question q = new Question(resultSet.getInt("id"), resultSet.getString("content"), resultSet.getString("answer"), formatter.parse(resultSet.getString("created")));
                q.addOption(resultSet.getString("op1"));
                q.addOption(resultSet.getString("op2"));
                q.addOption(resultSet.getString("op3"));
                q.addOption(resultSet.getString("op4"));
                questions.add(q);
            }
        } catch (SQLException | ParseException ex) {
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
    /**
     * add question
     * @param q
     * @throws Exception 
     */
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
    /**
     * delete question
     * @param id
     * @throws Exception 
     */
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

    /**
     * find question by ID
     *
     *
     * @param id
     * @return
     * @throws Exception
     */
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
    /**
     * get size of question
     * @return
     * @throws Exception 
     */
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
//
//    public static void main(String[] args) throws Exception {
//        QuestionModel q = new QuestionModel();
//        System.out.println(q.getQuestionSize());
//    }
}
