package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.CnWork;

public interface CnWorkMapper {
    /**
     * 根据ID查询
     * @param id 工作id
     * @return
     */
    CnWork queryById(int id);

}
