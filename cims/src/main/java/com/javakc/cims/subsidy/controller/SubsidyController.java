package com.javakc.cims.subsidy.controller;

import com.javakc.cims.subsidy.entity.Subsidy;
import com.javakc.cims.subsidy.factory.SubsidyFactory;
import com.javakc.cims.subsidy.service.SubsidyService;
import com.javakc.cims.util.validate.Validate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "SubsidyController",
        value = "/subsidy.do",
        loadOnStartup = 0
)
public class SubsidyController extends HttpServlet {

    private SubsidyService service = SubsidyFactory.getSubsidyService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        page(request, response, type);


    }

    protected void page(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
        int currentSize=1;
        String current = request.getParameter("currentSize");
        if (Validate.isNotEmpty(current)&&Validate.isInteger(current)){
            currentSize=Integer.parseInt(current);
        }
        int pageSize = 7;
        String pages = request.getParameter("pageSize");
        if (Validate.isNotEmpty(pages)&&Validate.isInteger(pages)){
            pageSize=Integer.parseInt(pages);
        }


        int startSize = (currentSize - 1) * pageSize;
        Map<String,Object> params=new HashMap<>();
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String month = request.getParameter("month");

        params.put("type",type);
        params.put("startSize",startSize);
        params.put("pageSize",pageSize);
        params.put("name",name);
        params.put("code",code);
        params.put("month",month);
        List<Subsidy> subsidyList = service.queryByPage(params);
        System.out.println(Integer.parseInt(type));
        long totalSize = service.queryByCount(params);

        int maxSize =(int) Math.ceil((totalSize * 1.0 / pageSize));
        request.setAttribute("list",subsidyList);
        request.setAttribute("currentSize",currentSize);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("totalSize",totalSize);
        request.setAttribute("maxSize",maxSize);
        request.setAttribute("type",type);

        request.getRequestDispatcher("view/subsidy/list.jsp").forward(request,response);
    }
}
