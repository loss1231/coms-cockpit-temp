package com.carerobot.cockpit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.common.util.EmojiConverterUtil;
import com.carerobot.cockpit.dto.AccidentReportForEDto;
import com.carerobot.cockpit.dto.FeedbackDto;
import com.carerobot.cockpit.model.*;
import com.carerobot.cockpit.mapper.AccidentReportMapper;
import com.carerobot.cockpit.service.AccidentReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carerobot.cockpit.service.SysSettingService;
import com.carerobot.cockpit.service.SysUsersService;
import com.carerobot.cockpit.service.WechatOfficialAccountUserService;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-06-16
 */
@Service
public class AccidentReportServiceImpl extends ServiceImpl<AccidentReportMapper, AccidentReport> implements AccidentReportService {

    private Logger logger = LoggerFactory.getLogger(AccidentReportServiceImpl.class);
    @Autowired
    private SysUsersService sysUsersService;
    @Autowired
    private WechatOfficialAccountUserService wechatService;
    @Autowired
    private SysSettingService sysSettingService;

    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<AccidentReport> getDataAccidentFromRemote() {
        QueryWrapper<AccidentReport> wrapper = new QueryWrapper<>();
        //暂时数据分布情况未知，获天/周/月节点的数据
        Wrapper<AccidentReport> accidentReportWrapper = wrapperByMonth(wrapper);
        return this.list(accidentReportWrapper);
    }

