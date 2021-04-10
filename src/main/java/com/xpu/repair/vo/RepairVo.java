package com.xpu.repair.vo;

import com.xpu.repair.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class RepairVo {

    private Integer id;

    private Integer status;

    private String statusName;

    private String detail;

    private String place;

    private String pictureUrl;

    private Date submitTime;

    private String userId;

    private User user;
}
