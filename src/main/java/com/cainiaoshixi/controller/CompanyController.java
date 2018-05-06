package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Company;
import com.cainiaoshixi.service.ICompanyService;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
@CrossOrigin
@Api(value = "公司信息控制器", tags = {"公司信息接口"})
@ResponseBody
public class CompanyController {

    private final ICompanyService companyService;
    /**
     * 当前会话
     */
    private final SessionUtil session;

    @Autowired
    public CompanyController(ICompanyService companyService, SessionUtil session) {
        this.companyService = companyService;
        this.session = session;
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 查询用户的公司信息
     * @Date: 17:11 2018/5/6
     */
    @ApiOperation("根据userid获取公司信息")
    @RequestMapping(value = "/get",method= RequestMethod.GET)
    public Result getCompanyListByCompanyId(){
        Integer userId = session.userId();
        Company companyList = companyService.getCompanyListByUserId(userId);  //条件查询
        return ResultUtil.success(companyList);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 新增用户的公司信息
     * @Date: 17:11 2018/5/6
     */
    @ApiOperation("新增公司信息")
    @PostMapping("/add")
    public Result addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CompanyId", company.getId());
        return ResultUtil.success(jsonObject);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 更新用户的公司信息
     * @Date: 17:11 2018/5/6
     */
    @ApiOperation("更新公司信息")
    @RequestMapping("/update")
    public Result updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 删除用户的公司信息
     * @Date: 17:11 2018/5/6
     */
    @ApiOperation("删除公司信息")
    @PostMapping("/delete")
    public Result deleteCompany(@RequestParam("id") int id){
        companyService.deleteCompany(id);
        return ResultUtil.success("");
    }
}
