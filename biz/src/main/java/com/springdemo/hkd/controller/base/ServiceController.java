package com.springdemo.hkd.controller.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springdemo.hkd.dao.bean.base.SaOpauthorize;
import com.springdemo.hkd.dao.bean.base.SaOporg;
import com.springdemo.hkd.service.intface.base.OrgService;
import com.springdemo.hkd.service.intface.multiple.OrgWithAuthService;
import com.springdemo.hkd.utils.OrgUtils;

@RestController
@RequestMapping(value = "/biz")
public class ServiceController {

	@Autowired
	private OrgService orgService;
	@Autowired
	private OrgWithAuthService orgWithAuthService;
	@Autowired
	protected EntityManager em;

	/**
	 * 根据人员pCOde获取服务
	 */
	@RequestMapping(value = "/service/getServicesBypCode")
	public JSONObject getServicesBypCode(@Param(value = "pCode") String pCode) {
		JSONObject res = new JSONObject();
		
		List<SaOporg> orgList = orgService.findOrgByPersonCode(pCode);
		List<String> orgPIDs = OrgUtils.getOrgByOrgPid(orgList);
		
		List<SaOpauthorize> authList = orgWithAuthService.findAuthByOrgID(orgPIDs);
		
		if (authList.size() == 0) {
			return res;
		}
		//角色列表不为空，查询出所有服务
		StringBuffer getServiceSql = new StringBuffer();
		getServiceSql.append(
				"SELECT s.* FROM sp_appservicepermission p LEFT JOIN sp_appservice s ON p.pAppServiceID = s.pID WHERE s.pValidState = '1' AND p.pAuthorizeRoleID IN (");
		for (int i = 0; i < authList.size(); i++) {
			SaOpauthorize authItem = authList.get(i);
			if (i != authList.size() - 1) {
				getServiceSql.append("'" + authItem.getPauthorizeroleid() + "', ");
			} else {
				getServiceSql.append("'" + authItem.getPauthorizeroleid() + "'");
			}
		}
		getServiceSql.append(")");
		
		String jsonStr = JSON.toJSONString(authList);
		JSONArray j = JSON.parseArray(jsonStr);
		Query nativeQuery = em.createNativeQuery(getServiceSql.toString());
		@SuppressWarnings("unchecked")
		List<SaOpauthorize> services = nativeQuery.getResultList();
		res.put("services", services);
		return res;
	}
}
