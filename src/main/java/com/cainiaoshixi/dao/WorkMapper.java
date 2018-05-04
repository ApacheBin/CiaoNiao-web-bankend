package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Work;

import java.util.List;

public interface WorkMapper {

    Work queryById(int id);

    List<Work> queryByUserId(int UserId);

    void delete(int id);

    void update(Work work);

    int insert(Work work);
}
