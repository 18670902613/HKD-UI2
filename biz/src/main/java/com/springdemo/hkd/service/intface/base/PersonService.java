package com.springdemo.hkd.service.intface.base;

import java.util.List;

import com.springdemo.hkd.dao.bean.base.SaOpperson;

public interface PersonService {
    public void savePerson(SaOpperson person);

    public void delete(String pCode);
    
    public List<SaOpperson> findByDept(String deptCode);

	List<SaOpperson> findAllWithLimit(int limit, int offset);
	
	public List<SaOpperson> findByName(String name);
	
	public List<SaOpperson> findByCode(String code);

}
