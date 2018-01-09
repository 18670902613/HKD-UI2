package com.springdemo.hkd.dao.beansOperInterface.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springdemo.hkd.dao.bean.base.SaOporg;


public interface OrgJPAOper extends JpaRepository<SaOporg, Long>{
	/**
	 * 获取人员所在的所有组织
	 */
	@Query(value = "select * from sa_oporg o where o.pFID LIKE %?1%", nativeQuery = true)
	public List<SaOporg> findOrgByPersonCode(String pCode);
}
