package com.xpu.repair.controller;


import com.xpu.repair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


}

