package com.xpu.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="Maintenance对象", description="维修记录表")
public class Maintenance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "该维修记录对应的报修单编号")
    @TableField("repairId")
    private Integer repairId;

    @ApiModelProperty(value = "维修人员的编号")
    @TableField("technicianId")
    private Integer technicianId;

    @ApiModelProperty(value = "维修开始的时间")
    @TableField("startTime")
    private Date startTime;

    @ApiModelProperty(value = "维修结束的时间")
    @TableField("endTime")
    private Date endTime;

    @ApiModelProperty(value = "维修完成后现场照片")
    @TableField("picURL")
    private String picURL;


}
