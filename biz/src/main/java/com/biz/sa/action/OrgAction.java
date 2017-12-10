package com.biz.sa.action;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.biz.sa.entity.Sa_oporg;
import com.jfinal.core.Controller;

public class OrgAction extends Controller {
	/**
	 * 获取组织相关信息
	 * 
	 * @return
	 */
	public void index() {
		renderText("获取组织相关信息");
	}
	
	/**
	 * 获取人员所在的所有组织
	 * 
	 * @return
	 */
	public void getOrgByPersonID() {
		String pCode = getPara("pCode");
		List<Sa_oporg> orgs = Sa_oporg.dao.find("select * from sa_oporg o where o.pCode = ?", pCode);
		renderJson("orgs", orgs);
	}
}
