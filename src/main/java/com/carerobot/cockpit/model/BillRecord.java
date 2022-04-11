package com.carerobot.cockpit.model;

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
 * 
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BillRecord对象", description="")
public class BillRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    @ApiModelProperty(value = "账单类型：0为机构常规，1为会籍常规；后续待补充")
    private String billType;

    @ApiModelProperty(value = "客户编号")
    private Integer accountId;

    @ApiModelProperty(value = "账单日期")
    private Date statementDate;

}