    private Wrapper<AccidentReport> wrapperByMonth(QueryWrapper<AccidentReport> wrapper) {
        return wrapper.eq("IsDeleted", false)
               .between("CreationTime", DateUtils.stringToDate(DateUtils.monthStart, "yyyy-MM-dd HH:mm:ss")
                , DateUtils.stringToDate(DateUtils.monthEnd, "yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    @Transactional
    public boolean saveBatchIntoLocal(List<AccidentReport> dataList) {
        logger.info("事故报告数据更新开始");
        if(this.saveBatch(dataList)){
            logger.info("事故报告数据更新结束---此次更新数据条数：{}", dataList.size());
            return true;
        }
        logger.info("事故报告数据更新失败----");
        return false;
    }

    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<AccidentReport> getDataAccidentByDay() {
        QueryWrapper<AccidentReport> wrapper = new QueryWrapper<>();
        //暂时数据分布情况未知，获天/周/月节点的数据
        Wrapper<AccidentReport> accidentReportWrapper = wrapperByDay(wrapper);
        return this.list(accidentReportWrapper);
    }

    @Override
    @Transactional
    public void updateThenAdd(List<AccidentReport> dataList) {
        /*//先批量更新操作
        if(this.updateBatchById(dataList)){
            //再批量插入操作
            this.saveBatch(dataList);
            XxlJobLogger.log("客户数据同步结束,总条数：{}", dataList.size());
        }else{
            XxlJobLogger.log("数据更新失败");
        }*/

        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新事故报告旧数据结束");

            //再批量插入操作
            for (AccidentReport accident : dataList) {
                AccidentReport byId = this.getById(accident.getId());
                if (byId==null){
                    this.save(accident);
                }
            }
            logger.info("事故报告数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("事故报告数据更新失败");
        }
    }

    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<AccidentReport> getEntireTableAccident() {
        return this.list(new QueryWrapper<>());
    }

    private Wrapper<AccidentReport> wrapperByDay(QueryWrapper<AccidentReport> wrapper) {
        return wrapper.eq("IsDeleted", false)
                .between("CreationTime", DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")
                        , DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    @DataSource(DataSourceEnum.DB8)
    public Map<String,Object> GetAddAccident(AccidentReportForEDto accidentDto, HttpServletRequest request) {
        AccidentReport accident = new AccidentReport();
        BeanUtils.copyProperties(accidentDto,accident);
        accident.setLitigantName(accident.getLitigantName());
        accident.setCreatorUserId(1L);
        accident.setCreatorUserName("管理员");
        accident.setIsDeleted(false);
        accident.setAccidentId(UUID.randomUUID().toString());
        this.save(accident);
        HashMap<String, Object> map = new HashMap<>();
        map.put("reportName",accident.getAccidentTitle());
        map.put("reportTime",DateUtils.format(accident.getHappenData(),"yyyy-MM-dd HH:mm:ss"));
        map.put("description",accident.getDescription());
        map.put("accidentId",accident.getId());
        return map;
    }

    @DataSource(DataSourceEnum.DB7)
    public String sendAccidentMessage(Long userId, String reportName,Date reportTime, String description, Integer reportId) {
        String result = "";
        SysUsers user = this.sysUsersService.getById(userId);
        if (user != null) {
            FeedbackDto feedback = getAccidentFeedbackDto(user, reportName, reportTime, description, reportId);
            if(feedback.getTouser()==null || feedback.getTouser().isEmpty()){
                return "用户未关注公众号或未同步："+userId;
            }
            String token = getOfficialAccountToken();
            String url1 = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
            Object jsonStrs = JSONObject.toJSON(feedback);
            System.out.println(jsonStrs.toString());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(url1, feedback, String.class);
            result = responseEntity.getBody();
            System.out.println(result);
        }
        else {
            result = "无效用户：" + userId;
        }
        return result;
    }
    //保存微信公众号用户
    @DataSource(DataSourceEnum.DB7)
    public void saveOfficialAccountUsers()
    {
        String token = getOfficialAccountToken();
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + token;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        if(object== null || !object.containsKey("data") || !object.getJSONObject("data").containsKey("openid")){
            return;
        }
        List<String> openIds = (List<String>) object.getJSONObject("data").get("openid");
        List<WechatOfficialAccountUser> listUsers= new ArrayList<>();
        for (String openId:openIds){
            Map<String, String> params = new HashMap<>();
            params.put("token", token);
            params.put("openid", openId);
            ResponseEntity<String> responseEntity1 = restTemplate.getForEntity(
                    "https://api.weixin.qq.com/cgi-bin/user/info?access_token={token}&openid={openid}", String.class, params);
            String body1 = responseEntity1.getBody();
            WechatOfficialAccountUser user = JSON.parseObject(body1, (Type) WechatOfficialAccountUser.class);
            String nickname= EmojiConverterUtil.emojiConvert1(user.getNickname());
            user.setNickname(nickname);

            WechatOfficialAccountUser user1 = this.wechatService.getOne(new QueryWrapper<WechatOfficialAccountUser>().eq("openid", openId));
            if (user1!=null)user.setId(user1.getId());
            user.setCreateTime(new Date());
            listUsers.add(user);
        }
        //有主键,每次更新没用,都是新增
        Boolean result= wechatService.saveOrUpdateBatch(listUsers);
    }
    //微信公众号token
    @DataSource(DataSourceEnum.DB7)
    public String getOfficialAccountToken() {
//        Object token=DateUtils.localCache.get("wxOfficialAccountToken");
//        if(token!=null) return token.toString();

        SysSetting sysSetting = this.sysSettingService.getOne(new QueryWrapper<SysSetting>().eq("setting_key", "wxOfficialAccountToken"));
        if (sysSetting != null) {
            Date expireTime = sysSetting.getExpireTime();
            if (new Date().getTime() - expireTime.getTime() < 7200000) {
                //数据库有token,直接返回
                return sysSetting.getSettingValue();
            }
        }
        //数据库没有token或已过期,需要重新获取token,并存入数据库中
        if (sysSetting == null) sysSetting = new SysSetting();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("APPID", "wx23b2c0ee53844876");
        params.put("APPSECRET", "d2ee6e925a4a04b7e55d673bc4ae301d");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        String Access_Token = object.getString("access_token");
        System.out.println("access_token：" + Access_Token);
        Long expires_in = object.getLong("expires_in");
        System.out.println("有效时长expires_in：" + expires_in);
        //新token存入数据库
        sysSetting.setSettingKey("wxOfficialAccountToken");
        sysSetting.setTitle("token");
        sysSetting.setSettingValue(Access_Token);
        sysSetting.setExpireTime(new Date());
//        DateUtils.localCache.put("wxOfficialAccountToken",Access_Token,expires_in);
        this.sysSettingService.saveOrUpdate(sysSetting);
        return Access_Token;
    }

    //获取事故消息内容
    @DataSource(DataSourceEnum.DB7)
    private FeedbackDto getAccidentFeedbackDto(SysUsers user, String reportName,Date reportTime, String description, Integer reportId){
        Map<String, Object> text = new HashMap<String, Object>();
        FeedbackDto feedback = new FeedbackDto();
        WechatOfficialAccountUser weUser= wechatService.getOne(new QueryWrapper<WechatOfficialAccountUser>()
                .eq("unionid",user.getOauthId()));
        if(weUser!=null){
            Map<String, Object> miniprogram = new HashMap<String, Object>();
            miniprogram.put("appid", "wx2c3f20a44b0fa8ad");
            String url="pages/tools/addsetcombine";
            if(reportId != null) {
                url = url + "?id=" + reportId;
            }
            miniprogram.put("pagepath", url);
            feedback.setMiniprogram(miniprogram);
            feedback.setTouser(weUser.getOpenid());
            feedback.setTemplate_id("wy3gOVPbu-uacAPYHQ6hddlZTyehhdATGyKE1TpRUwU");
            Map<String, Object> first = new HashMap<String, Object>();
            first.put("value", "已生成事故报告。");
            text.put("first", first);
            Map<String, Object> keyword1 = new HashMap<String, Object>();
            keyword1.put("value", reportName);
            text.put("keyword1", keyword1);
            Map<String, Object> keyword2 = new HashMap<String, Object>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datef = sdf.format(reportTime);
            keyword2.put("value", datef);
//        keyword2.put("value", "2021年10月19日 17:30");
            text.put("keyword2", keyword2);
            Map<String, Object> keyword3 = new HashMap<String, Object>();
            keyword3.put("value", description);
            text.put("keyword3", keyword3);
            Map<String, Object> remark = new HashMap<String, Object>();
            remark.put("value", "详细报告请点击查看");
            text.put("remark", remark);
            feedback.setData(text);
        }
        return feedback;
    }

}
