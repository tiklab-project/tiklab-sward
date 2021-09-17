package com.doublekit.user.auth.passport.filter;

import com.doublekit.common.exception.DarthException;
import com.doublekit.user.auth.passport.model.Ticket;
import com.doublekit.user.auth.passport.service.AuthService;
import com.doublekit.user.auth.passport.service.AuthServiceFactory;
import com.doublekit.user.auth.setting.support.AuthConfigContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Component
@Order(2)
public class TicketFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(TicketFilter.class);

    @Autowired
    AuthServiceFactory authServiceFactory;

    //排除url列表
    private static String[] staticTypes = {
            ".ico",
            ".jpg",
            ".jpeg",
            ".png",
            ".gif",
            ".html",
            ".js",
            ".css",
            ".json",
            ".xml",
            ".ftl",
    };

    //排除url列表
    private static String[] excludeUrls = {
            "/",
            "/passport/login",
            "/passport/logout",
            "/auth/valid",
            "/document/view",
            "/comment/view",
            "/share/verifyAuthCode",
            "/share/judgeAuthCode"
    };

    //排除url前缀列表
    private static String[] excludePreUrls = {
            "/api/list",
            "/api/detail",
            "/file",
            "/plugin",
            "/authConfig"
    };

    //表态文件类型list
    private static List<String> staticTypeList = new ArrayList<>();

    //精确排除url list
    private static List<String> excludeUrlList = new ArrayList<>();

    //模糊排除url list
    private static List<String> excludePreUrlList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        staticTypeList = Arrays.asList(staticTypes);
        excludeUrlList = Arrays.asList(excludeUrls);
        excludePreUrlList = Arrays.asList(excludePreUrls);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String path = httpServletRequest.getRequestURI();

        //ticket安全验证
        if(!isExcludeUrl(path)){
            String requestURI = httpServletRequest.getRequestURI();
            //非空判断
            String ticketId = httpServletRequest.getHeader("ticket");
            if(StringUtils.isEmpty(ticketId) || "null".equalsIgnoreCase(ticketId)){
                throw new DarthException(1000,
                        String.format("requestURI:%s,ticket must not be null.",requestURI)
                );
            }

            //验证ticket
            String authType = AuthConfigContext.getAuthConfig().getAuthType();
            AuthService authService = authServiceFactory.create(authType);
            Ticket ticket = authService.valid(ticketId);
            if(ticket == null){
                throw new DarthException(1001, String.format("requestURI:%s,ticket invalid.",requestURI));
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    void printHeaders(HttpServletRequest httpServletRequest){
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String headerValue = httpServletRequest.getHeader(headerName);
            //logger.info("header,name:{},value:{}",headerName,headerValue);
        }
    }

    /**
     * 判断是否为排除url
     * @param path
     * @return
     */
    boolean isExcludeUrl(String path){
        for(String staticType:staticTypeList){
            if(path.endsWith(staticType)){
                return true;
            }
        }

        //匹配
        if(excludeUrlList.contains(path)){
            return true;
        }

        //前缀匹配
        for(String excludePreUrl: excludePreUrlList){
            if(path.contains(excludePreUrl)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}