package com.xiaour.spring.boot.entity;

import java.util.Date;

public class UserInfo {
    private Integer id;

    private String name;

    private String age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public UserInfo() {
		super();
	}

	public UserInfo(Integer id, String name, String age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	
}