package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carerobot.cockpit.common.util.PageUtils;
import com.carerobot.cockpit.common.vo.PageResultVo;
import com.carerobot.cockpit.dto.ElderStatisticsDto;
import com.carerobot.cockpit.dto.FeeIncomeRecognitionShowDto;
import com.carerobot.cockpit.dto.FeeReportQueryDto;
import com.carerobot.cockpit.dto.FeeReportShowDto;
import com.carerobot.cockpit.model.ReportIncomeItem;
import com.carerobot.cockpit.model.VElderRecord;
import com.carerobot.cockpit.model.ViewFee;
import com.carerobot.cockpit.mapper.ViewFeeMapper;
import com.carerobot.cockpit.model.ViewMonthBill;
import com.carerobot.cockpit.service.ReportIncomeItemService;
import com.carerobot.cockpit.service.VElderRecordService;
import com.carerobot.cockpit.service.ViewFeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carerobot.cockpit.service.ViewMonthBillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wd
 * @since 2022-03-28
 */
@Service
public class ViewFeeServiceImpl extends ServiceImpl<ViewFeeMapper, ViewFee> implements ViewFeeService {

    @Autowired
    private ViewFeeService viewFeeService;

    @Autowired
    private ReportIncomeItemService reportIncomeItemService;

    @Autowired
    private ViewMonthBillService viewMonthBillService;

