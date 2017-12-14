package com.biz.portal.action;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.biz.portal.entity.Sp_appservice;
import com.biz.portal.entity.Sp_appservicestore;
import com.biz.sa.entity.Sa_opauthorize;
import com.biz.sa.entity.Sa_oporg;
import com.biz.sa.entity.Sp_appservicepermission;
import com.biz.sa.saUtils.OrgUtils;
import com.jfinal.core.Controller;
import com.xiaoleilu.hutool.util.StrUtil;

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
	 * 根据人员pCOde获取服务,包含服务过滤
	 */
	public JSONObject getServicesBypCode() {
		String pCode = getPara("pCode");
		String dept = getPara("dept");// 部门ID
		String orderBy = getPara("orderBy");// 排序功能 最新 最热 口碑
		String serviceType = getPara("serviceType");// 按服务分类过滤
		if (StrUtil.isBlank(pCode)) {
			pCode = "_ANONYMOUS";
		}
		// 获取人员所在的所有组织
		List<Sa_oporg> orgs = Sa_oporg.dao.find("select o.pID from sa_oporg o where o.pFID LIKE '%" + pCode + "%'");
		OrgUtils orgUtils = new OrgUtils();
		List<String> orgPIDs = orgUtils.getOrgByOrgPid(orgs);

		// 根据组织获取所拥有的角色
		StringBuffer getRoleSql = new StringBuffer();
		getRoleSql.append("SELECT a.pAuthorizeRoleID FROM sa_opauthorize a WHERE a.pOrgID IN (");
		for (int i = 0; i < orgPIDs.size(); i++) {
			if (i != orgPIDs.size() - 1) {
				getRoleSql.append("'" + orgPIDs.get(i) + "', ");
			} else {
				getRoleSql.append("'" + orgPIDs.get(i) + "'");
			}
		}
		getRoleSql.append(")");
		List<Sa_opauthorize> roles = Sa_opauthorize.dao.find(getRoleSql.toString());
		if (roles.size() == 0) {
			renderJson("services", new JSONObject());
			return null;
		}
		// 根据角色查询出所有服务
		StringBuffer getServiceSql = new StringBuffer();
		getServiceSql.append(
				"SELECT s.* FROM sp_appservicepermission p LEFT JOIN sp_appservice s ON p.pAppServiceID = s.pID WHERE s.pValidState = '1' AND p.pAuthorizeRoleID IN (");
		for (int i = 0; i < roles.size(); i++) {
			if (i != roles.size() - 1) {
				getServiceSql.append("'" + roles.get(i).getStr("pAuthorizeRoleID") + "', ");
			} else {
				getServiceSql.append("'" + roles.get(i).getStr("pAuthorizeRoleID") + "'");
			}
		}
		getServiceSql.append(")");
		// 部门过滤
		if (StrUtil.isNotBlank(dept)) {
			getServiceSql.append("AND s.pCreatorDeptID = '%" + dept + "%' ");
		}
		// 服务类型过滤
		if (StrUtil.isNotBlank(serviceType)) {
			getServiceSql.append("AND s.pType = '%" + serviceType + "%' ");
		}
		getServiceSql.append("GROUP BY s.pName,s.pID ORDER BY s.pSequence");
		if (StrUtil.isNotBlank(orderBy)) {
			getServiceSql.append(", 's." + orderBy + "'");
		}

		String countSql = "select count(*) from (" + getServiceSql.toString() + ") t";
		List<Sp_appservicepermission> services = Sp_appservicepermission.dao.find(getServiceSql.toString());
		renderJson("services", services);
		return null;
	}

	/**
	 * 获取service的创建部门
	 */
	public void getServicesDepartment() {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT s.pCreatorDeptID, s.pCreatorDeptName FROM sp_appservice s WHERE s.pCreatorDeptID IS NOT null GROUP BY s.pCreatorDeptID, s.pCreatorDeptName");
		List<Sp_appservice> departments = Sp_appservice.dao.find(sql.toString());
		renderJson("departments", departments);
	}

	/**
	 * 获取人员收藏的部门
	 */
	public JSONObject getServiceStore() {
		String pCode = getPara("pCode");
		String dept = getPara("dept");// 部门ID
		String orderBy = getPara("orderBy");// 排序功能 最新 最热 口碑
		String serviceType = getPara("serviceType");// 按服务分类过滤
		if (StrUtil.isBlank(pCode)) {
			renderJson("stores", new JSONObject());
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT s.*, sto.pCreateTime AS storeTime, sto.pCreatorID AS storePCode, sto.pCreatorName AS storeName, sto.pKind AS storePersonKind ");
		sql.append("FROM sp_appservicestore sto  ");
		sql.append("LEFT JOIN sp_appservice s ON s.pID = sto.pAppServiceID ");
		sql.append("WHERE sto.pCreatorID = '" + pCode + "' ");
		// 部门过滤
		if (StrUtil.isNotBlank(dept)) {
			sql.append("AND s.pCreatorDeptID = '%" + dept + "%' ");
		}
		// 服务类型过滤
		if (StrUtil.isNotBlank(serviceType)) {
			sql.append("AND s.pType = '%" + serviceType + "%' ");
		}
		sql.append("ORDER BY s.pSequence ");
		if (StrUtil.isNotBlank(orderBy)) {
			sql.append(", 's." + orderBy + "'");
		}
		List<Sp_appservicestore> stores = Sp_appservicestore.dao.find(sql.toString());
		renderJson("stores", stores);
		return null;
	}
	
	/**
	 * 根据服务ID 构建服务菜单
	 */
	public JSONObject getServiceMenu() {
		
		
		
		StringBuffer s = new StringBuffer();
		s.append("{\r\n" + 
				"    \"serviceID\": \"0001\",\r\n" + 
				"    \"serviceName\": \"评奖评优\",\r\n" + 
				"    \"data\": [\r\n" + 
				"    {\r\n" + 
				"        \"icon\": \"android-person-add\",       \r\n" + 
				"        \"text\": \"评奖查询\",\r\n" + 
				"        \"router\": \"\",\r\n" + 
				"        \"leaf\": true,\r\n" + 
				"        \"subMenu\":\r\n" + 
				"        {\r\n" + 
				"            \"data\": [\r\n" + 
				"            {\r\n" + 
				"                \"icon\": \"ios-paper\",\r\n" + 
				"                \"text\": \"获奖名单查询\",\r\n" + 
				"                \"router\": \"page1\",\r\n" + 
				"                \"leaf\": false,\r\n" + 
				"                \"subMenu\":\r\n" + 
				"                {\r\n" + 
				"                    \"data\": []\r\n" + 
				"                }\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"icon\": \"ios-paper\",\r\n" + 
				"                \"text\": \"获奖名单公示查询\",\r\n" + 
				"                \"router\": \"page2\",\r\n" + 
				"                \"leaf\": false,\r\n" + 
				"                \"subMenu\":\r\n" + 
				"                {\r\n" + 
				"                    \"data\": []\r\n" + 
				"                }\r\n" + 
				"            }]\r\n" + 
				"        }\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"icon\": \"trophy\",\r\n" + 
				"        \"text\": \"评奖评优\",\r\n" + 
				"        \"router\": \"\",\r\n" + 
				"        \"leaf\": true,\r\n" + 
				"        \"subMenu\":\r\n" + 
				"        {\r\n" + 
				"            \"data\": [\r\n" + 
				"            {\r\n" + 
				"                \"icon\": \"ios-paper\",\r\n" + 
				"                \"text\": \"我的评奖评优\",\r\n" + 
				"                \"router\": \"page3\",\r\n" + 
				"                \"leaf\": false,\r\n" + 
				"                \"subMenu\":\r\n" + 
				"                {\r\n" + 
				"                    \"data\": []\r\n" + 
				"                }\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"icon\": \"ios-paper\",\r\n" + 
				"                \"text\": \"集体评奖评优\",\r\n" + 
				"                \"router\": \"page4\",\r\n" + 
				"                \"leaf\": false,\r\n" + 
				"                \"subMenu\":\r\n" + 
				"                {\r\n" + 
				"                    \"data\": []\r\n" + 
				"                }\r\n" + 
				"            }]\r\n" + 
				"        }\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"icon\": \"compass\",\r\n" + 
				"        \"text\": \"服务介绍\",\r\n" + 
				"        \"router\": \"page5\",\r\n" + 
				"        \"leaf\": false,\r\n" + 
				"        \"subMenu\":\r\n" + 
				"        {\r\n" + 
				"            \"data\": []\r\n" + 
				"        }\r\n" + 
				"    }]\r\n" + 
				"}");
		renderJson("menu", JSONObject.parse(s.toString()));
		return null;
		
	}
}
