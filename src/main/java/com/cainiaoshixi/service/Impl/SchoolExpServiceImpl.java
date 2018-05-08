package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.SchoolExpMapper;
import com.cainiaoshixi.entity.SchoolExperience;
import com.cainiaoshixi.service.ISchoolExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SchoolExpServiceImpl implements ISchoolExpService {

    private final SchoolExpMapper schoolExpMapper;

    @Autowired
    public SchoolExpServiceImpl(SchoolExpMapper schoolExpMapper) {
        this.schoolExpMapper = schoolExpMapper;
    }

    @Override
    public List<SchoolExperience> queryBySchId(int userId,int schId)
    {
        return schoolExpMapper.queryBySchId(userId,schId);
    }

    @Override
    public int insert(SchoolExperience schoolExperience) {
        Date now = new Date();
        schoolExperience.setCreateTime(now);
        schoolExperience.setUpdateTime(now);
        return schoolExpMapper.insert(schoolExperience);
    }

    @Override
    public void update(SchoolExperience schoolExperience) {
        Date now = new Date();
        schoolExperience.setUpdateTime(now);
        schoolExpMapper.update(schoolExperience);
    }

    @Override
    public void delete(int id) {
        schoolExpMapper.delete(id);
    }

}
