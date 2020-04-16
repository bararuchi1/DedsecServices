package com.dedsec;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class DedsecApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(DedsecApplication.class);

	public static void  main(String[] args) {
		SpringApplication.run(DedsecApplication.class, args);
	LOGGER.info("Application Started I guess - Bararuchi Mohany.");
	}

}
