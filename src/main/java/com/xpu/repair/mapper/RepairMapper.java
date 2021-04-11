package com.xpu.repair.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Repair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpu.repair.vo.RepairVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 报修单表 Mapper 接口
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Repository
public interface RepairMapper extends BaseMapper<Repair> {
    List<RepairVo> listRepairDetail(Page page);

    List<RepairVo> listRepairDetailByRepair(Page page,Repair repair);

    List<RepairVo> listReminderDetail(Page page);
}
