package com.xpu.repair.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.pojo.entity.Maintenance;
import com.xpu.repair.mapper.MaintenanceMapper;
import com.xpu.repair.service.MaintenanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpu.repair.pojo.vo.MaintenanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 维修记录表 服务实现类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Service
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceMapper, Maintenance> implements MaintenanceService {

    @Autowired
    MaintenanceMapper maintenanceMapper;

    private static final int SIZE = 10;

    @Override
    public Page<MaintenanceVO> findCompleteMaintenance(int pageNum) {
        Page<MaintenanceVO> page = new Page<>(pageNum,SIZE);
        List<MaintenanceVO> successMaintenanceVO = maintenanceMapper.findSuccessMaintenanceVO(page);
        page.setRecords(successMaintenanceVO);
        return page;
    }

    @Override
    public Page<MaintenanceVO> listUnCompleteMaintenanceByTechnicianId(String technicianId, int pageNum) {
        Page<MaintenanceVO> page = new Page<>(pageNum,SIZE);
        List<MaintenanceVO> unCompleteMaintenanceVO = maintenanceMapper.listUnCompleteMaintenanceByTechnicianId(page,technicianId);
        page.setRecords(unCompleteMaintenanceVO);
        return page;
    }
}
