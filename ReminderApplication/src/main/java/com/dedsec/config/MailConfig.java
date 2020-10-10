package com.dedsec.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailConfig {

	@Value("${spring.mail.host}")
	private String hosts;
	@Value("${spring.mail.port}")
	private int port;
	@Value("${spring.mail.username}")
	private String userName;
	@Value("${spring.mail.password}")
	private String password;

	public String getHosts() {
		return hosts;
	}

	public void setHosts(String hosts) {
		this.hosts = hosts;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "MailConfig [hosts=" + hosts + ", port=" + port + ", userName=" + userName + ", password=" + password
				+ "]";
	}

}
