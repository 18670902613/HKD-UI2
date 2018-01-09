package com.springdemo.hkd.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.springdemo.hkd.dao.bean.base.SaOpperson;
import com.springdemo.hkd.dao.beansOperInterface.base.PersonJPAOper;
import com.springdemo.hkd.service.intface.base.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonJPAOper personJPAOper;

	public List<SaOpperson> findAllWithLimit(int limit, int offset) {
		return personJPAOper.findAllWithLimit(limit, offset);
	}

	public void savePerson(SaOpperson person) {
		personJPAOper.save(person);
	}

	public void delete(String pCode) {
		personJPAOper.delete(pCode);

	}

	public List<SaOpperson> findByDept(String deptCode) {
		return personJPAOper.findByDept(deptCode);
	}

	@Cacheable("persons")
	public List<SaOpperson> findByName(String name) {
		return personJPAOper.findByName(name);
	}

	@Cacheable("persons")
	public List<SaOpperson> findByCode(String code) {
		return personJPAOper.findByCode(code);
	}

	public SaOpperson addUser(SaOpperson person) {
		personJPAOper.save(person);
		return person;
	}
}
