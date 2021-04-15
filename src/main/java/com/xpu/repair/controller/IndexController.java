package com.xpu.repair.controller;

import com.xpu.repair.pojo.dto.ResultDTO;
import com.xpu.repair.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = {"/","/login"},method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = {"user/index"},method = RequestMethod.GET)
    public String userIndex(){
        return "user/index";
    }

    @RequestMapping(value = {"admin/index"},method = RequestMethod.GET)
    public String adminIndex(){
        return "admin/index";
    }

    @RequestMapping(value = {"technician/index"},method = RequestMethod.GET)
    public String technicianIndex(){
        return "technician/index";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:login";
    }

    /**
     * 测试文件上传
     * @param file
     * @return
     */
    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO upload(@RequestParam("file")MultipartFile file) {
        String uploadUrl = fileService.upload(file);

        //返回对象
        return ResultDTO.ok().message("文件上传成功").data("url",uploadUrl);
    }
}
