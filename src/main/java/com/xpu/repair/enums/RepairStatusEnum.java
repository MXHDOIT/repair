package com.xpu.repair.enums;

public enum RepairStatusEnum {

    UNALLOCATED(0,"未分配"),
    ALLOCATED(1,"分配未维修完成"),
    COMPLETE(2,"维修完成");

    private int statusId;
    private String statusName;

    private RepairStatusEnum(int statusId,String statusName){
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public static RepairStatusEnum getById(int statusId){
        RepairStatusEnum[] values = RepairStatusEnum.values();
        for (RepairStatusEnum value : values) {
            if (value.statusId == statusId) {
                return value;
            }
        }
        return null;
    }
}
