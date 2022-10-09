package com.javakc.cims.person.controller;

import com.javakc.cims.person.entity.Person;
import com.javakc.cims.person.factory.PersonFactory;
import com.javakc.cims.person.service.PersonService;
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

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:28
 * @description: [人员表现层实现]
 */
@WebServlet(
        name = "PersonController",
        urlPatterns = "/person.do",
        loadOnStartup = 0
)
public class PersonController extends HttpServlet {

    //获取逻辑层实现类
    private final PersonService personService = PersonFactory.getPersonService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Map<String,Object> params = new HashMap<>();

       int currentSize=1;
        String currentSizeParam = request.getParameter("currentSize");
        if (Validate.isNotEmpty(currentSizeParam)&&Validate.isInteger(currentSizeParam)){
            currentSize=Integer.parseInt(currentSizeParam);
        }
       int pageSize=7;
        String pageSizeParam=request.getParameter("pageSize");
        if (Validate.isNotEmpty(pageSizeParam)&&Validate.isInteger(pageSizeParam)){
            pageSize=Integer.parseInt(pageSizeParam);
        }
        int startSize=(currentSize-1)*pageSize;
       params.put("startSize",startSize);
       params.put("pageSize",pageSize);
        long totalSize = personService.queryByCount(params);
        System.out.println("数据库总条数:"+totalSize);
        int maxSize =(int)Math.ceil(totalSize * 1.0 / pageSize);
        System.out.println("最大页数："+maxSize);
        List<Person> list = personService.queryByPage(params);

        request.setAttribute("list",list);

        request.setAttribute("startSize",startSize);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("totalSize",totalSize);
        request.setAttribute("maxSize",maxSize);
        request.setAttribute("currentSize",currentSize);
        request.getRequestDispatcher("/view/person/list.jsp").forward(request,response);
    }

}
