package com.carerobot.cockpit.model;

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
 * 
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ItemDetail对象", description="")
public class ItemDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增编号")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "关联的账单编号")
    private Integer itemId;

    @ApiModelProperty(value = "明细对应的枚举编号，例如：餐费对应一餐或者二餐；杂费对应有线电视或者电话费；")
    private String code;

    @ApiModelProperty(value = "具体描述：如果是床位，那么就是床位号；如果是餐费，那么就是餐数；如果是空置费，就是床位；如果是护理费，就对应级别；如果是杂费（要到具体的杂费类型），例如：有线电视，就是床位；电话费：也是床位；")
    private String dsc;

    @ApiModelProperty(value = "备注")
    private String remark;


}
