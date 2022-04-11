package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wd
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ViewMonthBill对象", description="VIEW")
public class ViewMonthBill implements Serializable {

    private static final long serialVersionUID = 1L;

    private String month;

    private Integer orgid;

    private BigDecimal billed;

    private BigDecimal billing;

    private BigDecimal monthIl;

    private BigDecimal monthIlDouble;

    private BigDecimal monthAl;

    private BigDecimal monthBasic;

    private BigDecimal monthAlNurse;

    private BigDecimal monthOther;

    private BigDecimal monthUnassigned;

    private BigDecimal longCare;

    private BigDecimal thirdRent;

    private BigDecimal adjustMonthIl;

    private BigDecimal adjustMonthIlDouble;

    private BigDecimal adjustMonthAl;

    private BigDecimal adjustMonthBasic;

    private BigDecimal adjustMonthFood;

    private BigDecimal adjustCareAl;

    private BigDecimal adjustCareIl;

    private BigDecimal adjustHealthyCare;

    private BigDecimal adjustProtocol;

    private BigDecimal adjustOther;

    private BigDecimal adjustUnassigned;

    private BigDecimal adjustLate;

    private BigDecimal restaurantService;

    private BigDecimal restaurantRetail;

    private BigDecimal outpatient;

    private BigDecimal commissionMediate;

    private BigDecimal commissionOther;

    private BigDecimal otherOpBasic;

    private BigDecimal otherOpProtocol;

    private BigDecimal otherOpElectric;

    private BigDecimal otherOpWater;

    private BigDecimal otherOpTech;

    private BigDecimal otherOpOther;


}
