/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Helper.DbConnectionHelp;
import Model.Entity.User;
import com.mysql.jdbc.Connection;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stulab
 */
public class UserModel {

    private Connection connection;

    public UserModel() {
    }

    public User login(String userName, String password) throws Exception {
        User returnValue = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnectionHelp.getConnection();
            statement = connection.prepareStatement("select * from users where username=? and password=?");
            statement.setString(1, userName);
            statement.setString(2, getMD5Hash(password));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                returnValue = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("type"));
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
        return returnValue;
    }

    public int validateNewUser(User user) throws Exception {
        int returnValue = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnectionHelp.getConnection();
            statement = connection.prepareCall("select * from users where username=? or email=?");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(user.getUserName())) {
                    returnValue = 1;
                    break;
                }
                if (resultSet.getString("email").equals(user.getEmail())) {
                    returnValue = 2;
                    break;
                }
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
        return returnValue;
    }

    public static String getMD5Hash(String s) throws NoSuchAlgorithmException {

        String result = s;
        if (s != null) {
            MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
            md.update(s.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32) { // 40 for SHA-1
                result = "0" + result;
            }
        }
        return result;
    }

    public void addUser(User newUser) throws Exception {
        PreparedStatement ps = null;
        try {
            connection = DbConnectionHelp.getConnection();
            String query = "Insert into users (username,password,email,type) values(?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, newUser.getUserName());
            ps.setString(2, getMD5Hash(newUser.getPassword()));
            ps.setString(3, newUser.getEmail());
            ps.setInt(4, newUser.getType());
            ps.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            DbConnectionHelp.closeConnection(connection);
            if ((ps != null) && (!ps.isClosed())) {
                ps.close();
            }
        }
    }
}
