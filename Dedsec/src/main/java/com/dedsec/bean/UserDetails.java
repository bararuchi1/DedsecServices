package com.dedsec.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_details_master_table")
public class UserDetails {
	@Id
	private String userId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "password")
	private String userPassword;

	@Column(name = "userType")
	private String userRole;

	@Column(name = "userFullName")
	private String userFullName;

	@Transient
	private String loginStatus;
	@Transient
	private String loginStatusCode;
	@Transient
	private String loginStatusMessage;

	public String getLoginStatusMessage() {
		return loginStatusMessage;
	}

	public void setLoginStatusMessage(String loginStatusMessage) {
		this.loginStatusMessage = loginStatusMessage;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getLoginStatusCode() {
		return loginStatusCode;
	}

	public void setLoginStatusCode(String loginStatusCode) {
		this.loginStatusCode = loginStatusCode;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userRole=" + userRole + "]";
	}

}
