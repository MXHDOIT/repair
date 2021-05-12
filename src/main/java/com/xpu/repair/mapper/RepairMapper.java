package com.xpu.repair.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.pojo.entity.Repair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpu.repair.pojo.vo.RepairVO;
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
    List<RepairVO> listRepairDetail(Page page);

    List<RepairVO> listRepairDetailByRepair(Page page, Repair repair);

    List<RepairVO> listReminderDetail(Page page);

    List<RepairVO> findAllRepairs(Page<RepairVO> page);

    List<RepairVO> listRepairByUserIdAndStatus(String userId, int statusId);
}
