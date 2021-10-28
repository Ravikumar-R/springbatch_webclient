package com.ravi.springbatch.controller;

import com.github.javafaker.Faker;
import com.ravi.springbatch.domain.NameList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
public class APIController {

    int count = 0;

    @Autowired
    private WebClient webClient;

    @GetMapping("/getNames")
    private NameList getNames() {
        System.out.println("Inside get names..");
        count++;
        if(count >= 10)
            return null;
        sleep(1000);
        NameList names = new NameList();
        for (int i = 0; i < 10; i++) {
            names.getNames().add(Faker.instance().name().firstName());
        }
        return names;
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}