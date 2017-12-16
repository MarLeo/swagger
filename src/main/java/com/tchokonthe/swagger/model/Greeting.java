package com.tchokonthe.swagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Greeting {

    @ApiModelProperty(notes = "Provides user name", required = true)
    private String player;

    @ApiModelProperty(notes = "The system generated greeting message", readOnly = true)
    private String message;

    public Greeting(String player, String message) {
        this.player = player;
        this.message = message;
    }

}
