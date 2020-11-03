/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
public abstract class BaseServlet extends HttpServlet {

    public abstract String getFileName();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String userName = (String) session.getAttribute("user");

        String page = "loginForm.jsp";
        if (userName != null) {
        String userType = (String) session.getAttribute("userType").toString();
            page = getFileName();
            if (isTeacherOnly()&&userType.equals("0"))
                page = "errorPage/accessDenied.jsp";
        }

        request.setAttribute("page", page);
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    public boolean isTeacherOnly() {
        return false;
    }
}
