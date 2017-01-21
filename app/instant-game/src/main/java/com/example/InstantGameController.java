package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class InstantGameController {

    @ResponseBody
    @RequestMapping(value = "msg", method = RequestMethod.GET)
    public String getMsg(HttpSession httpSession) {
        return httpSession.getId() + (String)httpSession.getAttribute("aaa");
    }

    @ResponseBody
    @RequestMapping(value = "ss", method = RequestMethod.GET)
    public String getSess(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession().getId() + (String)httpServletRequest.getSession().getAttribute("aaa");
    }

}
