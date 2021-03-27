package com.xpu.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别：0为男1为女，默认为男
     */
    private Integer sexual;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;


}
