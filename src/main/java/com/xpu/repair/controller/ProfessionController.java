package com.xpu.repair.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.pojo.dto.ResultDTO;
import com.xpu.repair.pojo.entity.Profession;
import com.xpu.repair.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 职业表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/profession")
public class ProfessionController {

    @Autowired
    ProfessionService professionService;

    /**
     * 跳转到工种页面，携带信息
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/addProfessionPage",method = RequestMethod.GET)
    public String showAddProfessionPage(Model model, @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum){
        Page<Profession> professionPage = professionService.findProfessionPage(pageNum);
        model.addAttribute("page",professionPage);
        return "admin/addProfession";
    }

    /**
     * 添加工种
     * @param professionName
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO add(String professionName){
        QueryWrapper<Profession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",professionName);
        Profession profession = professionService.getOne(queryWrapper);
        if (profession!=null){
            return ResultDTO.error().message("此工种已经存在");
        }

        boolean saveResult = professionService.save(new Profession().setName(professionName));
        if (saveResult) {
            return ResultDTO.ok();
        }
        return ResultDTO.error().message("添加失败");
    }

    /**
     * 删除工种，通过professionId
     * @param professionId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO delete(int professionId) {
        boolean removeResult = professionService.removeById(professionId);

        if (removeResult){
            return ResultDTO.ok().data("url","/profession/addProfessionPage");
        }else {
            return ResultDTO.error().message("删除失败");
        }
    }
}

