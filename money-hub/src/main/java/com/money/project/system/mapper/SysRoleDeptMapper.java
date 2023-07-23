package com.money.project.system.mapper;

import com.money.project.system.domain.SysRoleDept;

import java.util.List;


public interface SysRoleDeptMapper
{

    public int deleteRoleDeptByRoleId(Long roleId);


    public int deleteRoleDept(Long[] ids);


    public int selectCountRoleDeptByDeptId(Long deptId);


    public int batchRoleDept(List<SysRoleDept> roleDeptList);
}
