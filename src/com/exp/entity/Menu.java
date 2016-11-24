package com.exp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu {

	private Integer MenuId;
	private String menuName;//菜单的名字
	private String menuUrl;//菜单的url
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getMenuId() {
		return MenuId;
	}
	public void setMenuId(Integer menuId) {
		MenuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
}
