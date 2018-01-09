package com.springdemo.hkd.service.impl.multiple;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.hkd.dao.bean.base.SaOpauthorize;
import com.springdemo.hkd.service.intface.multiple.OrgWithAuthService;

@Service
public class OrgWithAuthServiceImpl implements OrgWithAuthService {
	@Autowired
	protected EntityManager em;

	@Override
	public List<SaOpauthorize> findAuthByOrgID(List<String> orgPIDs) {
		// 根据组织获取所拥有的角色
		StringBuffer getRoleSql = new StringBuffer();
		getRoleSql.append("SELECT a.* FROM sa_opauthorize a WHERE a.pOrgID IN (");
		for (int i = 0; i < orgPIDs.size(); i++) {
			if (i != orgPIDs.size() - 1) {
				getRoleSql.append("'" + orgPIDs.get(i) + "', ");
			} else {
				getRoleSql.append("'" + orgPIDs.get(i) + "'");
			}
		}
		getRoleSql.append(")");
		Query nativeQuery = em.createNativeQuery(getRoleSql.toString(), SaOpauthorize.class);
		@SuppressWarnings("unchecked")
		List<SaOpauthorize> authList = nativeQuery.getResultList();
		return authList;
	}
}
