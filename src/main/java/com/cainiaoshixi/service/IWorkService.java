package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Work;

public interface IWorkService {

    Work getById(int id);

    Work getByUserId(int userId);

    void insert(Work work);

    void update(Work work);

    void delete(int id);
}
