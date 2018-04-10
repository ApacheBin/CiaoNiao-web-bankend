package cn.xiaobaiqiuzhi.admin.dao;

import cn.xiaobaiqiuzhi.admin.entity.WxUser;

public interface WxUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(WxUser record);

    int insertSelective(WxUser record);

    WxUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(WxUser record);

    int updateByPrimaryKey(WxUser record);
}