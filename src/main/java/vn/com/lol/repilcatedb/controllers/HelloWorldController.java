package vn.com.lol.repilcatedb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.repilcatedb.configs.datasource.AppConfig;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HelloWorldController {

    private final AppConfig appConfig;


    @GetMapping("/hello")
    public String helloWorld() {
        System.out.println(appConfig.getDatabase().getMaster().getHost());
        System.out.println(appConfig.getDatabase().getSlaves().size());


        return "Hello World!";
    }
}
