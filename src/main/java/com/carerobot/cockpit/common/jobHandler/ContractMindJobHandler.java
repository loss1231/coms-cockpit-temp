package com.carerobot.cockpit.common.jobHandler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.contants.Url;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.ContractInfo;
import com.carerobot.cockpit.service.ContractInfoService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ZhaoPo
 * @Description 通过no获取所有的选项
 * @Date ${Date}
 * @Param ${params}
 * @return ${return}
 **/
@Component
public class ContractMindJobHandler {

    @Autowired
    private ContractInfoService contractInfoService;

    @Autowired
    private RestTemplate restTemplate;

    //分机构发送
    @XxlJob("ContractMindJobHandler")
    public ReturnT<String> executorEntireData(String param) {
        List<ContractInfo> list = contractInfoService.list(new QueryWrapper<ContractInfo>()
                .le("EndDate", DateUtils.addDateMonths(new Date(), 2))
                .ge("EndDate", new Date())
                .eq("ContractStatus",1)
                .eq("IsDeleted",false)
                .eq("IsSend",0));
        HashMap<String, String> map = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorizatior",getToken());
        HttpEntity requestEntity = new HttpEntity(map, headers);

        //合同到期提醒时间：到期前两个月；内容：住户，区域，房间号，入住类型，合同开始日期，合同到期日期
        HashMap<Integer, String> msgMap = new HashMap<>();
        for (ContractInfo contractInfo : list) {
            if (contractInfo.getOrgId()==0 || contractInfo.getOrgId()==null)continue;
            if (!msgMap.containsKey(contractInfo.getOrgId()))msgMap.put(contractInfo.getOrgId(),"");

            String msg = msgMap.get(contractInfo.getOrgId());
            msg = msg +
                    "姓名：" + contractInfo.getArchivesInfoName() +
                    ",区域："+ ("1".equals(contractInfo.getTType())?"AL":"2".equals(contractInfo.getTType()) || "4".equals(contractInfo.getTType())?"IL":"") +
                    ",房间号：" + (contractInfo.getBedInfoBedNo()==null?"":contractInfo.getBedInfoBedNo()) +
                    ",入住类型：" + (contractInfo.getIsLongOrShort()==1?"长客":
                                        contractInfo.getIsLongOrShort()==2?"短住":
                                                contractInfo.getIsLongOrShort()==3?"访客":
                                                        contractInfo.getIsLongOrShort()==4?"试住":"") +
                    ",合同开始时间：" + DateUtils.format(contractInfo.getStartDate(),"yyyy-MM-dd") +
                    ",合同结束时间：" + DateUtils.format(contractInfo.getEndDate(),"yyyy-MM-dd") +
                    "<br>";
            msgMap.put(contractInfo.getOrgId(),msg);
        }

        for (Integer integer : msgMap.keySet()) {
            headers.set("OrgId",integer.toString());
            map.put("msg",msgMap.get(integer));
            this.restTemplate.exchange(Url.url + "todo/api/webapi/sendmail?code=102",HttpMethod.POST,requestEntity,String.class);
        }
        String ids = list.stream().map(ContractInfo::getId).collect(Collectors.toList()).toString().replaceAll("\\[","(").replaceAll("\\]",")");
        this.contractInfoService.updateSend(ids);
        return ReturnT.SUCCESS;
    }

    private String getToken(){
        HashMap<String, String> map = new HashMap<>();
        map.put("oauthid","");
        map.put("password","654321");
        map.put("type","0");
        map.put("user_name","admin");
        HttpEntity requestEntity = new HttpEntity(map, null);
        ResponseEntity<String> archives = restTemplate.exchange(Url.url + "ims/api/coms/login", HttpMethod.POST, requestEntity, String.class);
        JSONObject result = JSONObject.parseObject(archives.getBody());
        return result.getString("data");
    }

}
