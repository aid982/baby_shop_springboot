package com.osetskiy.baby_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* for standalone run
 * public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}*/
@SpringBootApplication
public class Application {
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
		
	}

}
