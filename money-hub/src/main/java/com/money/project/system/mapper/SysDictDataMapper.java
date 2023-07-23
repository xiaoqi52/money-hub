package com.money.project.system.mapper;import com.money.project.system.domain.SysDictData;import org.apache.ibatis.annotations.Param;import java.util.List;public interface SysDictDataMapper{        public List<SysDictData> selectDictDataList(SysDictData dictData);        public List<SysDictData> selectDictDataByType(String dictType);        public String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);        public SysDictData selectDictDataById(Long dictCode);        public int countDictDataByType(String dictType);        public int deleteDictDataById(Long dictCode);        public int deleteDictDataByIds(Long[] dictCodes);        public int insertDictData(SysDictData dictData);        public int updateDictData(SysDictData dictData);        public int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);}