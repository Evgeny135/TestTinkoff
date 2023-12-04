package com.example.testtinkoff.controllers;

import com.example.testtinkoff.forms.FormRequest;
import com.example.testtinkoff.forms.FormResponse;
import com.example.testtinkoff.services.TranslationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Controller {

    @Autowired
    private TranslationService translationService;

    @PostMapping(value = "/",produces="application/json")
    public FormResponse index(@RequestBody FormRequest formRequest, HttpServletRequest request){
        LocalDateTime localDateTime = LocalDateTime.now();
        return translationService.getResponse(formRequest,request.getRemoteAddr(), localDateTime);
    }
}
