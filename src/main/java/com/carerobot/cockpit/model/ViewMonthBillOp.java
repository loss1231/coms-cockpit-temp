package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="ViewMonthBillOp对象", description="VIEW")
public class ViewMonthBillOp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("OrgId")
    private Integer OrgId;

    private String month;

    @ApiModelProperty(value = "自理区服务费收入")
    private BigDecimal incomIl;

    @ApiModelProperty(value = "护理区服务费收入")
    private BigDecimal incomAl;

    @ApiModelProperty(value = "餐饮收入")
    private Integer incomRestaurant;

    @ApiModelProperty(value = "其他运营收入")
    private BigDecimal incomOther;


}
