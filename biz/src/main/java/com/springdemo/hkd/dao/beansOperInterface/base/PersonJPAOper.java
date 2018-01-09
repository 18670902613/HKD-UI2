package com.springdemo.hkd.dao.beansOperInterface.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springdemo.hkd.dao.bean.base.SaOpperson;

public interface PersonJPAOper extends JpaRepository<SaOpperson, Long> {
	/**
	 * 用户名模糊查询
	 */
	@Query(value = "select * from sa_opperson p limit ?1 offset ?2", nativeQuery = true)
	public List<SaOpperson> findAllWithLimit(Integer limit, Integer offset);

	/**
	 * 用户名模糊查询
	 */
	@Query(value = "select * from sa_opperson p where p.pname like %?1%", nativeQuery = true)
	public List<SaOpperson> findByName(String name);

	/**
	 * pCode查询
	 */
	@Query(value = "select * from sa_opperson p where p.pcode = ?1", nativeQuery = true)
	public List<SaOpperson> findByCode(String pCode);

	/**
	 * pCode删除
	 */
	@Query(value = "DELETE FROM sa_opperson where pCode = ?", nativeQuery = true)
	public void delete(String pCode);

	/**
	 * dept查询
	 */
	@Query(value = "select * from sa_opperson p where p.pMainOrgId = ?", nativeQuery = true)
	public List<SaOpperson> findByDept(String deptCode);
}
