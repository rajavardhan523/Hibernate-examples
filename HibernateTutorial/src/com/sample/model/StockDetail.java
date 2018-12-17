package com.sample.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_detail")
public class StockDetail implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STOCK_Detail_ID", unique = true, nullable = false)
	private Integer stockDetailId;
	private String compName;
	private String compDesc;
	private String remark;
	private Date listedDate;


	public Integer getStockDetailId() {
		return stockDetailId;
	}

	public void setStockDetailId(Integer stockDetailId) {
		this.stockDetailId = stockDetailId;
	}


	@Column(name = "COMP_NAME", nullable = false, length = 100)
	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	@Column(name = "COMP_DESC", nullable = false)
	public String getCompDesc() {
		return this.compDesc;
	}

	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}

	@Column(name = "REMARK", nullable = false)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "LISTED_DATE", nullable = false, length = 10)
	public Date getListedDate() {
		return this.listedDate;
	}

	public void setListedDate(Date listedDate) {
		this.listedDate = listedDate;
	}

}