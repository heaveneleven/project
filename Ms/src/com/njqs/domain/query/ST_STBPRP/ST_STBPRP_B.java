package com.njqs.domain.query.ST_STBPRP;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author heaven 测站基本属性表 用于存储测站的基本信息
 */
@Entity
@Table(name = "ST_STBPRP_B")
public class ST_STBPRP_B {
	private int id;
	/** 测站编码 */
	private char stcd;
	/** 测站名称 */
	private char stnm;
	/** 河流名称 */
	private char rvnm;
	/** 水系名称 */
	private char hnnm;
	/** 流域名称 */
	private char bsnm;
	/** 经度 */
	private double lgtd;
	/** 纬度 */
	private double lttd;
	/** 站址 */
	private char stlc;
	/** 行政区划码 */
	private char addvcd;
	/** 基面名称 */
	private char dtmnm;
	/** 基面高程 */
	private double dtmel;
	/** 基面修正值 */
	private double dtpr;
	/** 站类-测站类别见子表ST_STBPRP_STTP */
	private char sttp;
	/** 报讯等级 -等级对应见ST_STBPRP_FRGRD*/
	private char frgrd;
	/**
	 * 建站年月,编码格式：YYYYMM。 其中：YYYY-四位数字，表示年份；MM-二位数字，表示月份，若数值不足两位，前面加0补齐。
	 */
	private char esstym;
	/** 始报年月 */
	private char bgfrym;
	/** 隶属行业单位 */
	private char atcunit;
	/** 信息管理单位 */
	private char admauth;
	/** 交换管理单位 ，见表ST_STBPRP_LOCALITY_DETAIL*/
	private char locality;
	/** 测站岸别 
	 * 描述测站站房位于河流的左岸或右岸的代码，
	 * 取“0”表示观测站房位于河流的左岸，
	 * 取“1”表示测站站房位于河流的右岸，
	 * 若测站并不在河流上，则置为空。
	 * */
	private char stbk;
	/** 测站方位 */
	private char stazt;
	/** 至河口距离 */
	private double dstrvm;
	/** 集水面积 */
	private char drna;
	/** 拼音码 */
	private char phcd;
	/** 启用标志 */
	private char usfl;
	/** 备注 */
	private String comments;
	/** 时间戳 */
	private Date moditime;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable = false, columnDefinition = "CHAR(8)")
	public char getStcd() {
		return stcd;
	}

	public void setStcd(char stcd) {
		this.stcd = stcd;
	}

	@Column(columnDefinition = "CHAR(30)")
	public char getStnm() {
		return stnm;
	}

	public void setStnm(char stnm) {
		this.stnm = stnm;
	}

	@Column(columnDefinition = "CHAR(30)")
	public char getRvnm() {
		return rvnm;
	}

	public void setRvnm(char rvnm) {
		this.rvnm = rvnm;
	}

	@Column(columnDefinition = "CHAR(30)")
	public char getHnnm() {
		return hnnm;
	}

	public void setHnnm(char hnnm) {
		this.hnnm = hnnm;
	}

	@Column(columnDefinition = "CHAR(30)")
	public char getBsnm() {
		return bsnm;
	}

	public void setBsnm(char bsnm) {
		this.bsnm = bsnm;
	}

	@Column(columnDefinition = "DECIMAL(10,6)")
	public double getLgtd() {
		return lgtd;
	}

	public void setLgtd(double lgtd) {
		this.lgtd = lgtd;
	}
	/**为适应百度地图精度舍去原先"DECIMAL(7,3)"*/
	@Column(columnDefinition = "DECIMAL(10,6)")
	public double getLttd() {
		return lttd;
	}

	public void setLttd(double lttd) {
		this.lttd = lttd;
	}

	@Column(columnDefinition = "CHAR(50)")
	public char getStlc() {
		return stlc;
	}

	public void setStlc(char stlc) {
		this.stlc = stlc;
	}

	@Column(columnDefinition = "CHAR(6)")
	public char getAddvcd() {
		return addvcd;
	}

	public void setAddvcd(char addvcd) {
		this.addvcd = addvcd;
	}

	@Column(columnDefinition = "CHAR(16)")
	public char getDtmnm() {
		return dtmnm;
	}

	public void setDtmnm(char dtmnm) {
		this.dtmnm = dtmnm;
	}

	@Column(columnDefinition = "DECIMAL(7,3)")
	public double getDtmel() {
		return dtmel;
	}

	public void setDtmel(double dtmel) {
		this.dtmel = dtmel;
	}

	@Column(columnDefinition = "DECIMAL(7,3)")
	public double getDtpr() {
		return dtpr;
	}

	public void setDtpr(double dtpr) {
		this.dtpr = dtpr;
	}

	@Column(nullable = true, columnDefinition = "CHAR(2)")
	public char getSttp() {
		return sttp;
	}

	public void setSttp(char sttp) {
		this.sttp = sttp;
	}

	@Column(nullable = true)
	public char getFrgrd() {
		return frgrd;
	}

	public void setFrgrd(char frgrd) {
		this.frgrd = frgrd;
	}

	@Column(nullable = true, columnDefinition = "CHAR(6)")
	public char getEsstym() {
		return esstym;
	}

	public void setEsstym(char esstym) {
		this.esstym = esstym;
	}

	@Column(nullable = true, columnDefinition = "CHAR(6)")
	public char getBgfrym() {
		return bgfrym;
	}

	public void setBgfrym(char bgfrym) {
		this.bgfrym = bgfrym;
	}

	@Column(nullable = true, columnDefinition = "CHAR(20)")
	public char getAtcunit() {
		return atcunit;
	}

	public void setAtcunit(char atcunit) {
		this.atcunit = atcunit;
	}

	@Column(nullable = true, columnDefinition = "CHAR(20)")
	public char getAdmauth() {
		return admauth;
	}

	public void setAdmauth(char admauth) {
		this.admauth = admauth;
	}

	@Column(nullable = false, columnDefinition = "CHAR(10)")
	public char getLocality() {
		return locality;
	}

	public void setLocality(char locality) {
		this.locality = locality;
	}

	@Column(nullable = true, columnDefinition = "CHAR(1)")
	public char getStbk() {
		return stbk;
	}

	public void setStbk(char stbk) {
		this.stbk = stbk;
	}

	@Column(nullable = true, columnDefinition = "CHAR(1)")
	public char getStazt() {
		return stazt;
	}

	public void setStazt(char stazt) {
		this.stazt = stazt;
	}

	@Column(nullable = true, columnDefinition = "DECIMAL(6,1)")
	public double getDstrvm() {
		return dstrvm;
	}

	public void setDstrvm(double dstrvm) {
		this.dstrvm = dstrvm;
	}

	@Column(nullable = true, columnDefinition = "CHAR(7)")
	public char getDrna() {
		return drna;
	}

	public void setDrna(char drna) {
		this.drna = drna;
	}

	@Column(nullable = true, columnDefinition = "CHAR(6)")
	public char getPhcd() {
		return phcd;
	}

	public void setPhcd(char phcd) {
		this.phcd = phcd;
	}

	@Column(nullable = true, columnDefinition = "CHAR(1)")
	public char getUsfl() {
		return usfl;
	}

	public void setUsfl(char usfl) {
		this.usfl = usfl;
	}

	@Column(nullable = true, columnDefinition = "VARCHAR(200)")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(nullable = true, columnDefinition = "DATETIME")
	public Date getModitime() {
		return moditime;
	}

	public void setModitime(Date moditime) {
		this.moditime = moditime;
	}
}
