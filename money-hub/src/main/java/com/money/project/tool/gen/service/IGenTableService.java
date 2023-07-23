package com.money.project.tool.gen.service;

import com.money.project.tool.gen.domain.GenTable;

import java.util.List;
import java.util.Map;


public interface IGenTableService
{
    
    public List<GenTable> selectGenTableList(GenTable genTable);

    
    public List<GenTable> selectDbTableList(GenTable genTable);

    
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    
    public List<GenTable> selectGenTableAll();

    
    public GenTable selectGenTableById(Long id);

    
    public void updateGenTable(GenTable genTable);

    
    public void deleteGenTableByIds(Long[] tableIds);

    
    public void importGenTable(List<GenTable> tableList);

    
    public Map<String, String> previewCode(Long tableId);

    
    public byte[] downloadCode(String tableName);

    
    public void generatorCode(String tableName);

    
    public void synchDb(String tableName);

    
    public byte[] downloadCode(String[] tableNames);

    
    public void validateEdit(GenTable genTable);
}
