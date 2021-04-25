package com.xpu.repair.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.pojo.entity.Urgentrepair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpu.repair.pojo.vo.UrgentrepairVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 催单表 Mapper 接口
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Repository
public interface UrgentrepairMapper extends BaseMapper<Urgentrepair> {

    List<UrgentrepairVo> listVo(Page<UrgentrepairVo> page, String technicianId,String userId);
}
