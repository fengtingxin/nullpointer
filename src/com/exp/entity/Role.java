package com.exp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 实体类Role 
 * name:角色
 * @author fengtingxin
 *
 */
@Entity
@Table(name="role")
public class Role {

	//属性 
	private Integer roleId; //id //主键
	private String  roleName; //角色名
	private Set<Power> powers=new HashSet<Power>(0);
	//set/get方法	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "r_role_power", joinColumns = {
			@JoinColumn(name = "roleId", referencedColumnName = "roleId") }, inverseJoinColumns = {
					@JoinColumn(name = "powerId", referencedColumnName = "powerId") })
	public Set<Power> getPowers() {
		return powers;
	}
	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}
	
}
