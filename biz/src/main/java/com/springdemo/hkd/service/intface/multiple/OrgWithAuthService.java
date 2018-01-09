package com.springdemo.hkd.service.intface.multiple;

import java.util.List;

import com.springdemo.hkd.dao.bean.base.SaOpauthorize;

public interface OrgWithAuthService {
	public List<SaOpauthorize> findAuthByOrgID(List<String> orgPIDs);
}
