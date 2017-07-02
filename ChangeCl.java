package com.store;

/**
 * Created by Queeny on 2017/4/25.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeCl extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        String sign=request.getParameter("sign");

        //删除
        if(sign.equals("del"))
        {
            String username= (String)request.getSession().getAttribute("username");
            String temp=request.getParameter("ID");
            int id=Integer.parseInt(temp);
            UeserBeanCl ubc=new UeserBeanCl();

            boolean b=ubc.delete(id,username);

            if(b)
            {
                request.getRequestDispatcher("Result.jsp").forward(request, response);

            }
            else
            {
                request.getRequestDispatcher("Fail.jsp").forward(request, response);
            }

        }
        //增加
        else if(sign.equals("add"))
        {
            String username=request.getParameter("username");
            String name=request.getParameter("name");

            String remark=request.getParameter("remark");
            String per=request.getParameter("per");
            username=Utf.getString(username);
            name=Utf.getString(name);

            remark=Utf.getString(remark);
            per=Utf.getString(per);
            String quantity=request.getParameter("quantity");
            String exp=request.getParameter("exp");
            String pri=request.getParameter("price");
            double price=Double.valueOf(pri);

            UeserBeanCl ubc=new UeserBeanCl();
            boolean b=ubc.add(username,name, quantity, exp,remark,price,per);

            if(b)
            {

                request.getRequestDispatcher("Result.jsp").forward(request, response);

            }
            else
            {
                request.getRequestDispatcher("Fail.jsp").forward(request, response);
            }

        }

        //修改
        else if(sign.equals("update"))
        {
            String id=request.getParameter("ID");
            String quantity=request.getParameter("quantity");
            String exp=request.getParameter("exp");
            String name=request.getParameter("name");
            String remark=request.getParameter("remark");
            String pri=request.getParameter("price");
            String per=request.getParameter("per");
            String username=request.getParameter("username");
            name=Utf.getString(name);
            remark=Utf.getString(remark);
            per=Utf.getString(per);
            double price=Double.valueOf(pri);
            UeserBeanCl ubc=new UeserBeanCl();

            boolean b=ubc.change(name,id,quantity,exp,remark,price,per,username);

            if(b)
            {
                request.getRequestDispatcher("Result.jsp").forward(request, response);

            }
            else
            {
                request.getRequestDispatcher("Fail.jsp").forward(request, response);
            }
        }

        //查询
        else if(sign.equals("find"))
        {
            String name=request.getParameter("name");

            UeserBeanCl ubc=new UeserBeanCl();
            boolean b=ubc.find(name);

            if(b)
            {
                request.setAttribute("name", name);
                request.getRequestDispatcher("FindResult.jsp").forward(request, response);


            }
            else
            {
                request.getRequestDispatcher("Fail.jsp").forward(request, response);
            }

        }



    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}

