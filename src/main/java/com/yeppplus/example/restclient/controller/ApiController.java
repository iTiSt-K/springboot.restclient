package com.yeppplus.example.restclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeppplus.example.restclient.service.AApiService;
import com.yeppplus.example.restclient.service.BApiService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class ApiController {
    private final AApiService aApiService;
    private final BApiService bApiService;

    // public ApiController(AApiService aApiService, BApiService bApiService) {
    //     this.aApiService = aApiService;
    //     this.bApiService = bApiService;
    // }

    @GetMapping("/a/{query}")
    public String testA(@PathVariable String query) {
        return aApiService.getAData(query);
    }

    @GetMapping("/b/{query}")
    public String testB(@PathVariable String query) {
        return bApiService.getBData(query);
    }
}
