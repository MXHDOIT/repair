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
 * 报修单表
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Repair对象", description="报修单表")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "报修单状态：0为被用户删除，1为未安排检修，2为已安排检修，3为待同意取消， 4为已同意取消，5为待验收，6为已验收，默认为被用户删除")
    private Integer status;

    @ApiModelProperty(value = "问题详情")
    private String detail;

    @ApiModelProperty(value = "发生故障的物业的地点")
    private String place;

    @ApiModelProperty(value = "现场照片")
    @TableField("picURL")
    private String picURL;

    @ApiModelProperty(value = "提交报修单的时间")
    @TableField("submitTime")
    private Date submitTime;

    @ApiModelProperty(value = "提交该报修单的用户的编号")
    @TableField("userId")
    private String userId;


}
