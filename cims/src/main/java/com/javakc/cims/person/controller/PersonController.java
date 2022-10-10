package com.javakc.cims.person.controller;

import com.javakc.cims.person.entity.Person;
import com.javakc.cims.person.factory.PersonFactory;
import com.javakc.cims.person.service.PersonService;
import com.javakc.cims.util.validate.Validate;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
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
    protected void service(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tag = request.getParameter("tag");
        if ("remove".equals(tag)) {
            this.remove(request, response);
        } else if ("batch".equals(tag)) {
            this.batch(request, response);
        } else if ("add".equals(tag)) {
            this.add(request, response);
        } else {
            this.page(request, response);
        }
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String unit = request.getParameter("unit");
        String state = request.getParameter("state");
        String grade = request.getParameter("grade");
        String[] subsidies = request.getParameterValues("subsidy");
        String reason = request.getParameter("reason");
        Person entity = new Person();
        entity.setName(name);
        entity.setCode(code);
        entity.setUnit(unit);
        if (Validate.isNotEmpty(state) && !"0".equals(state)) {
            entity.setState(Integer.parseInt(state));
        } else {
            response.sendError(400, "请选择人员状态");
            return;
        }
        if (Validate.isNotEmpty(grade) && !"0".equals(grade)) {
            entity.setGrade(Integer.parseInt(grade));
        } else {
            response.sendError(400, "请选择职级");
            return;
        }
        if (Validate.isNotEmpty(subsidies)) {
            if (subsidies.length == 1) {
                if (subsidies[0].equals("1")){
                    entity.setHeating(1);
                }else {
                    entity.setEstate(1);
                }
            } else {
                entity.setEstate(1);
                entity.setHeating(1);
            }
        }

        entity.setReason(reason);
        personService.insert(entity);
        response.sendRedirect(request.getContextPath()+"/person.do");
    }

    protected void batch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("ids");
        if (Validate.isNotEmpty(ids)) {
            int[] array = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
            personService.deletes(array);
            response.sendRedirect(request.getContextPath() + "/person.do");
        } else {
            response.sendError(400, "删除时携带的ID数据不正确[" + ids + "]");
        }
    }

    protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (Validate.isInteger(id)) {
            personService.delete(Integer.parseInt(id));
            response.sendRedirect(request.getContextPath() + "/person.do");
        } else {
            response.sendError(400, "删除时携带的ID数据不正确[" + id + "]");
        }

    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> params = new HashMap<>();

        int currentSize = 1;
        String currentSizeParam = request.getParameter("currentSize");
        if (Validate.isNotEmpty(currentSizeParam) && Validate.isInteger(currentSizeParam)) {
            currentSize = Integer.parseInt(currentSizeParam);
        }
        int pageSize = 7;
        String pageSizeParam = request.getParameter("pageSize");
        if (Validate.isNotEmpty(pageSizeParam) && Validate.isInteger(pageSizeParam)) {
            pageSize = Integer.parseInt(pageSizeParam);
        }
        int startSize = (currentSize - 1) * pageSize;

        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String grade = request.getParameter("grade");
        String heating = request.getParameter("heating");
        String estate = request.getParameter("estate");

        params.put("code", code);
        params.put("name", name);
        params.put("state", state);
        params.put("grade", grade);
        params.put("heating", heating);
        params.put("estate", estate);

        params.put("startSize", startSize);
        params.put("pageSize", pageSize);
        long totalSize = personService.queryByCount(params);
        System.out.println("数据库总条数:" + totalSize);
        int maxSize = (int) Math.ceil(totalSize * 1.0 / pageSize);
        System.out.println("最大页数：" + maxSize);
        List<Person> list = personService.queryByPage(params);

        request.setAttribute("list", list);

        request.setAttribute("startSize", startSize);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalSize", totalSize);
        request.setAttribute("maxSize", maxSize);
        request.setAttribute("currentSize", currentSize);
        request.setAttribute("params", params);
        request.getRequestDispatcher("/view/person/list.jsp").forward(request, response);
    }

}
