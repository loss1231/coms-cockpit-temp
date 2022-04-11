package com.carerobot.cockpit.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 账户表
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Account对象", description="账户表")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    private Date updateTime;

    private Integer updateUserId;

    private String updateUserName;

    private String isDeleted;

    @ApiModelProperty(value = "自增编号")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "客户编号")
    private String archiveId;

    @ApiModelProperty(value = "客户姓名")
    private String archiveName;

    @ApiModelProperty(value = "总缴费金额：总缴费额大于账单金额，即有余额；反之，即有欠费；")
    private BigDecimal totalPayAmount;

    @ApiModelProperty(value = "总账单金额")
    private BigDecimal totalBillAmount;

    @ApiModelProperty(value = "性别")
    private String archiveGender;


}
