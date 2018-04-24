package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Work;

public interface WorkMapper {
    /**
     * 根据ID查询
     * @param id 工作id
     * @return
     */
    Work queryById(int id);

}
