package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstantGameController {

    @ResponseBody
    @RequestMapping(value = "msg", method = RequestMethod.GET)
    public String getMsg() {
        return "message";
    }

}
