package com.xpu.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Repair;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 报修单表 服务类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
public interface RepairService extends IService<Repair> {
    Page<Repair> findRepairPage(int pageNum);
}
