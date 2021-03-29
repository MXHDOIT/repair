package com.xpu.repair.service.impl;

import com.xpu.repair.entity.Repair;
import com.xpu.repair.mapper.RepairMapper;
import com.xpu.repair.service.RepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
