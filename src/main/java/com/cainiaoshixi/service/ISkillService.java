package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Skill;

public interface ISkillService {

    int deleteByPrimaryKey(Integer id);

    int insert(Skill skill);

    //Skill selectByPrimaryKey(Integer id);

    Skill selectByUserId(Integer userId);

    int updateByUserId(Skill skill);

    int isExistSkill(int userId);

}
