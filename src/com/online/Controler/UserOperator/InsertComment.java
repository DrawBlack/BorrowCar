package com.online.Controler.UserOperator;

import com.online.DAOlmpl.CommentImpl;
import com.online.Model.CommentVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by macbookpro on 2016/12/9.
 *
 */
public class InsertComment extends HttpServlet {

    private CommentImpl commentImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        if (session.getAttribute(session.getId())!=null){
            String comment = new String(req.getParameter("comment").getBytes("utf-8"),"utf-8");
            System.out.print(comment);
            CommentVO commentVO = new CommentVO((String) session.getAttribute(session.getId()), Integer.parseInt(req.getParameter("carID")) , comment);
            commentImpl = new CommentImpl();
            try {
                commentImpl.insertComment(commentVO);
            } catch (SQLException e) {
                resp.sendRedirect("/car/Error.html");
                e.printStackTrace();
            }
            resp.sendRedirect("/car/details.jsp?id="+commentVO.getCarID());
        }else {
            resp.sendRedirect("/car/NoLogin.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
