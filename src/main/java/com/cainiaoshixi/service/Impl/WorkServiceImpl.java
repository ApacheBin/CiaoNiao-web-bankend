package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.WorkMapper;
import com.cainiaoshixi.entity.Work;
import com.cainiaoshixi.service.IWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements IWorkService {

    @Autowired
    private WorkMapper workMapper;

    public Work getById(int id) {
        return workMapper.queryById(id);
    }
}
