package com.ml.serviceapi.controllers;

import com.ml.serviceapi.controllers.requests.HelloWorldRequest;
import com.ml.serviceapi.controllers.requests.HelloWorldResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/serviceapi")
public class ServiceApiController {

    @GetMapping("/helloWorld")
    public String helloWorld() {
        String msg = "Hello, World!";
        log.info("execute 'serviceapi' HTTP request. type: '{}' name: '{}', msg: {}", HttpMethod.POST, "helloWorld", msg);
        return msg;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        String msg = "Hello, " + name + "!";
        log.info("execute 'serviceapi' HTTP request. type: '{}' name: '{}', msg: {}", HttpMethod.POST, "helloWorld", msg);
        return msg;
    }

    @PostMapping("/helloWorld")
    public HelloWorldResponse helloWorld(@RequestBody HelloWorldRequest request) {
        String msg = String.format("%s, %s!", request.getGreet(), request.getName());
        log.info("execute 'serviceapi' HTTP request. type: '{}' name: '{}', msg: {}", HttpMethod.POST, "helloWorld", msg);
        return new HelloWorldResponse().setMethod("helloWorld").setType(HttpMethod.POST.name()).setMsg(msg);
    }
}
