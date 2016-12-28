package com.online.Controler.UserOperator;


import com.online.DAOlmpl.Orderlmpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by macbookpro on 2016/12/10.
 *
 */
public class OrderCar extends HttpServlet {

    private static Orderlmpl orderlmpl = new Orderlmpl();

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute(session.getId())!=null){
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();

            String username = req.getParameter("username");
            String createTime =df.format(new Date());
            int carid = Integer.parseInt(req.getParameter("carID"));

            if (orderlmpl.isHave(username , carid , createTime)){
                out.print("预约成功，即将返回主页");
                resp.setHeader("refresh" ,"3;URL=/car/index.jsp");
            }else {
                out.print("你已经预约过，即将返回主页");
                resp.setHeader("refresh" ,"3;URL=/car/index.jsp");
            }

        }else {
            resp.sendRedirect("/car/NoLogin.html");
        }
    }
}
