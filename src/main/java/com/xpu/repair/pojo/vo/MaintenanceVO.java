package com.xpu.repair.pojo.vo;

import com.xpu.repair.pojo.entity.Repair;
import com.xpu.repair.pojo.entity.Technician;
import lombok.Data;

import java.util.Date;

@Data
public class MaintenanceVO {

    private Integer id;

    private Repair repair;

    private Technician technician;

    private Date startTime;

    private Date endTime;

    private String pictureUrl;
}
