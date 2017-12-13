package com.biz.portal.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.biz.sa.entity.Sa_opauthorize;
import com.biz.sa.entity.Sa_oporg;
import com.biz.sa.entity.Sp_appservicepermission;
import com.biz.sa.saUtils.OrgUtils;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class ServicesAction extends Controller {
	/**
	 * 获取所有服务
	 * 
	 * @return
	 */
	public JSONObject getAllServices() {
		return null;
	}

	/**
	 * 根据人员pCOde获取服务
	 */
	public JSONObject getServicesBypCode() {
		String pCode = getPara("pCode");
		if(StringUtils.isBlank(pCode)) {
			pCode = "_ANONYMOUS";
		}
		// 获取人员所在的所有组织
		List<Sa_oporg> orgs = Sa_oporg.dao.find("select o.pID from sa_oporg o where o.pFID LIKE '%" + pCode + "%'");
		OrgUtils orgUtils = new OrgUtils();
		List<String> orgPIDs = orgUtils.getOrgByOrgPid(orgs);
		
		//根据组织获取所拥有的角色
		StringBuffer getRoleSql = new StringBuffer();
		getRoleSql.append("SELECT a.pAuthorizeRoleID FROM sa_opauthorize a WHERE a.pOrgID IN (");
		for(int i = 0; i < orgPIDs.size(); i++) {
			if(i != orgPIDs.size() - 1) {
				getRoleSql.append("'" + orgPIDs.get(i) + "', ");
			}else {
				getRoleSql.append("'" + orgPIDs.get(i) + "'");
			}
		}
		getRoleSql.append(")");
		List<Sa_opauthorize> roles = Sa_opauthorize.dao.find(getRoleSql.toString());
		if(roles.size() == 0) {
			renderJson("services", new JSONObject());
			return null;
		}
		//根据角色查询出所有服务
		StringBuffer getServiceSql = new StringBuffer();
		getServiceSql.append("SELECT s.* FROM sp_appservicepermission p LEFT JOIN sp_appservice s ON p.pAppServiceID = s.pID WHERE s.pValidState = '1' AND p.pAuthorizeRoleID IN (");
		for(int i = 0; i < roles.size(); i++) {
			if(i != roles.size() - 1) {
				getServiceSql.append("'" + roles.get(i).getStr("pAuthorizeRoleID") + "', ");
			}else {
				getServiceSql.append("'" + roles.get(i).getStr("pAuthorizeRoleID") + "'");
			}
		}
		getServiceSql.append(")GROUP BY s.pName,s.pID ORDER BY s.pSequence");
		
		String countSql = "select count(*) from (" + getServiceSql.toString() + ") t";
		Page<Sp_appservicepermission> services = (Page<Sp_appservicepermission>) Sp_appservicepermission.dao.paginateByFullSql(1, 10, countSql, getServiceSql.toString());
		renderJson("services", services);
		return null;
	}
}
