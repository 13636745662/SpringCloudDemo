package com.jarry.consumerticket.controller;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.consumerticket.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-06 09:46
 */
import com.jarry.consumerticket.utils.ReqContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SubscribeController {

    @RequestMapping("/subscribe")
    public void subscribe(HttpServletRequest req, HttpServletResponse res, @RequestParam("topic") String topic) {
        ReqContextUtils.addSubscrib(topic, req, res);
    }

    @RequestMapping("/index")
    public String index(){

        return "/index";
    }

    @RequestMapping("/publish")
    public void publish(@RequestParam("topic") String topic, @RequestParam("content") String content) {
        ReqContextUtils.publishMessage(topic, content);
    }

}
