package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.CnUser;

public interface CnUserMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(CnUser record);

    Integer insertSelective(CnUser record);

    CnUser selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(CnUser record);

    Integer updateByPrimaryKey(CnUser record);

    Integer getUserByOpenId(String openId);  //根据openId查询用户
}