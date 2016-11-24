package com.exp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="power")
public class Power {
	private Integer powerId;
	private Integer powerName;
	private Menu menu;
	private Set<Role> roles=new HashSet<Role>(0);
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPowerId() {
		return powerId;
	}
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	public Integer getPowerName() {
		return powerName;
	}
	public void setPowerName(Integer powerName) {
		this.powerName = powerName;
	}
	@ManyToMany(mappedBy = "powers")
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@ManyToOne
	@JoinColumn(name = "menuId")
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	

}
