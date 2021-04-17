package com.xpu.repair.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ExcelData {

    // 表头
    private List<String> titles;

    // 数据
    private List<List<Object>> rows;

    // 页签名称
    private String name;
}
