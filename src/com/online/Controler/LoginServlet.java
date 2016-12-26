package com.online.Controler;

import com.online.DAOlmpl.VIPUserImplement;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;

/**
 * Created by macbookpro on 2016/12/5.
 *
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setCharacterEncoding("utf-8");
        VIPUserImplement vipUserImplement = new VIPUserImplement();
        try {
            boolean isHaveUser = vipUserImplement.isHaveUser(username , password);
            if (isHaveUser){
                HttpSession session = req.getSession();
                session.setAttribute(session.getId() , username);
                resp.sendRedirect("index.jsp");
            }else {
                resp.sendRedirect("/car/WrongUsernameOrWrongPassword.html");
            }
        } catch (SQLException e) {
            resp.sendRedirect("/car/Error.html");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            resp.sendRedirect("/car/Error.html");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            resp.sendRedirect("/car/Error.html");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            resp.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
