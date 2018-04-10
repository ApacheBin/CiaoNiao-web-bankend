package cn.xiaobaiqiuzhi.admin.dao;

import cn.xiaobaiqiuzhi.admin.entity.CnUser;

public interface CnUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CnUser record);

    int insertSelective(CnUser record);

    CnUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CnUser record);

    int updateByPrimaryKey(CnUser record);

    int getUserByOpenId(String openId);  //根据openId查询用户
}