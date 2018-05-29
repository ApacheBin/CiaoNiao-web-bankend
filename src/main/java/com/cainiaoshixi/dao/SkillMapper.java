package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Skill;
import java.util.List;

public interface SkillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Skill skill);

    //Skill selectByPrimaryKey(Integer id);

    Skill selectByUserId(Integer userId);

    int updateByUserId(Skill skill);

    int isExistSkill(int userID);
}