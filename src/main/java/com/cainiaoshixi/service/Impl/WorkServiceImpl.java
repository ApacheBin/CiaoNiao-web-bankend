package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.CnWorkMapper;
import com.cainiaoshixi.entity.CnWork;
import com.cainiaoshixi.service.IWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements IWorkService {

    @Autowired
    private CnWorkMapper workMapper;

    public CnWork getById(int id) {
        return workMapper.queryById(id);
    }
}
