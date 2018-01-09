package com.springdemo.hkd.dao.bean.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "SaOporg.findBypID", query = "select u from SaOporg u where u.pid=?")
public class SaOporg {
	@Id
	private String pid;//
	@Column(name = "pfid")
	private String pfid;// 全路径标识
	@Column(name = "pname")
	private String pname;// 名称
	@Column(name = "pfname")
	private String pfname;// 全路径名称
	@Column(name = "plongname")
	private String plongname;// 全名、全称
	@Column(name = "pcode")
	private String pcode;// 代码
	@Column(name = "pfcode")
	private String pfcode;// 全路径代码
	@Column(name = "pkind")
	private String pkind;// 类别
	@Column(name = "pparent")
	private String pparent;// 父节点
	@Column(name = "plevel")
	private Integer plevel;// 级别
	@Column(name = "pphone")
	private String pphone;// 电话
	@Column(name = "pfax")
	private String pfax;// 传真
	@Column(name = "paddress")
	private String paddress;// 地址
	@Column(name = "pzip")
	private String pzip;// 邮编
	@Column(name = "pdescription")
	private String pdescription;// 描述、备注
	@Column(name = "ppersonid")
	private String ppersonid;// 人员id
	@Column(name = "pnodekind")
	private String pnodekind;// 节点类型
	@Column(name = "psequence")
	private String psequence;// 序号、排序
	@Column(name = "pvalidstate")
	private Integer pvalidstate;// 可用状态：1、可用；0、不可用
	@Column(name = "version")
	private Integer version;// 版本
	@Column(name = "pchildren")
	private String pchildren;// 子节点
	@Column(name = "preal")
	private Integer preal;// 实体标记，1表示是实际存在的组织，反之0
	@Column(name = "peducational")
	private Integer peducational;// 教学组织标记，1表示是教学组织，反之0
	@Column(name = "pfixed")
	private Integer pfixed;// 固定标记，1表示固定不能进行任何操作，反之0
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPfid() {
		return pfid;
	}
	public void setPfid(String pfid) {
		this.pfid = pfid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPfname() {
		return pfname;
	}
	public void setPfname(String pfname) {
		this.pfname = pfname;
	}
	public String getPlongname() {
		return plongname;
	}
	public void setPlongname(String plongname) {
		this.plongname = plongname;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPfcode() {
		return pfcode;
	}
	public void setPfcode(String pfcode) {
		this.pfcode = pfcode;
	}
	public String getPkind() {
		return pkind;
	}
	public void setPkind(String pkind) {
		this.pkind = pkind;
	}
	public String getPparent() {
		return pparent;
	}
	public void setPparent(String pparent) {
		this.pparent = pparent;
	}
	public Integer getPlevel() {
		return plevel;
	}
	public void setPlevel(Integer plevel) {
		this.plevel = plevel;
	}
	public String getPphone() {
		return pphone;
	}
	public void setPphone(String pphone) {
		this.pphone = pphone;
	}
	public String getPfax() {
		return pfax;
	}
	public void setPfax(String pfax) {
		this.pfax = pfax;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public String getPzip() {
		return pzip;
	}
	public void setPzip(String pzip) {
		this.pzip = pzip;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public String getPpersonid() {
		return ppersonid;
	}
	public void setPpersonid(String ppersonid) {
		this.ppersonid = ppersonid;
	}
	public String getPnodekind() {
		return pnodekind;
	}
	public void setPnodekind(String pnodekind) {
		this.pnodekind = pnodekind;
	}
	public String getPsequence() {
		return psequence;
	}
	public void setPsequence(String psequence) {
		this.psequence = psequence;
	}
	public Integer getPvalidstate() {
		return pvalidstate;
	}
	public void setPvalidstate(Integer pvalidstate) {
		this.pvalidstate = pvalidstate;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getPchildren() {
		return pchildren;
	}
	public void setPchildren(String pchildren) {
		this.pchildren = pchildren;
	}
	public Integer getPreal() {
		return preal;
	}
	public void setPreal(Integer preal) {
		this.preal = preal;
	}
	public Integer getPeducational() {
		return peducational;
	}
	public void setPeducational(Integer peducational) {
		this.peducational = peducational;
	}
	public Integer getPfixed() {
		return pfixed;
	}
	public void setPfixed(Integer pfixed) {
		this.pfixed = pfixed;
	}
	
}
