package com.online.Controler;

import com.online.DAOlmpl.VIPUserImplement;
import com.online.Model.VIPUserVO;

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
import java.util.logging.Logger;

/**
 *
 *
 * Created by macbookpro on 2016/12/6.
 *
 *
 */
public class SignUpServlet extends HttpServlet {

    private Logger logger = Logger.getLogger("borrowcar");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("utf-8");
        //String username = new String(req.getParameter("username").getBytes("8859_1"),"UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.print(username);
        VIPUserImplement vipUserImplement = new VIPUserImplement();
        try {
            boolean isHave = vipUserImplement.isHave(username);
            if (isHave){
                resp.sendRedirect("/car/UsernameOccupation.html");
            }else {
                vipUserImplement.insertUser(new VIPUserVO(username , password , null));
                HttpSession session = req.getSession();
                session.setAttribute(session.getId() , username);
                resp.sendRedirect("/car/index.jsp");
            }
        } catch (SQLException e) {
            logger.warning("sign up出现问题");
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
