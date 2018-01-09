package com.springdemo.hkd.dao.bean.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "SpAppservice.findByPID", query = "select u from SpAppservice u where u.pid=?")
public class SpAppservice { 
	@Id
	private String pid;
	@Column(name = "pdescription")
	private String pdescription;//服务描述
	@Column(name = "pvalidstate")
	private Integer pvalidstate;//发布状态：1、发布；0、未发布
	@Column(name = "pdistributetime")
	private Date pdistributetime;//发布时间
	@Column(name = "pmainpagetempl")
	private String pmainpagetempl;//服务主页面模板
	@Column(name = "pnavmenu")
	private String pnavmenu;//服务导航菜单
	@Column(name = "pkind")
	private String pkind;//服务类别：五类
	@Column(name = "precommendlevel")
	private Integer precommendlevel;//推荐级别
	@Column(name = "pcreatetime")
	private Date pcreatetime;//创建时间、操作时间
	@Column(name = "plastmodifytime")
	private Date plastmodifytime;//最后修改时间
	@Column(name = "pcreatorid")
	private String pcreatorid;//创建者id
	@Column(name = "pcreatorname")
	private String pcreatorname;//提创建者名称
	@Column(name = "picon")
	private String picon;//服务图标
	@Column(name = "pname")
	private String pname;//名称
	@Column(name = "ppf")
	private Double ppf;//评分
	@Column(name = "version")
	private Integer version;//版本
	@Column(name = "pcreatordeptid")
	private String pcreatordeptid;//创建者部门id
	@Column(name = "pcreatordeptname")
	private String pcreatordeptname;//创建者部门
	@Column(name = "pguide")
	private String pguide;//流程和指南
	@Column(name = "pregulation")
	private String pregulation;//规章制度
	@Column(name = "pvisitcount")
	private Integer pvisitcount;//访问次数
	@Column(name = "pcatalog")
	private String pcatalog;//分类：pc或者mobile
	@Column(name = "paddress")
	private String paddress;
	@Column(name = "pphone")
	private String pphone;
	@Column(name = "pauthorizerdeptname")
	private String pauthorizerdeptname;
	@Column(name = "pauthorizerdeptid")
	private String pauthorizerdeptid;
	@Column(name = "pcode")
	private String pcode;//服务类别代码
	@Column(name = "plongname")
	private String plongname;
	@Column(name = "pflag")
	private String pflag;
	@Column(name = "psequence")
	private Integer psequence;
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public Integer getPvalidstate() {
		return pvalidstate;
	}
	public void setPvalidstate(Integer pvalidstate) {
		this.pvalidstate = pvalidstate;
	}
	public Date getPdistributetime() {
		return pdistributetime;
	}
	public void setPdistributetime(Date pdistributetime) {
		this.pdistributetime = pdistributetime;
	}
	public String getPmainpagetempl() {
		return pmainpagetempl;
	}
	public void setPmainpagetempl(String pmainpagetempl) {
		this.pmainpagetempl = pmainpagetempl;
	}
	public String getPnavmenu() {
		return pnavmenu;
	}
	public void setPnavmenu(String pnavmenu) {
		this.pnavmenu = pnavmenu;
	}
	public String getPkind() {
		return pkind;
	}
	public void setPkind(String pkind) {
		this.pkind = pkind;
	}
	public Integer getPrecommendlevel() {
		return precommendlevel;
	}
	public void setPrecommendlevel(Integer precommendlevel) {
		this.precommendlevel = precommendlevel;
	}
	public Date getPcreatetime() {
		return pcreatetime;
	}
	public void setPcreatetime(Date pcreatetime) {
		this.pcreatetime = pcreatetime;
	}
	public Date getPlastmodifytime() {
		return plastmodifytime;
	}
	public void setPlastmodifytime(Date plastmodifytime) {
		this.plastmodifytime = plastmodifytime;
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
	public String getPicon() {
		return picon;
	}
	public void setPicon(String picon) {
		this.picon = picon;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getPpf() {
		return ppf;
	}
	public void setPpf(Double ppf) {
		this.ppf = ppf;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getPcreatordeptid() {
		return pcreatordeptid;
	}
	public void setPcreatordeptid(String pcreatordeptid) {
		this.pcreatordeptid = pcreatordeptid;
	}
	public String getPcreatordeptname() {
		return pcreatordeptname;
	}
	public void setPcreatordeptname(String pcreatordeptname) {
		this.pcreatordeptname = pcreatordeptname;
	}
	public String getPguide() {
		return pguide;
	}
	public void setPguide(String pguide) {
		this.pguide = pguide;
	}
	public String getPregulation() {
		return pregulation;
	}
	public void setPregulation(String pregulation) {
		this.pregulation = pregulation;
	}
	public Integer getPvisitcount() {
		return pvisitcount;
	}
	public void setPvisitcount(Integer pvisitcount) {
		this.pvisitcount = pvisitcount;
	}
	public String getPcatalog() {
		return pcatalog;
	}
	public void setPcatalog(String pcatalog) {
		this.pcatalog = pcatalog;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public String getPphone() {
		return pphone;
	}
	public void setPphone(String pphone) {
		this.pphone = pphone;
	}
	public String getPauthorizerdeptname() {
		return pauthorizerdeptname;
	}
	public void setPauthorizerdeptname(String pauthorizerdeptname) {
		this.pauthorizerdeptname = pauthorizerdeptname;
	}
	public String getPauthorizerdeptid() {
		return pauthorizerdeptid;
	}
	public void setPauthorizerdeptid(String pauthorizerdeptid) {
		this.pauthorizerdeptid = pauthorizerdeptid;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPlongname() {
		return plongname;
	}
	public void setPlongname(String plongname) {
		this.plongname = plongname;
	}
	public String getPflag() {
		return pflag;
	}
	public void setPflag(String pflag) {
		this.pflag = pflag;
	}
	public Integer getPsequence() {
		return psequence;
	}
	public void setPsequence(Integer psequence) {
		this.psequence = psequence;
	}
}