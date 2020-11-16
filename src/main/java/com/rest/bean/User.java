package com.rest.bean;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the user")
//@JsonIgnoreProperties(value = {"id", "birthDate"})
@JsonFilter("filter user")
public class User {
	
//	@JsonIgnore
	private Integer id;
	
	@Size(min = 2, max = 25, message = "name length must be between 2 to 25")
	@ApiModelProperty(name =  "User Name", notes = "name length must be between 2 to 25")
	private String name;
	
	@Past(message = "Date must be past")
	@ApiModelProperty(name = "User Birth Date", dataType = "Date-Time", notes = "Birth date should be in the past")
	private Date birthDate;

	public User() {
		
	}
			
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
		
}
