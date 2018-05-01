package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.CnUserMapper;
import com.cainiaoshixi.entity.CnUser;
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
    private CnUserMapper cnUserMapper;

    /**
     * @Author: Chy
     * @Param:
     * @Description: 创建一个新用户
     * @Date: 20:25 2018/4/9
     */
    public int createUser(String openId) {
        //若用户不存在，则新建用户
        Integer id = cnUserMapper.getUserByOpenId(openId);
        if (id != null){
            return id;
        }else{
            CnUser cnUser = new CnUser();
            cnUser.setOpenId(openId);
            cnUser.setCreateTime(new Date());
            cnUser.setUpdateTime(new Date());
            cnUserMapper.insertSelective(cnUser);
            return cnUserMapper.getUserByOpenId(openId);
        }
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 更新用户信息
     * @Date: 19:03 2018/5/1
     */
    @Override
    public void updateUserById(CnUser cnUser) {
        cnUser.setUpdateTime(new Date());
        cnUserMapper.updateByPrimaryKeySelective(cnUser);
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 获取用户基本信息
     * @Date: 19:03 2018/5/1
     */
    @Override
    public CnUser getUserByPrimaryKey(int userId) {
        return cnUserMapper.selectByPrimaryKey(userId);
    }
}