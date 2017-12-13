package com.biz.sa.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.biz.sa.entity.SA_opPerson;
import com.jfinal.core.Controller;

public class PersonAction extends Controller {
	/**
	 * 获取组织相关信息
	 * 
	 * @return
	 */
	public void index() {
		renderText("获取人员相关信息");
	}

	/**
	 * 获取人员所在的所有组织
	 * 
	 * @return
	 */
	public void getPersonInfoByPersonID() {
		String pCode = getPara("pCode");
		if(StringUtils.isBlank(pCode)) {
			pCode = "_ANONYMOUS";
		}
		List<SA_opPerson> person = SA_opPerson.dao.find("select * from sa_opperson o where o.pCode = ?", pCode);
		renderJson("person", person.get(0));
	}
}
