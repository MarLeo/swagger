package com.tchokonthe.swagger.api;

import feign.Headers;
import feign.RequestLine;

import java.util.List;

@Headers("Accept: application/json")
public interface SwaggerAPI {

    @RequestLine("GET /api/users")
    List getAllUsers();


}
