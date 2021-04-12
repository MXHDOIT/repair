package com.xpu.repair.pojo.vo;

import com.xpu.repair.pojo.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class RepairVO {

    private Integer id;

    private Integer status;

    private String statusName;

    private String detail;

    private String place;

    private String pictureUrl;

    private Date submitTime;

    private User user;
}
