package cn.xiaobaiqiuzhi.admin.service.Impl;

import cn.xiaobaiqiuzhi.admin.dao.CnUserMapper;
import cn.xiaobaiqiuzhi.admin.entity.CnUser;
import cn.xiaobaiqiuzhi.admin.service.IUserService;
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
    public void createUser(String openId) {
        //若用户不存在，则新建用户并保存
        if (cnUserMapper.getUserByOpenId(openId) > 0){
            return;
        }else{
            CnUser cnUser = new CnUser();
            cnUser.setOpenId(openId);
            cnUser.setCreateTime(new Date());
            cnUser.setUpdateTime(new Date());
            cnUserMapper.insertSelective(cnUser);
        }
    }
}