package com.biz.portal.action;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.biz.sa.entity.Sa_opauthorize;
import com.biz.sa.entity.Sa_oporg;
import com.biz.sa.saUtils.OrgUtils;
import com.jfinal.core.Controller;

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
	public void getServicesBypCode() {
		String pCode = getPara("pCode");
		// 获取人员所在的所有组织，根据角色查询出所有服务
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
		renderJson("roles", roles);
	}
}
