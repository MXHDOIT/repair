package com.xpu.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Profession;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职业表 服务类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
public interface ProfessionService extends IService<Profession> {
    Page<Profession> findProfessionPage(int pageNum);
}
