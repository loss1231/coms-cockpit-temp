package com.carerobot.cockpit.controller;

import com.carerobot.cockpit.common.vo.ResultVo;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.ElderDetail;
import com.carerobot.cockpit.model.OccupancyRate;
import com.carerobot.cockpit.model.newOccupancyRate;
import com.carerobot.cockpit.service.ArchivesinfoService;
import com.carerobot.cockpit.service.CockpitArchivesService;
import com.carerobot.cockpit.service.ContractInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wd
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/cockpit/api/webapi")
@Api("客户接口控制器")
public class ArchivesinfoController {

    @Autowired
    private ArchivesinfoService archivesinfoService;

    @Autowired
    private ContractInfoService contractInfoService;

    @Autowired
    private CockpitArchivesService cockpitArchivesService;

    @GetMapping("/GetAll")
    @ApiOperation("测试用")
    public ResultVo GetAll(){
        List<Archivesinfo> archiveList = this.archivesinfoService.getDataFromCrm();
        return ResultVo.ok(archiveList);
    }

    @GetMapping("/GetCockpit")
    @ApiOperation("入住率")
    public ResultVo GetCockpit(HttpServletRequest request){
        String orgId = request.getHeader("OrgId");
        return ResultVo.ok(contractInfoService.GetCockpit(Integer.parseInt(orgId)));
    }

    @GetMapping("/GetCode")
    @ApiOperation("获取code")
    public ResultVo GetCode(Integer id){
        String code = this.cockpitArchivesService.getCode(id);
        if (code==null){
            return ResultVo.fail("查无此人！");
        }
        return ResultVo.ok(code);
    }

//    //同步客户并同步carebox获取careboxcode
    @GetMapping("/UpdateCockpitCode")
    @ApiOperation("更新code")
    public ResultVo updateCareboxCode(Integer id,HttpServletRequest request){
        try{
            String token = request.getHeader("Authorizatior");
            String orgId = request.getHeader("OrgId");
            if (token == null || orgId == null)return ResultVo.fail("增加失败");
            this.cockpitArchivesService.updateCareboxCode(id,token,Integer.parseInt(orgId));
            return ResultVo.ok();
        }catch (Exception e){
            return ResultVo.ok();
        }
    }

    @GetMapping("/updateNurseLevel")
    @ApiOperation("获取code")
    public ResultVo updateNurseLevel(String code,Integer nurseLevel,String nurseLevelStr){
        String msg = this.cockpitArchivesService.updateNurseLevel(code,nurseLevel,nurseLevelStr);
        return ResultVo.ok(msg);
    }

    @GetMapping("/testOcc")
    public ResultVo testOcc(){
        Date now = new Date();
        String tableName = "occupancy_snapshot_"+DateUtils.format(new Date(),"yyyyMMdd");
        //创建表
        cockpitArchivesService.createOccupancyTable(tableName);
        List<newOccupancyRate> result1 = cockpitArchivesService.searchOccupancy(1,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result2 = cockpitArchivesService.searchOccupancy(2,DateUtils.addDateDays(now,-1),now);
        //List<OccupancyRate> result3 = cockpitArchivesService.searchOccupancy(1,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result4 = cockpitArchivesService.searchOccupancy(4,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result5 = cockpitArchivesService.searchOccupancy(5,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result6 = cockpitArchivesService.searchOccupancy(6,DateUtils.addDateDays(now,-1),now);
        List<newOccupancyRate> result7 = cockpitArchivesService.searchOccupancy(7,DateUtils.addDateDays(now,-1),now);
        result1.addAll(result2);
        result1.addAll(result4);
        result1.addAll(result5);
        result1.addAll(result6);
        result1.addAll(result7);
        cockpitArchivesService.insertOccupancy(tableName,result1);
        return ResultVo.ok();
    }

    @GetMapping("/testAD")
    public ResultVo testAD(){
        Date now = new Date();
        String tableName = "elder_detail_snapshot_"+DateUtils.format(new Date(),"yyyyMMdd");
        //创建表
        cockpitArchivesService.createElderDetailTable(tableName);
        List<ElderDetail> result = cockpitArchivesService.searchElderDetail();
        cockpitArchivesService.insertElderDetail(tableName,result);
        return ResultVo.ok();
    }
}

