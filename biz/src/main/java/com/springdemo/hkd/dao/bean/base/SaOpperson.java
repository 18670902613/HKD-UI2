package com.springdemo.hkd.dao.bean.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "SaOpperson.findByCode", query = "select u from SaOpperson u where u.pid=?")
public class SaOpperson {
	@Id
	private String pid;
	@Column(name = "pname")
	private String pname;//名称
	@Column(name = "pcode")
	private String pcode;//代码
	@Column(name = "pnum")
	private Integer pnum;//数字编码
	@Column(name = "ploginname")
	private String ploginname;//>登录名、账户名
	@Column(name = "penglishname")
	private String penglishname;//英文名称
	@Column(name = "ppassword")
	private String ppassword;//密码(md5)
	@Column(name = "pmainorgid")
	private String pmainorgid;//所属部门
	@Column(name = "pdescription")
	private String pdescription;//描述、备注
	@Column(name = "pbirthday")
	private Date pbirthday;//出生日期
	@Column(name = "pfamilyaddress")
	private String pfamilyaddress;//家庭住址
	@Column(name = "pzip")	
	private String pzip;//邮编
	@Column(name = "pmsn")
	private String pmsn;//msn
	@Column(name = "pqq")
	private String pqq;//qq
	@Column(name = "pmail")
	private String pmail;//电子邮件
	@Column(name = "pmobilephone")
	private String pmobilephone;//移动电话
	@Column(name = "pfamilyphone")
	private String pfamilyphone;//家庭电话
	@Column(name = "pofficephone")
	private String pofficephone;//办公电话
	@Column(name = "pidcard")
	private String pidcard;//身份证号
	@Column(name = "plastmodifytime")
	private Date plastmodifytime;//最后修改时间
	@Column(name = "psequence")
	private String psequence;//序号、排序
	@Column(name = "pvalidstate")
	private Integer pvalidstate;//可用状态：1、可用；0、不可用
	@Column(name = "version")
	private Integer version;//版本
	@Column(name = "pphoto")
	private String pphoto;//照片
	@Column(name = "psex")
	private String psex;//性别
	@Column(name = "ppasswordtimelimit")
	private Date ppasswordtimelimit;
	@Column(name = "pphotolastmodified")
	private Date pphotolastmodified;//图片修改时间
	@Column(name = "paccounttype")
	private String paccounttype;//账户类型
	@Column(name = "pleaveschool")
	private Integer pleaveschool;//离校时间
	@Column(name = "pwxh")
	private String pwxh;//微信号
	@Column(name = "pbusinessvalidstate")
	private String pbusinessvalidstate;
	@Column(name = "pxywpassword")
	private String pxywpassword;//校园网密码
	@Column(name = "ppasswordsec")
	private String ppasswordsec;//加密密码
	@Column(name = "ppersontype")
	private String ppersontype;//人员类型
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	public String getPloginname() {
		return ploginname;
	}
	public void setPloginname(String ploginname) {
		this.ploginname = ploginname;
	}
	public String getPenglishname() {
		return penglishname;
	}
	public void setPenglishname(String penglishname) {
		this.penglishname = penglishname;
	}
	public String getPpassword() {
		return ppassword;
	}
	public void setPpassword(String ppassword) {
		this.ppassword = ppassword;
	}
	public String getPmainorgid() {
		return pmainorgid;
	}
	public void setPmainorgid(String pmainorgid) {
		this.pmainorgid = pmainorgid;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public Date getPbirthday() {
		return pbirthday;
	}
	public void setPbirthday(Date pbirthday) {
		this.pbirthday = pbirthday;
	}
	public String getPfamilyaddress() {
		return pfamilyaddress;
	}
	public void setPfamilyaddress(String pfamilyaddress) {
		this.pfamilyaddress = pfamilyaddress;
	}
	public String getPzip() {
		return pzip;
	}
	public void setPzip(String pzip) {
		this.pzip = pzip;
	}
	public String getPmsn() {
		return pmsn;
	}
	public void setPmsn(String pmsn) {
		this.pmsn = pmsn;
	}
	public String getPqq() {
		return pqq;
	}
	public void setPqq(String pqq) {
		this.pqq = pqq;
	}
	public String getPmail() {
		return pmail;
	}
	public void setPmail(String pmail) {
		this.pmail = pmail;
	}
	public String getPmobilephone() {
		return pmobilephone;
	}
	public void setPmobilephone(String pmobilephone) {
		this.pmobilephone = pmobilephone;
	}
	public String getPfamilyphone() {
		return pfamilyphone;
	}
	public void setPfamilyphone(String pfamilyphone) {
		this.pfamilyphone = pfamilyphone;
	}
	public String getPofficephone() {
		return pofficephone;
	}
	public void setPofficephone(String pofficephone) {
		this.pofficephone = pofficephone;
	}
	public String getPidcard() {
		return pidcard;
	}
	public void setPidcard(String pidcard) {
		this.pidcard = pidcard;
	}
	public Date getPlastmodifytime() {
		return plastmodifytime;
	}
	public void setPlastmodifytime(Date plastmodifytime) {
		this.plastmodifytime = plastmodifytime;
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
	public String getPphoto() {
		return pphoto;
	}
	public void setPphoto(String pphoto) {
		this.pphoto = pphoto;
	}
	public String getPsex() {
		return psex;
	}
	public void setPsex(String psex) {
		this.psex = psex;
	}
	public Date getPpasswordtimelimit() {
		return ppasswordtimelimit;
	}
	public void setPpasswordtimelimit(Date ppasswordtimelimit) {
		this.ppasswordtimelimit = ppasswordtimelimit;
	}
	public Date getPphotolastmodified() {
		return pphotolastmodified;
	}
	public void setPphotolastmodified(Date pphotolastmodified) {
		this.pphotolastmodified = pphotolastmodified;
	}
	public String getPaccounttype() {
		return paccounttype;
	}
	public void setPaccounttype(String paccounttype) {
		this.paccounttype = paccounttype;
	}
	public Integer getPleaveschool() {
		return pleaveschool;
	}
	public void setPleaveschool(Integer pleaveschool) {
		this.pleaveschool = pleaveschool;
	}
	public String getPwxh() {
		return pwxh;
	}
	public void setPwxh(String pwxh) {
		this.pwxh = pwxh;
	}
	public String getPbusinessvalidstate() {
		return pbusinessvalidstate;
	}
	public void setPbusinessvalidstate(String pbusinessvalidstate) {
		this.pbusinessvalidstate = pbusinessvalidstate;
	}
	public String getPxywpassword() {
		return pxywpassword;
	}
	public void setPxywpassword(String pxywpassword) {
		this.pxywpassword = pxywpassword;
	}
	public String getPpasswordsec() {
		return ppasswordsec;
	}
	public void setPpasswordsec(String ppasswordsec) {
		this.ppasswordsec = ppasswordsec;
	}
	public String getPpersontype() {
		return ppersontype;
	}
	public void setPpersontype(String ppersontype) {
		this.ppersontype = ppersontype;
	}
}
 