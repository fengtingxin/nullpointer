package com.exp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "tb_goods")
public class TbGoods {

	@Id
	private Integer goodId;
	private String goodsName;
	private String introduce;

	private Double importPrice;
	private Double nowPrice;
	private String picture;
	@Column(name = "ImportDate")
	private Timestamp importDate;
	private Integer newGoods;
	private Integer number;
	private Integer hit;
	private String remarks;

	// Constructors

	/** default constructor */
	public TbGoods() {
	}

	/** minimal constructor */
	public TbGoods(String goodsName, Double nowPrice) {
		this.goodsName = goodsName;
		this.nowPrice = nowPrice;
	}

	/** full constructor */
	public TbGoods(String goodsName, String introduce, Double importPrice, Double nowPrice, String picture,
			Timestamp importDate, Integer newGoods, Integer number, Integer hit, String remarks) {
		this.goodsName = goodsName;
		this.introduce = introduce;
		this.importPrice = importPrice;
		this.nowPrice = nowPrice;
		this.picture = picture;
		this.importDate = importDate;
		this.newGoods = newGoods;
		this.number = number;
		this.hit = hit;
		this.remarks = remarks;

	}

	// Property accessors

	public Integer getGoodId() {
		return this.goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Double getImportPrice() {
		return this.importPrice;
	}

	public void setImportPrice(Double importPrice) {
		this.importPrice = importPrice;
	}

	public Double getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(Double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getImportDate() {
		return this.importDate;
	}

	public void setImportDate(Timestamp importDate) {
		this.importDate = importDate;
	}

	public Integer getNewGoods() {
		return this.newGoods;
	}

	public void setNewGoods(Integer newGoods) {
		this.newGoods = newGoods;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getHit() {
		return this.hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}