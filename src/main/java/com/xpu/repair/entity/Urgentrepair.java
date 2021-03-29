package com.xpu.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 催单表
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Urgentrepair对象", description="催单表")
public class Urgentrepair implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "状态：0为待查看，1为已查看，2为被用户取消，默认为待查看")
    private Integer status;

    @ApiModelProperty(value = "该催单对应的报修单编号")
    private Integer repairId;

    @ApiModelProperty(value = "发起该催单的用户的编号")
    private String userId;

    @ApiModelProperty(value = "催单的创建时间")
    private Date createTime;


}
