package com.xpu.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.pojo.entity.Repair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xpu.repair.pojo.vo.RepairVO;

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

    Page<Repair> findUnallocatedRepairPage(int pageNum);

    Page<RepairVO> findRepairByUserId(int pageNum, String userId);

    Page<RepairVO> findReminders(int pageNum);
}

