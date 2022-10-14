package com.javakc.cims.subsidy.controller;

import com.google.gson.Gson;
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
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
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

        String tag = request.getParameter("tag");
        if ("remove".equals(tag)) {
            this.delete(request, response, type);
        }
        else if ("batch".equals(tag)) {
            this.batch(request, response, type);
        } else if ("subsidy".equals(tag)) {
            this.subsidy(request, response, type);
        }
        else if ("load".equals(tag)) {
            this.load(request, response, type);
        }
        else if ("update".equals(tag)) {
            this.update(request, response, type);
        } else {
            page(request, response, type);
        }


    }

    private void subsidy(HttpServletRequest request, HttpServletResponse response, String type)throws ServletException, IOException {
        List<Map<String, Object>> maps = service.querySubsidy(Validate.isInteger(type) ? Integer.parseInt(type) : 0);
        Gson gson=new Gson();
        String s = gson.toJson(maps);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.flush();
        writer.close();

    }

    private void update(HttpServletRequest request, HttpServletResponse response, String type)throws ServletException,IOException {
        String id = request.getParameter("id");
        String money = request.getParameter("money");
        String reason = request.getParameter("reason");
        Subsidy subsidy=new Subsidy();
        subsidy.setId(Validate.isInteger(id)?Integer.parseInt(id):0);
        subsidy.setMoney(new BigDecimal(money));
        subsidy.setReason(reason);
        int result = service.update(subsidy);
        System.err.println("update|"+result);
        response.sendRedirect(request.getContextPath()+"/subsidy.do?type="+type);
    }

    private void load(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException,IOException {
        String id = request.getParameter("id");
        if (Validate.isNotEmpty(id)&&Validate.isInteger(id)){
            Subsidy subsidy = service.queryById(Integer.parseInt(id));
            request.setAttribute("subsidy",subsidy);
            request.getRequestDispatcher(request.getContextPath()+"/view/subsidy/update.jsp").forward(request,response);
        }else {
            response.sendError(400,"id参数不正确");
        }
    }

    protected void batch(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
        String[] ids = request.getParameterValues("ids");
            System.err.println(ids.length);
        if (Validate.isNotEmpty(ids)) {
            int[] ints = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
            service.deletes(ints);
        }
        response.sendRedirect(request.getContextPath()+"/subsidy.do?type="+type);

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (Validate.isInteger(id)) {
            int result = service.delete(Integer.parseInt(id));
            System.out.println("删除:" + result + "id=" + id);
        }
        response.sendRedirect(request.getContextPath() + "/subsidy.do?type=" + type);
    }

    protected void page(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
        int currentSize = 1;
        String current = request.getParameter("currentSize");
        if (Validate.isNotEmpty(current) && Validate.isInteger(current)) {
            currentSize = Integer.parseInt(current);
        }
        int pageSize = 7;
        String pages = request.getParameter("pageSize");
        if (Validate.isNotEmpty(pages) && Validate.isInteger(pages)) {
            pageSize = Integer.parseInt(pages);
        }


        int startSize = (currentSize - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String month = request.getParameter("month");
        params.put("type", type);
        params.put("startSize", startSize);
        params.put("pageSize", pageSize);
        params.put("name", name);
        params.put("code", code);
        params.put("month", month);
        List<Subsidy> subsidyList = service.queryByPage(params);
        System.out.println(Integer.parseInt(type));
        long totalSize = service.queryByCount(params);

        int maxSize = (int) Math.ceil((totalSize * 1.0 / pageSize));
        request.setAttribute("list", subsidyList);
        request.setAttribute("currentSize", currentSize);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalSize", totalSize);
        request.setAttribute("maxSize", maxSize);
        request.setAttribute("type", type);
        request.getRequestDispatcher("view/subsidy/list.jsp").forward(request, response);
    }
}
