package com.javakc.cims.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:31
 * @description: [数据字典监听器]
 */
@WebListener
public class DataDictionaryListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //初始化职级数据
        Map<Integer, String> grade = new LinkedHashMap<>();
        grade.put(1, "省级正职");
        grade.put(2, "省级副职");
        grade.put(3, "厅级正职");
        grade.put(4, "厅级副职");
        grade.put(5, "县级正职");
        grade.put(6, "县级副职");
        sce.getServletContext().setAttribute("grade", grade);
    }

}
