package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.SchoolExperience;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolExpMapper {

    List<SchoolExperience> queryBySchId(@Param("userId") Integer userId, @Param("schId") Integer schId);

    void delete(int id);

    void update(SchoolExperience schoolExperience);

    int insert(SchoolExperience schoolExperience);
}
