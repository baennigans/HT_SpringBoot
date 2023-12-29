package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="member")
public class Member {

	@Id
	private String id;
	private String pw;
	private String email;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", email=" + email + ", name=" + name + "]";
	}
}
