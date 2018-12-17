package com.sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	private Integer stockId;
	
	@Column(name="stock_code")
	private String stockCode;
	
	private String stockName;

	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID", nullable = false)
	private List<StockDetail> details = new ArrayList<StockDetail>();


	@OneToOne ( cascade = {CascadeType.ALL}) 
	@JoinColumn(name="client_id")
	private Client client;
	
	
	
	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", stockCode=" + stockCode
				+ ", stockName=" + stockName + "]";
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<StockDetail> getDetails() {
		return details;
	}

	public void setDetails(List<StockDetail> details) {
		this.details = details;
	}

	public Integer getStockId() {
		return this.stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	@Column(name = "STOCK_CODE", unique = true, nullable = false, length = 10)
	public String getStockCode() {
		return this.stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	@Column(name = "STOCK_NAME", unique = true, nullable = false, length = 20)
	public String getStockName() {
		return this.stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}