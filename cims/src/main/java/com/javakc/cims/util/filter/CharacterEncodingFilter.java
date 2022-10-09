package com.javakc.cims.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:29
 * @description: [中文字符键编码]
 */
@WebFilter(
        filterName = "CharacterEncodingFilter",
        urlPatterns = {"*.do"},
        initParams = {
                @WebInitParam(
                        name = "encoding",
                        value = "UTF-8"
                )
        }
)
public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        if (null == encoding || "".equals(encoding)) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}

