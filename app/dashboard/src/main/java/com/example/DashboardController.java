package com.example;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DashboardController {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMsg() {
        final String msg = restTemplate.getForObject("http://instant-game/msg", String.class, Maps.newHashMap());
        return "xxx: " + msg;
    }

}
