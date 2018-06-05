package com.cainiaoshixi.controller;

import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.Company;
import com.cainiaoshixi.entity.File;
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

@Controller
@RequestMapping("/company")
@CrossOrigin
@Api(value = "公司信息控制器", tags = {"公司信息接口"})
@ResponseBody
public class CompanyController {

    private final static String COMPANY_LOGO_IMAGE_DIR = "/data/images/company/logo/";

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
        jsonObject.put("companyId", company.getId());
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

    @PostMapping("/logo/add")
    @ApiOperation("新增公司照片")
    public Result addCompanyLogo(@RequestParam("companyId") Integer companyId,
                                 @RequestParam("image") MultipartFile image) throws IOException{
        String relativePath = FileUtil.getRelativePath(image);
        FileUtil.save(image, COMPANY_LOGO_IMAGE_DIR + relativePath);
        Company company = new Company();
        company.setId(companyId);
        company.setLogo(COMPANY_LOGO_IMAGE_DIR + relativePath);
        companyService.updateCompany(company);
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
