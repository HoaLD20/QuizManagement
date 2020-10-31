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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            String repassword = req.getParameter("repassword");
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
            } 
            
            else if(!password.equals(repassword)){
                message = "Password not match !";
            }
            
            else if (newUser.getPassword().length() < 8) {
                message = "Password must contain at least 8 character";
            } else if (strongPass(newUser.getPassword()).equals("reject")) {
                message = "Password must be strong (should include alphabets - upper or lower case, numbers and special characters) !";
            }
            
            
            else if (checkMail(newUser.getEmail()).equals("length")) {
                message = "Email must be right format !";
            } else if (checkMail(newUser.getEmail()).equals("lenghtusername")) {
                message = "Email user name must be 6 to 30 character !";
            } else if (checkMail(newUser.getEmail()).equals("@gmail.com")) {
                message = "Email must be right format !";
            } else if (checkMail(newUser.getEmail()).equals("wrongusername")) {
                message = "Email user name can not contain '..' or '.' on the first or the last !";
            } else if (checkMail(newUser.getEmail()).equals("wrongusernamechar")) {
                message = "Email user name can not contain $, =, _, !, ', -, +, ',', <, >";
            } else if (checkMail(newUser.getEmail()).equals("reject")) {
                message = "Email must be right format !";
            } 
//             if (username.contains("&") || username.contains("=") || username.contains("_") || username.contains("!")
//                                || username.contains("'") || username.contains("-") || username.contains("+")
//                                || username.contains(",") || username.contains("<") || username.contains(">?")) {
            
            
            
            
            
            
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

    public static void main(String[] args) {
        Register r = new Register();
        System.out.println(r.checkMail("hoa123456@gmail.com"));
    }

    public String checkMail(String mail) {
        if (mail.length() >= 11) {
            String mail1 = "@gmail.com";
            String mail2 = mail.substring(mail.length() - 10, mail.length());
            String username = mail.substring(0, mail.length() - 10);
            if (username.length() >= 6 && username.length() <= 30) {
                if (mail1.equals(mail2)) {
                    if (!username.contains("..") && !Character.toString(username.charAt(0)).equals(".")
                            && !Character.toString(username.charAt(username.length() - 1)).equals(".")) {
                        if (username.contains("&") || username.contains("=") || username.contains("_") || username.contains("!")
                                || username.contains("'") || username.contains("-") || username.contains("+")
                                || username.contains(",") || username.contains("<") || username.contains(">")) {
                            return "wrongusernamechar";
                        } else {
                            return mail;
                        }
                    }
                    else{
                        return "wrongusername.";
                    }
                    
                } else {
                    return "@gmail.com";
                }

            } else {
                return "lenghtusername";
            }

        } else {
            return "length";

        }
    }

    public String strongPass(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (password.matches(pattern)) {
            return password;
        } else {
            return "reject";
        }
    }
}
