package org.holbreich.web.ums;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private long id;
	
	@JsonProperty("login")
	private String login;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("password2")
	private String password2;
	
	@JsonProperty("email")
	private String email;
	
	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	

}
