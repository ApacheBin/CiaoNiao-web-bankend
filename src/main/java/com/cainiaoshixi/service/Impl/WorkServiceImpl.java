package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.WorkMapper;
import com.cainiaoshixi.entity.Work;
import com.cainiaoshixi.service.IWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkServiceImpl implements IWorkService {

    @Autowired
    private WorkMapper workMapper;

    @Override
    public Work getById(int id) {
        return workMapper.queryById(id);
    }

    @Override
    public Work getByUserId(int uid) {
        return workMapper.queryByUserId(uid);
    }

    @Override
    public void insert(Work work) {
        Date now = new Date();
        work.setCreateTime(now);
        work.setUpdateTime(now);
        workMapper.insert(work);
    }

    @Override
    public void update(Work work) {
        Date now = new Date();
        work.setUpdateTime(now);
        workMapper.update(work);
    }

    @Override
    public void delete(int id) {
        workMapper.delete(id);
    }

}
