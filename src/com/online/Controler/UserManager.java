package com.online.Controler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by macbookpro on 2016/12/5.
 *
 */
public class UserManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute(session.getId())!=null&&!session.getAttribute(session.getId()).equals("root")){
            resp.sendRedirect("/car/UserManager.jsp?username="+session.getAttribute(session.getId()));
        }else {
            resp.sendRedirect("/car/NoLogin.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req , resp);
    }
}
