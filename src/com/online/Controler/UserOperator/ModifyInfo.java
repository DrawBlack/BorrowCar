package com.online.Controler.UserOperator;

import com.online.DAOlmpl.CommentImpl;
import com.online.DAOlmpl.Orderlmpl;
import com.online.DAOlmpl.VIPUserImplement;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.sql.SQLException;

/**
 * Created by macbookpro on 2016/12/10.
 *
 */
public class ModifyInfo extends HttpServlet {

    private VIPUserImplement vipUserImplement = new VIPUserImplement();

    private Orderlmpl orderlmpl = new Orderlmpl();

    private CommentImpl commentImpl = new CommentImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        String type = req.getParameter("type");
        String oldUsername = (String) session.getAttribute(session.getId());
        if (type.equals("modify username")){
            String newUsername = req.getParameter("username");
            try {
                boolean isHave = vipUserImplement.isHave(newUsername);
                if (isHave){
                    out.print("用户名已被占用，即将返回");
                    resp.setHeader("refresh" , "1;URL=/car/UserManager.jsp?username="+oldUsername);
                }else {
                    vipUserImplement.updateUsername(oldUsername , newUsername);
                    orderlmpl.updateUsername(oldUsername , newUsername);
                    session.setAttribute(session.getId(),newUsername);
                    commentImpl.updateComment(oldUsername , newUsername);
                    resp.sendRedirect("/car/UserManager.jsp?username="+newUsername);
                }
            } catch (SQLException e) {
                resp.sendRedirect("/car/Error.html");
                e.printStackTrace();
            }
        }else if (type.equals("modify password")){
            String newPassword = req.getParameter("password");
            try {
                vipUserImplement.updateUserPassword(newPassword , oldUsername);
                resp.sendRedirect("/car/UserManager.jsp?username="+oldUsername);
            } catch (SQLException e) {
                resp.sendRedirect("/car/Error.html");
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }else {
            String phone = req.getParameter("phone");
            try {
                vipUserImplement.updateUserPhone(oldUsername,phone);
                resp.sendRedirect("/car/UserManager.jsp?username="+oldUsername);
            } catch (SQLException e) {
                resp.sendRedirect("/car/Error.html");
                e.printStackTrace();
            }
        }
    }
}
