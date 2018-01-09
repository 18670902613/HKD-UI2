package com.springdemo.hkd.dao.bean.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "SaOpauthorize.findByPID", query = "select u from SaOpauthorize u where u.pid=?")
public class SaOpauthorize { 
	@Id
	private String pid;
	@Column(name = "porgid")
	private String porgid;//组织机构
	@Column(name = "porgname")
	private String porgname;//组织名称
	@Column(name = "porgfid")
	private String porgfid;//组织全路径编码
	@Column(name = "porgfname")
	private String porgfname;//组织机构名称
	@Column(name = "pauthorizeroleid")
	private String pauthorizeroleid;//授权角色id
	@Column(name = "pdescription")
	private String pdescription;//描述
	@Column(name = "pcreatorfid")
	private String pcreatorfid;//提交者\\\\创建者全路径id
	@Column(name = "pcreatorfname")
	private String pcreatorfname;//提交者\\\\创建者全路径名称
	@Column(name = "pcreatetime")
	private Date pcreatetime;//创建时间、操作时间
	@Column(name = "version")
	private Integer version;//版本
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPorgid() {
		return porgid;
	}
	public void setPorgid(String porgid) {
		this.porgid = porgid;
	}
	public String getPorgname() {
		return porgname;
	}
	public void setPorgname(String porgname) {
		this.porgname = porgname;
	}
	public String getPorgfid() {
		return porgfid;
	}
	public void setPorgfid(String porgfid) {
		this.porgfid = porgfid;
	}
	public String getPorgfname() {
		return porgfname;
	}
	public void setPorgfname(String porgfname) {
		this.porgfname = porgfname;
	}
	public String getPauthorizeroleid() {
		return pauthorizeroleid;
	}
	public void setPauthorizeroleid(String pauthorizeroleid) {
		this.pauthorizeroleid = pauthorizeroleid;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public String getPcreatorfid() {
		return pcreatorfid;
	}
	public void setPcreatorfid(String pcreatorfid) {
		this.pcreatorfid = pcreatorfid;
	}
	public String getPcreatorfname() {
		return pcreatorfname;
	}
	public void setPcreatorfname(String pcreatorfname) {
		this.pcreatorfname = pcreatorfname;
	}
	public Date getPcreatetime() {
		return pcreatetime;
	}
	public void setPcreatetime(Date pcreatetime) {
		this.pcreatetime = pcreatetime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
 
}
