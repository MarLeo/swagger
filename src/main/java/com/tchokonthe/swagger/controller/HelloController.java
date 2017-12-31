package com.tchokonthe.swagger.controller;

import com.tchokonthe.swagger.model.Greeting;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "Greeting Controller", description = "shows greetings", tags = "Greeting API")
public class HelloController {

    @ApiOperation(value = "Returns Greetings", response = Greeting.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Greetings", required = false,
                             dataType = "string",
                             paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 100, message = "100 is the message"),
            @ApiResponse(code = 200, message = "Successful Hello World")
    })
    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting hello(@RequestParam("name") String name) {
        return new Greeting(name, "Hello " + name);
    }


}
