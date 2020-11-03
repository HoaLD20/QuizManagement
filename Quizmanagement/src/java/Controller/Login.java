package Controller;

import Model.Entity.User;
import Model.UserModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.UserModel;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author root
 */
public class Login extends BaseServlet {

    @Override
    public String getFileName() {
        return "loginForm.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel userModel = null;
        try {
            userModel = new UserModel();
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            req.setAttribute("savedUserName", userName);
            User user = userModel.login(userName, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user.getUserName());
                session.setAttribute("userId", user.getId());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("userType", user.getType());
                resp.sendRedirect("Index");
            } else {
                req.setAttribute("m", "fail");
                req.setAttribute("page", "loginForm.jsp");
                getServletContext().getRequestDispatcher("/index.jsp")
                        .forward(req, resp);
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            getServletContext().getRequestDispatcher("/errorPage/errorPage.jsp")
                    .forward(req, resp);
        }
    }
}