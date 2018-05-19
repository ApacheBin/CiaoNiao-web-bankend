package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Notice;
import com.cainiaoshixi.service.INoticeService;
import com.cainiaoshixi.util.FileUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/notice")
@CrossOrigin
@Api(value = "公告控制器", tags = {"公告接口"})
public class NoticeController {

    private final static Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private final static String NOTICE_IMAGE_DIR = "/data/images/notices/";

    private final INoticeService noticeService;


    @Autowired
    public NoticeController(INoticeService noticeService, SessionUtil session) {
        this.noticeService = noticeService;
    }

    @GetMapping("/get")
    @ApiOperation("获取最近的公告")
    public Result getRecentNotice() {
        return ResultUtil.success(noticeService.getRecent());
    }

    @PostMapping("/add")
    @ApiOperation("新增公告")
    public Result addNotice(@RequestParam("content") String content,
                            @RequestParam("url") String url,
                            @RequestParam("status") Byte status,
                            @RequestParam("image") MultipartFile image) throws IOException{
        String imageName = image.getOriginalFilename();
        String relativePath = FileUtil.getRelativePath(image);
        FileUtil.save(image, NOTICE_IMAGE_DIR + relativePath);
        Notice notice = new Notice();
        notice.setImageName(imageName);
        notice.setImageLocation(relativePath);
        notice.setContent(content);
        notice.setUrl(url);
        notice.setStatus(status);
        noticeService.insert(notice);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("noticeId", notice.getId());
        return ResultUtil.success(jsonObject);
    }
}
