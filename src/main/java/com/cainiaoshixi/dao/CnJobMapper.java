package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.CnJob;
import com.cainiaoshixi.vo.JobQueryVo;

import java.util.List;

public interface CnJobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CnJob record);

    CnJob selectByPrimaryKey(Integer id);

    List<CnJob> selectAll();

    int updateByPrimaryKey(CnJob record);

    List<CnJob> getJobListByVo(JobQueryVo jobQueryVo);

    int insertSelective(CnJob job);

    int updateByPrimaryKeySelective(CnJob job);
}