package com.xpu.repair.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.pojo.entity.Maintenance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpu.repair.pojo.vo.MaintenanceVO;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    List<MaintenanceVO> findAllMaintenanceVO(Page page);

//    List<MaintenanceVO> findSuccessMaintenanceVO(Page page);

    List<MaintenanceVO> listUnCompleteMaintenanceByTechnicianId(Page<MaintenanceVO> page, String technicianId);

    List<MaintenanceVO> listCompleteMaintenanceByTechnicianId(Page<MaintenanceVO> page, String technicianId, Date startTime, Date endTime);

    List<MaintenanceVO> listCompleteMaintenance(String technicianId, Date startTime, Date endTime);
}
