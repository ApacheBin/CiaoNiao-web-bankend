package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.SchoolExperience;

import java.util.List;

public interface ISchoolExpService {

    List<SchoolExperience> queryBySchId(int userId,int schId);

    void delete(int id);

    void update(SchoolExperience schoolExperience);

    int insert(SchoolExperience schoolExperience);
}
