package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.NoticeMapper;
import com.cainiaoshixi.dao.SkillMapper;
import com.cainiaoshixi.entity.Notice;
import com.cainiaoshixi.entity.Skill;
import com.cainiaoshixi.service.INoticeService;
import com.cainiaoshixi.service.ISkillService;
import com.cainiaoshixi.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SkillServiceImpl implements ISkillService {

    private final SkillMapper skillMapper;

    @Autowired
    public SkillServiceImpl(SkillMapper skillMapper) {
        this.skillMapper = skillMapper;
    }


    @Override
    public int deleteByPrimaryKey(Integer id){
        return skillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Skill skill){
        Date now = new Date();
        skill.setCreateTime(now);
        skill.setUpdateTime(now);
        return skillMapper.insert(skill);
    }

    //Skill selectByPrimaryKey(Integer id);

    @Override
    public Skill selectByUserId(Integer userId){
        return skillMapper.selectByUserId(userId);
    }

    @Override
    public int updateByUserId(Skill skill){
        Date now = new Date();
        skill.setUpdateTime(now);
        return skillMapper.updateByUserId(skill);
    }
    @Override
    public int isExistSkill(int userId){
        return skillMapper.isExistSkill(userId);
    }
}
