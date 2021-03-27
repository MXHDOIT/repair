package com.xpu.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 维修记录表
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Maintenance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 该维修记录对应的报修单编号
     */
    @TableField("repairId")
    private Integer repairId;

    /**
     * 维修人员的编号
     */
    @TableField("technicianId")
    private Integer technicianId;

    /**
     * 维修开始的时间
     */
    @TableField("startTime")
    private Date startTime;

    /**
     * 维修结束的时间
     */
    @TableField("endTime")
    private Date endTime;

    /**
     * 维修完成后现场照片
     */
    @TableField("picURL")
    private String picURL;


}
