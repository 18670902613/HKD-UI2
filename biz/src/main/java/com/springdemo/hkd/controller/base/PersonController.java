package com.springdemo.hkd.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.hkd.dao.bean.base.SaOpperson;
import com.springdemo.hkd.service.intface.base.PersonService;

@RestController
@RequestMapping(value = "/biz")
public class PersonController {
	@Autowired
	private PersonService PersonService;
	
	@RequestMapping(value = "/person/findAll")
	public List<SaOpperson> findAll(@Param(value = "limit") int limit, @Param(value = "offset") int offset) {
		return PersonService.findAllWithLimit(limit, offset);
	}

	@RequestMapping(value = "/person/add")
	public SaOpperson addUser(@Param(value = "person") SaOpperson person) {
		PersonService.savePerson(person);
		return person;
	}

	@RequestMapping(value = "/person/delete")
	public void deletePerson(@Param(value = "pCode") String pCode) {
		PersonService.delete(pCode);
	}
	
	@RequestMapping(value = "/person/findByName")
	public List<SaOpperson> findByName(@Param(value = "pName") String pName) {
		return PersonService.findByName(pName);
	}
	
	@RequestMapping(value = "/person/findByCode")
	public List<SaOpperson> findByCode(@Param(value = "pCode") String pCode) {
		return PersonService.findByCode(pCode);
	}
}
