package com.iot.spring.spr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/HiStudent")
    String Hi() {
        return "Hello Tanya !";
    }
}
