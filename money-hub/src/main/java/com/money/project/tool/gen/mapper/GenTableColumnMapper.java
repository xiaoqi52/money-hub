package com.money.project.tool.gen.mapper;

import com.money.project.tool.gen.domain.GenTableColumn;

import java.util.List;


public interface GenTableColumnMapper
{
    
    public List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

    
    public int insertGenTableColumn(GenTableColumn genTableColumn);

    
    public int updateGenTableColumn(GenTableColumn genTableColumn);

    
    public int deleteGenTableColumns(List<GenTableColumn> genTableColumns);

    
    public int deleteGenTableColumnByIds(Long[] ids);
}