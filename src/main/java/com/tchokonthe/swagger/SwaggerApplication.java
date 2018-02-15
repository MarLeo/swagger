package com.tchokonthe.swagger;

import emoji4j.EmojiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwaggerApplication {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);

        logger.info("The Application start successfully ", EmojiUtils.getEmoji(":)").getEmoji());


	}
}
