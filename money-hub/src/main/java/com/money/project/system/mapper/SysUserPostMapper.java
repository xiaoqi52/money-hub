package com.money.project.system.mapper;

import com.money.project.system.domain.SysUserPost;

import java.util.List;

public interface SysUserPostMapper
{
    public int deleteUserPostByUserId(Long userId);

    public int countUserPostById(Long postId);

    public int deleteUserPost(Long[] ids);

    public int batchUserPost(List<SysUserPost> userPostList);
}
