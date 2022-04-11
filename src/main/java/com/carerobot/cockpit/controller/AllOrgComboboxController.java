package com.carerobot.cockpit.controller;

import com.alibaba.fastjson.JSONArray;
import com.carerobot.cockpit.common.vo.ResultVo;
import com.carerobot.cockpit.dto.AllOrgComboboxDto;
import com.carerobot.cockpit.dto.AllOrgComboboxPostParamDto;
import com.carerobot.cockpit.dto.AllOrgComboboxReqDto;
import com.carerobot.cockpit.service.AllOrgComboboxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xz
 * @since 2022-04-07
 */
@RestController
@RequestMapping("/cockpit/api/webapi")
@Api
public class AllOrgComboboxController {

    @Autowired
    AllOrgComboboxService allOrgComboboxService;

    /**
     * 2022-04-07
     * @param ac 接收前端post的对象
     * @return
     */
    @PostMapping("/getAllOrgCombobox")
    @ApiOperation("经营数据分析报表")
    public ResultVo getOccupancyRate(@RequestBody AllOrgComboboxPostParamDto ac) throws ParseException{

//        List<AllOrgComboboxDto> list = allOrgComboboxService.getOccupancyRate(ac.getOrgId(), ac.getStartDate(),ac.getEndDate());
        AllOrgComboboxReqDto occupancyRates = allOrgComboboxService.getOccupancyRates(ac.getOrgId(), ac.getStartDate(), ac.getEndDate());

        return ResultVo.ok(occupancyRates);
    }
}
