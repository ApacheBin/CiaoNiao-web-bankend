package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.User;

/**
 * @Author: Chy
 * @Param:
 * @Description: 用户接口
 * @Date: 19:54 2018/4/9
 */
public interface IUserService {
    User createUser(String openId);  //创建用户

    void updateUserById(User user);  //更新用户

    User getUserByPrimaryKey(int userId);  //获取用户基本信息
}
