package com.money.project.system.mapper;

import com.money.project.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysUserRoleMapper
{

    public int deleteUserRoleByUserId(Long userId);

    public int deleteUserRole(Long[] ids);

    public int countUserRoleByRoleId(Long roleId);

    public int batchUserRole(List<SysUserRole> userRoleList);

    public int deleteUserRoleInfo(SysUserRole userRole);

    public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);
}
