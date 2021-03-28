package com.xpu.repair.service.impl;

import com.xpu.repair.entity.User;
import com.xpu.repair.mapper.UserMapper;
import com.xpu.repair.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
