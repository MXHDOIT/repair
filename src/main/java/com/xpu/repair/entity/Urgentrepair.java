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
 * 催单表
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Urgentrepair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 状态：0为待查看，1为已查看，2为被用户取消，默认为待查看
     */
    private Integer status;

    /**
     * 该催单对应的报修单编号
     */
    @TableField("repairId")
    private Integer repairId;

    /**
     * 发起该催单的用户的编号
     */
    @TableField("userId")
    private String userId;

    /**
     * 催单的创建时间
     */
    @TableField("createTime")
    private Date createTime;


}
