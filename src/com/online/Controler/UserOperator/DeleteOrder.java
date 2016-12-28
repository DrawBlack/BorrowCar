package com.online.Controler.UserOperator;

import com.online.DAOlmpl.Orderlmpl;
import com.online.Model.OrderVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by macbookpro on 2016/12/10.
 *
 */
public class DeleteOrder extends HttpServlet {

    private static Orderlmpl orderlmpl = new Orderlmpl();

    private Logger logger = Logger.getLogger("borrowcar");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        String createTime = req.getParameter("createTime");
        int carID = Integer.parseInt(req.getParameter("carid"));
        String username = (String) session.getAttribute(session.getId());

        try {
            orderlmpl.deleteOrder(new OrderVO(username , carID , createTime));
        } catch (SQLException e) {
            logger.warning("删除order出现异常");
            e.printStackTrace();
        }
        out.print("删除成功，正在跳转回个人管理");
        resp.setHeader("refresh" , "1;URL=/car/UserManager.jsp?username=" + username);
    }

}
