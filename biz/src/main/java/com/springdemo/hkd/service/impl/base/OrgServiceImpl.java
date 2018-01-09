package com.springdemo.hkd.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.hkd.dao.bean.base.SaOporg;
import com.springdemo.hkd.dao.beansOperInterface.base.OrgJPAOper;
import com.springdemo.hkd.service.intface.base.OrgService;

@Service
public class OrgServiceImpl implements OrgService{

	@Autowired
	private OrgJPAOper orgJPAOper;
	
	@Override
	public List<SaOporg> findOrgByPersonCode(String pCode) {
		return orgJPAOper.findOrgByPersonCode(pCode);
	}

}
