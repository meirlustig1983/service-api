package com.ml.serviceapi.controllers.requests;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HelloWorldResponse {
    private String method;
    private String type;
    private String msg;
}