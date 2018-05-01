package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.CnUser;

/**
 * @Author: Chy
 * @Param:
 * @Description: 用户接口
 * @Date: 19:54 2018/4/9
 */
public interface IUserService {
    int createUser(String openId);  //创建用户

    void updateUserById(CnUser cnUser);  //更新用户

    CnUser getUserByPrimaryKey(int userId);  //获取用户基本信息
}
