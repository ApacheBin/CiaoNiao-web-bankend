package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Work;

import java.util.List;

public interface IWorkService {

    Work getById(int id);

    List<Work> getByUserId(int userId);

    int insert(Work work);

    void update(Work work);

    void delete(int id);
}
