package com.money.project.system.service;

import com.money.project.system.domain.SysNotice;

import java.util.List;


public interface ISysNoticeService
{

    public SysNotice selectNoticeById(Long noticeId);


    public List<SysNotice> selectNoticeList(SysNotice notice);


    public int insertNotice(SysNotice notice);


    public int updateNotice(SysNotice notice);


    public int deleteNoticeById(Long noticeId);
    

    public int deleteNoticeByIds(Long[] noticeIds);
}
