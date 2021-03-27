package com.xpu.repair.controller;


import com.xpu.repair.dto.R;
import com.xpu.repair.entity.User;
import com.xpu.repair.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-27
 */
@Api(description = "用户管理")
@RestController
@RequestMapping("/repair/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "查询用户采用ID")
    @RequestMapping(value = "getUser/{id}",method = RequestMethod.GET)
    public R getUser(@PathVariable int id){
        User user = userService.getById(id);

        return R.ok().data("user",user);
    }
}

