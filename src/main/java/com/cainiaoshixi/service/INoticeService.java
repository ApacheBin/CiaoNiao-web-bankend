package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Notice;
import com.cainiaoshixi.vo.NoticeVo;

import java.util.List;

public interface INoticeService {

    List<NoticeVo> getRecent();

    int insert(Notice notice);

}
