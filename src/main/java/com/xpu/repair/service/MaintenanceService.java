package com.xpu.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Maintenance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xpu.repair.vo.MaintenanceVo;

/**
 * <p>
 * 维修记录表 服务类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
public interface MaintenanceService extends IService<Maintenance> {
    Page<MaintenanceVo> findCompleteMaintenance(int pageNum);
}
