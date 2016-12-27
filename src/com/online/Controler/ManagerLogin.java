package com.online.Controler;

import com.online.DAOlmpl.ManagertImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by macbookpro on 2016/12/10.
 *
 */
public class ManagerLogin extends HttpServlet {

    private static ManagertImpl manager = new ManagertImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            boolean isHave = manager.isHave(name , password);
            if (isHave){
                session.setAttribute(session.getId() , name);
                resp.sendRedirect("/car/Manager.jsp");
            }else {
                out.print("用户名或者密码不正确，请重新输入,即将跳转");
                resp.setHeader("refresh" , "2;URL=/car/ManagerLogin.jsp");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
    }
}
