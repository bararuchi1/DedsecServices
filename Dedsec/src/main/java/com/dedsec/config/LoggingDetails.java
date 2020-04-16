package com.dedsec.config;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.springframework.stereotype.Component;

//@Component
public class LoggingDetails {
	private static Logger LOGGER = Logger.getLogger(LoggingDetails.class.getName());
	FileHandler fh;

	public LoggingDetails() {
		try {
			fh = new FileHandler("B:\\wings\\Log\\DedsecLog.log");
			LOGGER.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			LOGGER.info("My first log");
		} catch (Exception e) {
			LOGGER.info("LOGGING DETAILS");
			e.printStackTrace();
		}
	}

}
