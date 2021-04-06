package com.xpu.repair.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.User;
import com.xpu.repair.mapper.UserMapper;
import com.xpu.repair.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    //分页每页数量
    private static final int SIZE = 10;

    @Override
    public Page<User> findUserByPage(int pageNum) {
        Page<User> page = new Page<>(pageNum,SIZE);
        userMapper.selectPage(page,null);
        return page;
    }
}
