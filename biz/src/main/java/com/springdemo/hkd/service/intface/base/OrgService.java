package com.springdemo.hkd.service.intface.base;

import java.util.List;

import com.springdemo.hkd.dao.bean.base.SaOporg;


public interface OrgService {
	public List<SaOporg> findOrgByPersonCode(String pCode);
}
