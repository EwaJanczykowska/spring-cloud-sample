package com.example;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class DashboardController {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMsg(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute("aaa", "bbb");
        restTemplate.getInterceptors().add(new SpringSessionClientHttpRequestInterceptor());
        final String msg = restTemplate.getForObject("http://instant-game/msg", String.class, Maps.newHashMap());
        return "xxx: " + msg;
    }

    @ResponseBody
    @RequestMapping(value = "ss", method = RequestMethod.GET)
    public String getSess(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession().getId() + (String)httpServletRequest.getSession().getAttribute("aaa");
    }

    public class SpringSessionClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
            boolean isMyService = true;

            // very important not to send the session id to external services
            if(isMyService) {
                request.getHeaders().add("Cookie", "SESSION="+RequestContextHolder.getRequestAttributes().getSessionId());
            }
            return execution.execute(request, body);
        }
    }

}
