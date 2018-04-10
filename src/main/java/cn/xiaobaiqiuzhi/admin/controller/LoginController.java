package cn.xiaobaiqiuzhi.admin.controller;

import cn.xiaobaiqiuzhi.admin.vo.QrcodeVo;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chy
 * @Description: web端扫码登录
 * @Date: Created at 11:31 2018/3/9
 */
@Controller
public class LoginController {

    Map<String,String> uuidMap = new HashMap<String,String>();  //存储uuid，用来判断是否扫码

    /**
     * @param qrcodeVo
     * @return
     * @Description 微信扫码后，将传递过来的uuid写入Map,表示已扫描
     */
    @RequestMapping("/login")
    @ResponseBody
    public String scanQrcodeToLoign(@RequestBody QrcodeVo qrcodeVo) {

        uuidMap.put(qrcodeVo.getUuid(), qrcodeVo.getUserId());
        return "success";
    }

    @RequestMapping("/checkLoginState")
    @ResponseBody
    public String checkLoginState(@RequestParam String uuid, Model model){

        if (uuidMap.containsKey(uuid)){
            String userId = uuidMap.get(uuid);
            model.addAttribute("userId", userId);
            model.addAttribute("loginStatus", "login");
        }else {
            model.addAttribute("loginStatus", "unlogin");
        }
        System.out.println(JSON.toJSONString(model));

        return JSON.toJSONString(model);
    }
}