    @Override
    public List<FeeIncomeRecognitionShowDto> getIncomeRecognitionReport(FeeReportQueryDto search) {
        List<FeeIncomeRecognitionShowDto> list = new ArrayList<>();
        QueryWrapper<ReportIncomeItem> wrapper = new QueryWrapper<ReportIncomeItem>();
        List<ReportIncomeItem> listItem=this.reportIncomeItemService.list(wrapper);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        String month = sdf.format(search.getEndDate());

        QueryWrapper<ViewMonthBill> wrapperBill = new QueryWrapper<ViewMonthBill>();
        wrapperBill.eq("month",month);
        wrapperBill.in("OrgId",search.getOrgIds());

        List<ViewMonthBill> monthBill=this.viewMonthBillService.list(wrapperBill);
        Field[] fields= ViewMonthBill.class.getDeclaredFields();
        for (ReportIncomeItem item:listItem
        ) {
//            Field field=Arrays.stream(fields).filter(x->x.equals(item.getQueryCode())).findFirst().get();
//            monthBill.stream().
            BigDecimal amount=BigDecimal.ZERO;
            switch (item.getQueryCode()){
                case "billed":
                    amount=monthBill.stream().map(ViewMonthBill::getBilled).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "billing":
                    amount=monthBill.stream().map(ViewMonthBill::getBilling).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "month_il":
                    amount=monthBill.stream().map(ViewMonthBill::getMonthIl).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "month_il_double":
                    amount=monthBill.stream().map(ViewMonthBill::getMonthIlDouble).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "month_al":
                    amount=monthBill.stream().map(ViewMonthBill::getMonthAl).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "month_basic":
                    amount=monthBill.stream().map(ViewMonthBill::getMonthBasic).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "month_al_nurse":
                    amount=monthBill.stream().map(ViewMonthBill::getMonthAlNurse).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "month_other":
                    amount=monthBill.stream().map(ViewMonthBill::getMonthOther).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "month_unassigned":
                    amount=monthBill.stream().map(ViewMonthBill::getMonthUnassigned).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "long_care":
                    amount=monthBill.stream().map(ViewMonthBill::getLongCare).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "third_rent":
                    amount=monthBill.stream().map(ViewMonthBill::getThirdRent).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_month_il":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustMonthIl).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_month_il_double":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustMonthIlDouble).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_month_al":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustMonthAl).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_month_basic":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustMonthBasic).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_month_food":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustMonthFood).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_care_al":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustCareAl).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_care_il":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustCareIl).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_healthy_care":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustHealthyCare).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_protocol":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustProtocol).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_other":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustOther).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_unassigned":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustUnassigned).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "adjust_late":
                    amount=monthBill.stream().map(ViewMonthBill::getAdjustLate).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "restaurant_service":
                    amount=monthBill.stream().map(ViewMonthBill::getRestaurantService).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "restaurant_retail":
                    amount=monthBill.stream().map(ViewMonthBill::getRestaurantRetail).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "outpatient":
                    amount=monthBill.stream().map(ViewMonthBill::getOutpatient).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "commission_mediate":
                    amount=monthBill.stream().map(ViewMonthBill::getCommissionMediate).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "commission_other":
                    amount=monthBill.stream().map(ViewMonthBill::getCommissionOther).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "other_op_basic":
                    amount=monthBill.stream().map(ViewMonthBill::getOtherOpBasic).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "other_op_protocol":
                    amount=monthBill.stream().map(ViewMonthBill::getOtherOpProtocol).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "other_op_electric":
                    amount=monthBill.stream().map(ViewMonthBill::getOtherOpElectric).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "other_op_water":
                    amount=monthBill.stream().map(ViewMonthBill::getOtherOpWater).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "other_op_tech":
                    amount=monthBill.stream().map(ViewMonthBill::getOtherOpTech).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                case "other_op_other":
                    amount=monthBill.stream().map(ViewMonthBill::getOtherOpOther).reduce(BigDecimal.ZERO,BigDecimal::add);
                    break;
                default:
                    break;
            }
            FeeIncomeRecognitionShowDto feeShow= new FeeIncomeRecognitionShowDto();
            BeanUtils.copyProperties(item,feeShow);
            feeShow.setAmount(amount);
            list.add(feeShow);
        }

        for (FeeIncomeRecognitionShowDto fee:list
        ) {
            fee.setAmount(sumIncomeRecognition(fee,list));
        }
        return list;
    }
    //循环求和
    private BigDecimal sumIncomeRecognition(FeeIncomeRecognitionShowDto fee, List<FeeIncomeRecognitionShowDto> list) {
        List<FeeIncomeRecognitionShowDto> childFees = list.stream().filter(x -> x.getParentId().equals(fee.getId())).collect(Collectors.toList());
        BigDecimal amount = BigDecimal.ZERO;
        if (childFees.size() > 0) {
            for (FeeIncomeRecognitionShowDto feeChild : childFees
            ) {
                amount = amount.add(sumIncomeRecognition(feeChild, list));
            }
        }
        return fee.getAmount().add(amount);
    }

    @Override
    public List<FeeReportShowDto> getFeeForReport(Date startDate, Date endDate, Integer name, Integer bedNo, String itemName, String orgName) {
        List<FeeReportShowDto> list = new ArrayList<>();
        QueryWrapper<ViewFee> wrapper = new QueryWrapper<ViewFee>();
        List<ViewFee> listView=this.viewFeeService.list(wrapper);
        return list;
    }

    @Override
    public PageResultVo getFeeForReportByPage(FeeReportQueryDto search) {
//        List<FeeReportShowDto> list = new ArrayList<>();
//        QueryWrapper<ViewFee> wrapper = new QueryWrapper<ViewFee>();

        Page<ViewFee> page = new Page<>(search.getPageDto().getPageIndex(), search.getPageDto().getPageSize());
        QueryWrapper<ViewFee> queryWrapper = new QueryWrapper<>();
        if (search.getStartDate() != null && search.getEndDate() != null){
            queryWrapper.between("happen_date",search.getStartDate(),search.getEndDate());
        }
        if(search.getName() != null && !search.getName().isEmpty()){
            queryWrapper.like("Name",search.getName());
        }
        if (search.getItemName() != null && !search.getItemName().isEmpty()){
            queryWrapper.eq("ItemName",search.getItemName());
        }
        if(search.getBedNo() != null && !search.getBedNo().isEmpty()){
            queryWrapper.like("BedInfoBedNo",search.getBedNo());
        }
        if (search.getOrgName() != null && !search.getOrgName().isEmpty()){
            queryWrapper.eq("OrgName",search.getOrgName());
        }
        if (search.getOrgIds() != null && search.getOrgIds().size() > 0){
            queryWrapper.in("OrgId",search.getOrgIds());
        }

        IPage<ViewFee> pageResult = this.page(page, queryWrapper);
//        List<ViewFee> records = pageResult.getRecords();
//        List<FeeReportShowDto> list = new ArrayList<>();
//        BeanUtils.copyProperties(records,list);


        return PageUtils.pageTrans(pageResult);
    }
}
