package com.tchokonthe.swagger.api;

import com.tchokonthe.swagger.model.User;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class SwaggerAPIDownload implements SwaggerAPI {

    private static final String URI_USER = "http://localhost:8080";


    private static final AtomicLong counter = new AtomicLong();

    private static List<User> users;

    static {
        users = populate();
    }

    private static List<User> populate() {
        List<User> users = new ArrayList<>();
        users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000));
        users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000));
        users.add(new User(counter.incrementAndGet(), "Jerome", 45, 30000));
        users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
        return users;
    }

    @Override
    public List getAllUsers() {

        SwaggerAPI api = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(SwaggerAPI.class))
                .logLevel(Logger.Level.FULL)
                .target(SwaggerAPI.class, URI_USER);


        return users;
    }
}
