package com.xpu.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Integer id;

    /**
     * 密码
     */
    private String password;

    @TableField("userName")
    private String userName;


}