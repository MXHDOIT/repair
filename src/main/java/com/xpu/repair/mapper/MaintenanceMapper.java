package com.xpu.repair.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Maintenance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpu.repair.vo.MaintenanceVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 维修记录表 Mapper 接口
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Repository
public interface MaintenanceMapper extends BaseMapper<Maintenance> {
    List<MaintenanceVo> findAllMaintenanceVO(Page page);

    List<MaintenanceVo> findSuccessMaintenanceVO(Page page);
}
