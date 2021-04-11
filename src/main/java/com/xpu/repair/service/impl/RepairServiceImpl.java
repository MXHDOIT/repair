package com.xpu.repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Repair;
import com.xpu.repair.enums.RepairStatusEnum;
import com.xpu.repair.mapper.RepairMapper;
import com.xpu.repair.service.RepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpu.repair.vo.RepairVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报修单表 服务实现类
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

    @Autowired
    RepairMapper repairMapper;

    private static final int SIZE = 10;

    @Override
    public Page<Repair> findRepairPage(int pageNum) {
        Page<Repair> page = new Page<>(pageNum,SIZE);
        repairMapper.selectPage(page,null);
        return page;
    }

    @Override
    public Page<Repair> findUnallocatedRepairPage(int pageNum) {
        Page<Repair> page = new Page<>(pageNum,SIZE);

        //查询条件
        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", RepairStatusEnum.UNALLOCATED.getStatusId());
        //查询数据库
        repairMapper.selectPage(page,queryWrapper);

        return page;
    }

    @Override
    public Page<RepairVo> findRepairByUserId(int pageNum, String userId) {
        Page<RepairVo> page = new Page<>(pageNum,SIZE);

        //查询条件
        Repair repair = new Repair();
        repair.setUserId(userId);
        //查询数据库
        List<RepairVo> repairVos = repairMapper.listRepairDetailByRepair(page, repair);
        for (RepairVo repairVo : repairVos) {
            repairVo.setStatusName(RepairStatusEnum.getById(repairVo.getStatus()).getStatusName());
        }
        page.setRecords(repairVos);

        return page;
    }

    @Override
    public Page<RepairVo> findReminders(int pageNum) {
        Page<RepairVo> page = new Page<>(pageNum,SIZE);

        //查询条件
        //查询数据库
        List<RepairVo> repairVos = repairMapper.listReminderDetail(page);
        for (RepairVo repairVo : repairVos) {
            repairVo.setStatusName(RepairStatusEnum.getById(repairVo.getStatus()).getStatusName());
        }
        page.setRecords(repairVos);
        return page;
    }


}
