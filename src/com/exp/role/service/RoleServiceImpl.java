package com.exp.role.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Role;
import com.exp.role.dao.RoleDaoImpl;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl {
 
	@Resource
	private RoleDaoImpl roleDaoImpl;
	/**
	 * @function 根据roleId查询单个Role
	 * @author tangwenru
	 * @param roleId
	 * @return
	 */
	public Role getRole(Integer roleId){
		return this.roleDaoImpl.getRole(roleId);
	}
}
