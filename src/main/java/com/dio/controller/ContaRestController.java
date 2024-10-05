package com.dio.controller;

import com.dio.model.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("conta")
public class ContaRestController {
    @GetMapping
    public void teste(){
        System.out.println("teste");
    }


}
