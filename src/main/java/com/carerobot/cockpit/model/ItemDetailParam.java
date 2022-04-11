package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ItemDetailParam对象", description="")
public class ItemDetailParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增序号")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "对应的账单明细类型的code，此项需要关联到enums_param表中的账单明细类型的code.对外显示为：项目名称。")
    private String itemDetailCode;

    @ApiModelProperty(value = "对应的项目类别code，即itemType，对外显示为：项目类别")
    private String itemTypeCode;

    @ApiModelProperty(value = "账单分类，一个不知道干嘛用的东东.")
    private String itemBillTypeCode;

    @ApiModelProperty(value = "杂费分类，另一个不知道做什么用的东西。")
    private String itemMiscTypeCode;

    @ApiModelProperty(value = "助记码")
    @TableField("mnemonicCode")
    private String mnemonicCode;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "月结单价")
    @TableField("monthPrice")
    private BigDecimal monthPrice;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "颜色")
    private String color;

    @ApiModelProperty(value = "顺序号")
    @TableField("orderId")
    private Integer orderId;

    @ApiModelProperty(value = "备注")
    private String remark;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    @ApiModelProperty(value = "是否删除")
    private String isDeleted;


}
