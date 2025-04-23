package com.enricus.curso.springboot.app.interceptor.springboot_interceptor.interceptors;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);
        
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
            HandlerMethod controller = (HandlerMethod)handler;
            logger.info("LoadingTimeInterceptor: preHandle() entrando... " + controller.getMethod().getName());

            long start = System.currentTimeMillis();
            request.setAttribute("start", start);
            Random r = new Random();
            int delay = r.nextInt(500);
            Thread.sleep(delay);

            //TO HANDLE A RESPONSE WHEN FALSE IS RETURNED
            // Map<String, String> result = new HashMap<>();
            // result.put("error", "Acced Denied!");
            // result.put("date", new Date().toString());

            // ObjectMapper mapper = new ObjectMapper();
            // String jsonString = mapper.writeValueAsString(result);

            // response.setContentType("application/json");
            // response.setStatus(401);
            // response.getWriter().write(jsonString);
            // return false;
            return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
                HandlerMethod controller = (HandlerMethod)handler;
                long end = System.currentTimeMillis();
                long start = (long)request.getAttribute("start");
                long lapsed = end - start;
                logger.info("lapsed time: " + lapsed + " milliseconds");
                logger.info("LoadingTimeInterceptor: postHandle() saliendo..." + controller.getMethod().getName());
    }

}
