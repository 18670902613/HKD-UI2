package com.springdemo.hkd.dao.bean.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "SpAppservicestore.findByPID", query = "select u from SpAppservicestore u where u.pid=?")
public class SpAppservicestore {
	@Id
	private String pappserviceid;// 应用服务id
	@Column(name = "pcreatorid")
	private String pcreatorid;// 操作者id
	@Column(name = "pcreatorname")
	private String pcreatorname;// 操作者姓名
	@Column(name = "pcreatetime")
	private Date pcreatetime;// 操作时间
	@Column(name = "pid")
	private String pid;
	@Column(name = "pkind")
	private String pkind;// 身份类别
	@Column(name = "xsccdm")
	private String xsccdm;// 学生层次代码
	@Column(name = "porgid")
	private String porgid;// 所属机构名称
	@Column(name = "pdeptname")
	private String pdeptname;// 所属部门院系名称
	@Column(name = "pname")
	private String pname;// 服务简称

	public String getPappserviceid() {
		return pappserviceid;
	}

	public void setPappserviceid(String pappserviceid) {
		this.pappserviceid = pappserviceid;
	}

	public String getPcreatorid() {
		return pcreatorid;
	}

	public void setPcreatorid(String pcreatorid) {
		this.pcreatorid = pcreatorid;
	}

	public String getPcreatorname() {
		return pcreatorname;
	}

	public void setPcreatorname(String pcreatorname) {
		this.pcreatorname = pcreatorname;
	}

	public Date getPcreatetime() {
		return pcreatetime;
	}

	public void setPcreatetime(Date pcreatetime) {
		this.pcreatetime = pcreatetime;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPkind() {
		return pkind;
	}

	public void setPkind(String pkind) {
		this.pkind = pkind;
	}

	public String getXsccdm() {
		return xsccdm;
	}

	public void setXsccdm(String xsccdm) {
		this.xsccdm = xsccdm;
	}

	public String getPorgid() {
		return porgid;
	}

	public void setPorgid(String porgid) {
		this.porgid = porgid;
	}

	public String getPdeptname() {
		return pdeptname;
	}

	public void setPdeptname(String pdeptname) {
		this.pdeptname = pdeptname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
}
