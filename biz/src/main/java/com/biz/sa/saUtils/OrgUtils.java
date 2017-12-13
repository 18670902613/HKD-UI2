package com.biz.sa.saUtils;

import java.util.ArrayList;
import java.util.List;

import com.biz.sa.entity.Sa_oporg;

public class OrgUtils {
	/**
	 * 根据组织PID获取所有组织
	 */
	public List<String> getOrgByOrgPid(List<Sa_oporg> orgs) {
		List<String> orgIDs = new ArrayList<String>();
		for(Sa_oporg o : orgs) {
			String pID = o.get("pID");
			if(pID.contains("@")) {
				String id = pID.split("@")[1];
				if(!orgIDs.contains(id)) {
					orgIDs.add(id);
				}
			}
		}
		return orgIDs;
	}
}
