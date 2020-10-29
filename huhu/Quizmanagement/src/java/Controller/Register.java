/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entity.User;
import Model.UserModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stulab
 */
public class Register extends BaseServlet {

    @Override
    public String getFileName() {
        return "registerForm.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("page", getFileName());
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = "registerForm.jsp";
        String message = "", messageClass = "failed";
        UserModel um = null;
        try {
            um = new UserModel();

            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            String type = req.getParameter("type");
            String email = req.getParameter("email");

            req.setAttribute("savedUserName", userName);
            req.setAttribute("savedEmail", email);
            req.setAttribute("savedType", Integer.parseInt(type));
            User newUser = new User(-1, userName, password, email, Integer.parseInt(type));

            int errorCode = um.validateNewUser(newUser);
            if (userName.equals("") || password.equals("") || email.equals("")) {
                message = "Can not register with any empty field!";

            } else if (errorCode == 1) {
                message = "Username is already exist";
            } else if (errorCode == 2) {
                message = "Email is already exist";
            } else if (newUser.getPassword().length() < 8) {
                message = "Password must contain at least 8 character";
            } 
            else if(strongPass(newUser.getPassword()).equals("Password must be strong")){
                message = "Password must be strong (should include alphabets - upper or lower case, numbers and special characters)";
                
            }
            else {
                um.addUser(newUser);
                message = "User registered successfully";
                messageClass = "success";
            }
            System.out.println(message);
            req.setAttribute("page", page);
            req.setAttribute("message", message);
            req.setAttribute("messageClass", messageClass);
            getServletContext().getRequestDispatcher("/index.jsp")
                    .forward(req, resp);

        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            getServletContext().getRequestDispatcher("/errorPage/errorPage.jsp")
                    .forward(req, resp);
        }
    }

    public String strongPass(String password) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (int i = 0; i < password.length(); i++) {
            char x = password.charAt(i);
            if (Character.isLetter(x)) {
                hasLetter = true;
            } else if (Character.isDigit(x)) {
                hasDigit = true;
            }
            // no need to check further, break the loop
            if (hasLetter && hasDigit) {
                break;
            }
        }
        if (hasLetter && hasDigit) {
            return password;
        } else {
            return "Password must be strong";
        }
    }
}
