package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.UserMapper;
import com.cainiaoshixi.entity.User;
import com.cainiaoshixi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Chy
 * @Description:
 * @Date: Created at 19:55 2018/4/9
 */
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper userMapper;

    /**
     * @Author: Chy
     * @Param:
     * @Description: 创建一个新用户
     * @Date: 20:25 2018/4/9
     */
    public User createUser(String openId) {
        User user = userMapper.getUserByOpenId(openId);
        //若用户不存在，则新建用户
        if (user == null){
            user = new User();
            user.setOpenId(openId);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userMapper.insertSelective(user);  //插入cnUser的同时，会将userId插入到cnUser中
        }
        return user;
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 更新用户信息
     * @Date: 19:03 2018/5/1
     */
    @Override
    public void updateUserById(User user) {
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 获取用户基本信息
     * @Date: 19:03 2018/5/1
     */
    @Override
    public User getUserByPrimaryKey(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}