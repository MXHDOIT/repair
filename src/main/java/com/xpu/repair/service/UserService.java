package com.xpu.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
public interface UserService extends IService<User> {
    Page<User> findUserByPage(int pageNum);
}
