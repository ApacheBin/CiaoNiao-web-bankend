package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.User;

public interface UserMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(User record);

    Integer insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(User record);

    Integer updateByPrimaryKey(User record);

    User getUserByOpenId(String openId);  //根据openId查询用户
}