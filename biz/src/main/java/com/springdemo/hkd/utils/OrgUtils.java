package com.springdemo.hkd.utils;

import java.util.ArrayList;
import java.util.List;

import com.springdemo.hkd.dao.bean.base.SaOporg;


public class OrgUtils {
	/**
	 * 根据组织PID获取所有组织
	 */
	public static List<String> getOrgByOrgPid(List<SaOporg> orgs) {
		List<String> orgIDs = new ArrayList<String>();
		for(SaOporg o : orgs) {
			String pID = o.getPid();
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
