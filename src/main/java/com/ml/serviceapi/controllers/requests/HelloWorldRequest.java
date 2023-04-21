package com.ml.serviceapi.controllers.requests;

import lombok.Data;

@Data
public class HelloWorldRequest {

    private String greet;
    private String name;
}