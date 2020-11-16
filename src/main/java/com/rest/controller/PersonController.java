package com.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.bean.Name;
import com.rest.bean.PersonV1;
import com.rest.bean.PersonV2;

//Example for versioning resful api
@RequestMapping("api")
@RestController
public class PersonController {

	//	URI Versioning
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Ankit Chadokar");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Ankit", "Chadokar"));
	}
	
	// Request Parameter versioning
	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Ankit Chadokar");
	}
	
	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Ankit", "Chadokar"));
	}
	
	// (Custom) Headers versioning
	@GetMapping(value = "person/header", headers = "API-VERSION=1")
	public PersonV1 haeaderV1() {
		return new PersonV1("Ankit Chadokar");
	}
	
	@GetMapping(value = "person/header", headers = "API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Ankit", "Chadokar"));
	}
	
	// Media type versioning (a.k.a “content negotiation” or “accept header”)
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Ankit Chadokar");
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Ankit", "Chadokar"));
	}
	
}
