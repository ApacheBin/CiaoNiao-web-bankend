package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Company;
import com.cainiaoshixi.entity.File;
import com.cainiaoshixi.entity.JobCompany;
import com.cainiaoshixi.enums.FileTypeEnum;
import com.cainiaoshixi.service.ICompanyService;
import com.cainiaoshixi.service.IFileService;
import com.cainiaoshixi.util.FileUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/company")
@CrossOrigin
@Api(value = "公司信息控制器", tags = {"公司信息接口"})
@ResponseBody
public class CompanyController {

    private final static String PRIMARY_COMPANY_LOGO_IMAGE_DIR = "/data/images/primaryCompany/logo/";
    private final static String SECONDARY_COMPANY_LOGO_IMAGE_DIR = "/data/images/secondaryCompany/logo/";

    private final ICompanyService companyService;
    /**
     * 当前会话
     */
    private final SessionUtil session;

    private final IFileService fileService;

    @Autowired
    public CompanyController(ICompanyService companyService, SessionUtil session, IFileService fileService) {
        this.companyService = companyService;
        this.session = session;
        this.fileService = fileService;
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 查询用户的认证公司信息
     * @Date: 17:11 2018/5/6
     */
    @ApiOperation("根据userid获取认证公司信息")
    @RequestMapping(value = "/primary/get",method= RequestMethod.GET)
    public Result getCompanyListByCompanyId(){
        Integer userId = session.userId();
        List<Company> companyList = companyService.getCompanyListByUserId(userId);  //条件查询
        return ResultUtil.success(companyList);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 查询用户的非认证公司信息（只为Job服务）
     * @Date: 17:11 2018/7/15
     */
    @ApiOperation("根据userid获取非认证公司信息")
    @RequestMapping(value = "/secondary/get",method= RequestMethod.GET)
    public Result getJobCompanyListByCompanyId(){
        Integer userId = session.userId();
        List<JobCompany> jobCompanyList = companyService.getJobCompanyListByUserId(userId);  //条件查询
        return ResultUtil.success(jobCompanyList);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 新增用户的认证公司信息
     * @Date: 17:11 2018/5/6
     */
    @ApiOperation("新增用户的认证公司信息")
    @PostMapping("/primary/add")
    public Result addCompany(@RequestBody Company company) {
        company.setUserId(session.userId());
        companyService.addCompany(company);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("companyId", company.getId());
        return ResultUtil.success(jsonObject);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 新增用户的非认证公司信息（只为Job服务）
     * @Date: 17:11 2018/7/15
     */
    @ApiOperation("新增用户的非认证公司信息")
    @PostMapping("/secondary/add")
    public Result addJobCompany(@RequestBody JobCompany jobCompany) {
        jobCompany.setUserId(session.userId());
        companyService.addJobCompany(jobCompany);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jobCompanyId", jobCompany.getId());
        return ResultUtil.success(jsonObject);
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 更新用户的认证公司信息
     * @Date: 17:11 2018/5/6
     */
    @ApiOperation("更新用户的认证公司信息")
    @RequestMapping("/primary/update")
    public Result updateCompany(@RequestBody Company company) {
        company.setUserId(session.userId());
        companyService.updateCompany(company);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 更新用户的非认证公司信息
     * @Date: 17:11 2018/7/15
     */
    @ApiOperation("更新用户的非认证公司信息")
    @RequestMapping("/secondary/update")
    public Result updateJobCompany(@RequestBody JobCompany jobCompany) {
        jobCompany.setUserId(session.userId());
        companyService.updateJobCompany(jobCompany);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 删除用户的认证公司信息
     * @Date: 17:11 2018/5/6
     */
    @ApiOperation("删除认证公司信息")
    @PostMapping("/primary/delete")
    public Result deleteCompany(@RequestParam("id") int id){
        companyService.deleteCompany(id);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 删除用户的非认证公司信息
     * @Date: 17:11 2018/7/15
     */
    @ApiOperation("删除非认证公司信息")
    @PostMapping("/secondary/delete")
    public Result deleteJobCompany(@RequestParam("id") int id){
        companyService.deleteJobCompany(id);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 新增认证公司Logo
     * @Date: 17:11 2018/7/6
     */
    @PostMapping("/primaryLogo/add")
    @ApiOperation("新增认证公司Logo")
    public Result addCompanyLogo(@RequestParam("companyId") Integer companyId,
                                 @RequestParam("image") MultipartFile image) throws IOException{
        String relativePath = FileUtil.getRelativePath(image);
        FileUtil.save(image, PRIMARY_COMPANY_LOGO_IMAGE_DIR + relativePath);
        Company company = new Company();
        company.setId(companyId);
        company.setLogo(PRIMARY_COMPANY_LOGO_IMAGE_DIR + relativePath);
        companyService.updateCompany(company);
        return ResultUtil.success("");
    }

    /**
     * @Author: Zxc
     * @Param:
     * @Description: 新增非认证公司Logo
     * @Date: 17:11 2018/7/6
     */
    @PostMapping("/secondaryLogo/add")
    @ApiOperation("新增非认证公司Logo")
    public Result addJobCompanyLogo(@RequestParam("companyId") Integer companyId,
                                 @RequestParam("image") MultipartFile image) throws IOException{
        String relativePath = FileUtil.getRelativePath(image);
        FileUtil.save(image, SECONDARY_COMPANY_LOGO_IMAGE_DIR + relativePath);
        JobCompany jobCompany = new JobCompany();
        jobCompany.setId(companyId);
        jobCompany.setLogo(SECONDARY_COMPANY_LOGO_IMAGE_DIR + relativePath);
        companyService.updateJobCompany(jobCompany);
        return ResultUtil.success("");
    }

    @PostMapping("/company/upload")
    @ApiOperation("上传公司证书信息")
    public Result getStudentCertification(@RequestParam("companyId") Integer companyId,
            @RequestParam("cert")MultipartFile cert) throws IOException {
            File fileEntity = new File();
            fileEntity.setUploaderId(session.userId());
            String relativePath = FileUtil.getRelativePath(cert);
            fileEntity.setType(FileTypeEnum.COMPANY.getCode());
            //保存公司证书
            fileService.save(fileEntity, cert);
            Company company = new Company();
            company.setId(companyId);
            company.setFileId(fileEntity.getId());
            companyService.updateCompany(company);
            return ResultUtil.success("");
    }
}
