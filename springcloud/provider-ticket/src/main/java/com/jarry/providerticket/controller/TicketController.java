package com.jarry.providerticket.controller;

import com.jarry.providerticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: SpringCloud
 * @BelongsPackage: com.jarry.providerticket.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-10 16:11
 */
@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/ticket")
    public String getTicket(String name){
        return ticketService.getTicket();
    }

}
