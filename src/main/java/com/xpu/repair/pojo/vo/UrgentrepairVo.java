package com.xpu.repair.pojo.vo;

import com.xpu.repair.pojo.entity.Repair;
import lombok.Data;

import java.util.Date;

@Data
public class UrgentrepairVo {

    private Integer id;

    private Repair repair;

    private String status;

    private Date createTime;

    private Integer count; //催单次数
}
