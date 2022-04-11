package com.carerobot.cockpit.dto;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;

@Data
public class AllOrgComboboxReqDto {

    private JSONArray jsonArray;

    private List list;

}