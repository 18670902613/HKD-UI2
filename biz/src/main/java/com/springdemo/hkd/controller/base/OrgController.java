package com.springdemo.hkd.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.hkd.dao.bean.base.SaOporg;
import com.springdemo.hkd.service.intface.base.OrgService;

@RestController
@RequestMapping(value = "/biz")
public class OrgController {
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value = "/org/findOrgByPersonCode")
	public List<SaOporg> findOrgByPersonCode(@Param(value = "pCode")String pCode) {
		return orgService.findOrgByPersonCode(pCode);
	}
	
}
