package com.dedsec.bean;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class ReminderDetails {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String wishMessage;
	private String occassion;
	private String emailId;
	private LocalDateTime eventDate;
	// private String eventTime;
	private String messageSentStatus;

	private int errorCode;
	private String errorMessage;

	private String mailSent="N"; // N,Y,I,F,R //NO,YES,INITIATE,FAILED,RETRIED

	public String getMailSent() {
		return mailSent;
	}

	public void setMailSent(String mailSent) {
		this.mailSent = mailSent;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessageSentStatus() {
		return messageSentStatus;
	}

	public void setMessageSentStatus(String messageSentStatus) {
		this.messageSentStatus = messageSentStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWishMessage() {
		return wishMessage;
	}

	public void setWishMessage(String wishMessage) {
		this.wishMessage = wishMessage;
	}

	public String getOccassion() {
		return occassion;
	}

	public void setOccassion(String occassion) {
		this.occassion = occassion;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "ReminderDetails [id=" + id + ", name=" + name + ", wishMessage=" + wishMessage + ", occassion="
				+ occassion + ", emailId=" + emailId + ", eventDate=" + eventDate + ", messageSentStatus="
				+ messageSentStatus + "]";
	}

}
