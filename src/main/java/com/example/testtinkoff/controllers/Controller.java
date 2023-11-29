package com.example.testtinkoff.controllers;

import com.example.testtinkoff.forms.FormRequest;
import com.example.testtinkoff.services.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @PostMapping(value = "/",produces="application/json")
    public String index(@RequestBody FormRequest formRequest, HttpServletRequest request){
        LocalDateTime localDateTime = LocalDateTime.now();
        return service.getResponse(formRequest,request.getRemoteAddr(), localDateTime);
    }
}
