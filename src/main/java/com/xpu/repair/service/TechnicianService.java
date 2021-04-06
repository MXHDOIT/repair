package com.xpu.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Technician;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xpu.repair.vo.TechnicianVo;

/**
 * <p>
 * 维修人员表 服务类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
public interface TechnicianService extends IService<Technician> {
    Page<TechnicianVo> findTechnicianPage(int pageNum);
}
