package com.xpu.repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Repair;
import com.xpu.repair.enums.RepairStatusEnum;
import com.xpu.repair.mapper.RepairMapper;
import com.xpu.repair.service.RepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", RepairStatusEnum.UNALLOCATED.getStatusId());
        Page<Repair> page = new Page<>(pageNum,SIZE);
        repairMapper.selectPage(page,queryWrapper);
        return page;
    }


}
