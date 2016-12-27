package com.online.Controler;

import com.online.DAOlmpl.*;
import com.online.Model.NewsVO;
import com.online.Model.OrderVO;
import com.online.Model.VIPUserVO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * Created by macbookpro on 2016/12/11.
 *
 */
public class ManagerOperator extends HttpServlet {

    private Logger logger = Logger.getLogger("borrowcar");

    private static VIPUserImplement vipUserImplement = new VIPUserImplement();

    private static Orderlmpl orderlmpl = new Orderlmpl();

    private static CarImpl carImpl = new CarImpl();

    private static NewsImpl newsImpl = new NewsImpl();

    private static CommentImpl commentImpl = new CommentImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("submit");
        switch (type){
            case "deleteUser" : deleteUser(req , resp); break;
            case "addUser" : addUser(req , resp) ; break;
            case "deleteOrder" : deleteOrder(req , resp) ; break;
            case "addOrder" : addOrder(req , resp) ; break;
            case "deleteCar" : deleteCar(req ,resp) ; break;
            case "deleteNews" : deleteNews(req , resp) ; break;
            case "addNews" : addNews(req , resp) ; break;
            case "deleteComments" : deleteComments(req , resp) ; break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    private boolean deleteUser(HttpServletRequest request , HttpServletResponse response ) throws IOException{
        String username = request.getParameter("username");
        boolean result = false;
        try {
            result = vipUserImplement.deleteUser(username);
        } catch (SQLException e) {
            logger.warning("servlet删除用户出错");
            response.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
        response.sendRedirect("/car/Manager.jsp");
        return result;
    }

    private boolean addUser(HttpServletRequest request , HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        boolean result = false;
        try {
            result = vipUserImplement.insertUser(new VIPUserVO(username , password , phone));
        } catch (SQLException e) {
            logger.warning("servlet增加用户出错");
            response.sendRedirect("/car/Error.html");
            e.printStackTrace();
            return false;
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/car/Manager.jsp");
        return result;
    }

    private boolean deleteOrder(HttpServletRequest request , HttpServletResponse response)throws IOException{
        String username = request.getParameter("username");
        int carID = Integer.parseInt(request.getParameter("carID"));
        boolean result = false;
        try {
            result = orderlmpl.deleteOrder(new OrderVO(username , carID , null));
        } catch (SQLException e) {
            logger.warning("servlet删除订单出现错误");
            response.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
        response.sendRedirect("/car/Manager.jsp");
        return result;

    }

    private boolean addOrder(HttpServletRequest request , HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        int carID = Integer.parseInt(request.getParameter("carID"));
        String createTime = request.getParameter("createTime");
        boolean result = false;
        try {
            result = orderlmpl.createOrder(new OrderVO(username , carID , createTime));
        } catch (SQLException e) {
            logger.warning("servlet增加订单出现错误");
            response.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
        response.sendRedirect("/car/Manager.jsp");
        return result;
    }

    private boolean deleteCar(HttpServletRequest request , HttpServletResponse response) throws IOException{
        int carID = Integer.parseInt(request.getParameter("carID"));
        boolean result = false;
        try {
            result =  carImpl.deleteCar(carID);
        } catch (SQLException e) {
            logger.warning("servlet删除汽车出错");
            response.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
        response.sendRedirect("/car/Manager.jsp");
        return result;
    }

    private boolean deleteNews(HttpServletRequest request , HttpServletResponse response) throws IOException{
        int id = Integer.parseInt(request.getParameter("newsID"));
        boolean result = false;
        try {
            result = newsImpl.deleteNews(id);
        } catch (SQLException e) {
            logger.warning("servlet删除新闻出错");
            response.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
        response.sendRedirect("/car/Manager.jsp");
        return result;
    }

    private boolean addNews(HttpServletRequest request , HttpServletResponse response) throws IOException{
        int id = Integer.parseInt(request.getParameter("newsID"));
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        String createTime = request.getParameter("createTime");
        boolean result = false;
        try {
            result = newsImpl.addNews(new NewsVO(id , title , contents , createTime));
        } catch (SQLException e) {
            logger.warning("servlet增加新闻出错");
            response.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
        response.sendRedirect("/car/Manager.jsp");
        return result;
    }

    private boolean deleteComments (HttpServletRequest request , HttpServletResponse response) throws IOException{
        int id = Integer.parseInt(request.getParameter("carID"));
        String username = request.getParameter("username");
        boolean result = false;
        try {
            result = commentImpl.deleteComment(username , id);
        } catch (SQLException e) {
            logger.warning("servlet删除留言评论出错");
            response.sendRedirect("/car/Error.html");
            e.printStackTrace();
        }
        response.sendRedirect("/car/Manager.jsp");
        return result;
    }

}
