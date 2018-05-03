package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Work;

public interface WorkMapper {

    Work queryById(int id);

    Work queryByUserId(int UserId);

    void delete(int id);

    void update(Work work);

    int insert(Work work);
}
