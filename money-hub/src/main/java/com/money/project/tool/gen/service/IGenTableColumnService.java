package com.money.project.tool.gen.service;

import com.money.project.tool.gen.domain.GenTableColumn;

import java.util.List;


public interface IGenTableColumnService
{
    
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

    
    public int insertGenTableColumn(GenTableColumn genTableColumn);

    
    public int updateGenTableColumn(GenTableColumn genTableColumn);

    
    public int deleteGenTableColumnByIds(String ids);
}
