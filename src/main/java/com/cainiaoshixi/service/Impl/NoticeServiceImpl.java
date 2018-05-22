package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.NoticeMapper;
import com.cainiaoshixi.entity.Notice;
import com.cainiaoshixi.service.INoticeService;
import com.cainiaoshixi.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements INoticeService {

    private final NoticeMapper noticeMapper;

    private final static String image_dir = "cainiaoshixi.com/images/notices/";

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }


    @Override
    public List<NoticeVo> getRecent() {
        List<Notice> noticeList = noticeMapper.selectAll();

        ArrayList<NoticeVo> noticeVoList = new ArrayList<>();
        for(Notice notice: noticeList) {
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setContent(notice.getContent());
            noticeVo.setUrl(notice.getUrl());
            noticeVo.setId(notice.getId());
            noticeVo.setImage(image_dir + notice.getImageLocation());
            noticeVoList.add(noticeVo);
        }
        return noticeVoList;
    }

    @Override
    public int insert(Notice notice) {
        Date now = new Date();
        notice.setCreateTime(now);
        notice.setUpdateTime(now);
        return noticeMapper.insert(notice);
    }
}
