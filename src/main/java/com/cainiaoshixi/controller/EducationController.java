package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Education;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.service.IEducationService;
import com.cainiaoshixi.util.SessionUtil;
import com.cainiaoshixi.vo.EducationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/education")
@CrossOrigin
@Api(value = "教育经历控制器", tags = {"教育经历接口"})
@ResponseBody
public class EducationController {

    private final IEducationService educationService;
    private final SessionUtil session;

    @Autowired
    public EducationController(IEducationService educationService, SessionUtil session) {
        this.educationService = educationService;
        this.session = session;
    }

    /**
     * @Author: Yzh
     * @Param:
     * @Description: 查询用户的教育经历
     * @Date: 17:11 2018/4/23
     */
    @ResponseBody
    @RequestMapping(value = "/get/{id}",method= RequestMethod.GET)
    public Result getEducationListByEduId(@PathVariable("id")int eduId){
        Integer userId = session.userId();
        List<Education> educationList = educationService.getEducationListByEduId(userId,eduId);  //条件查询
        return ResultUtil.success(educationList);
    }

    /**
     * @Author: Yzh
     * @Param:
     * @Description: 保存用户的教育经历
     * @Date: 17:20 2018/4/23
     */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    @ResponseBody
    public Result addEducation(EducationVo educationVo) {
        Education education=new Education();
        education.setSchool(educationVo.getSchool());
        education.setMajor(educationVo.getMajor());
        education.setDegree(educationVo.getDegree());
        education.setAdmissionTime(educationVo.getAdmissionTime());
        education.setGraduationTime(educationVo.getGraduationTime());
        education.setUserId(session.userId());
        educationService.addEducation(education);
        return ResultUtil.success("");
    }

    /**
     * @Author: Yzh
     * @Param:
     * @Description: 更新用户的教育经历
     * @Date: 17:31 2018/4/23
     */
    @ResponseBody
    @RequestMapping("/update")
    public Result updateEducation(@RequestBody Education education) {
        education.setUpdateTime(new Date());
        educationService.updateEducation(education);
        return ResultUtil.success("");
    }

    /**
     * @Author: Yzh
     * @Param:
     * @Description: 删除用户的教育经历
     * @Date: 17:31 2018/4/23
     */
    @ResponseBody
    @RequestMapping(value="/delete/{id}",method= RequestMethod.POST)
    public Result deleteEducation(@PathVariable int id) {
        educationService.deleteEducation(id);
        return ResultUtil.success("");
    }
}
