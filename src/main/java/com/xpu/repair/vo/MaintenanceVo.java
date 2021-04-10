package com.xpu.repair.vo;

import com.xpu.repair.entity.Repair;
import com.xpu.repair.entity.Technician;
import lombok.Data;

import java.util.Date;

@Data
public class MaintenanceVo {

    private Integer id;

    private Repair repair;

    private Technician technician;

    private Date startTime;

    private Date endTime;

    private String pictureUrl;
}
