package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Notice;
import com.cainiaoshixi.service.INoticeService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/notice")
@CrossOrigin
@Api(value = "公告控制器", tags = {"公告接口"})
public class NoticeController {

    private final static Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private final static String NOTICE_IMAGE_DIR = "/data/images/notices/";

    private final INoticeService noticeService;

    /**
     * 当前会话
     */
    private final SessionUtil session;

    @Autowired
    public NoticeController(INoticeService noticeService, SessionUtil session) {
        this.noticeService = noticeService;
        this.session = session;
    }

    @GetMapping("/get")
    public Result getNotice() {
        return ResultUtil.success(noticeService.getRecent());
    }

    @PostMapping("/add")
    public Result addNotice(@RequestParam("content") String content,
                            @RequestParam("url") String url,
                            @RequestParam("status") Byte status,
                            @RequestParam("image") MultipartFile image) throws IOException{
        String imageName = image.getOriginalFilename();
        String ext = imageName.substring(imageName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString();
        String dirname = (System.currentTimeMillis() >> 15) + "/";
        File dir = new File(NOTICE_IMAGE_DIR + dirname);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        image.transferTo(new File(dir + "/" + uuid + "." + ext));

        Notice notice = new Notice();
        notice.setImageName(imageName);
        notice.setImageLocation(dirname + uuid + "." + ext);
        notice.setContent(content);
        notice.setUrl(url);
        notice.setStatus(status);
        noticeService.insert(notice);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("noticeId", notice.getId());
        return ResultUtil.success(jsonObject);
    }
}
