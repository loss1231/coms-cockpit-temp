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
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReportIncomeItem对象", description="")
public class ReportIncomeItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "科目编码")
    private String itemCode;

    @ApiModelProperty(value = "科目名称")
    private String itemName;

    @ApiModelProperty(value = "层级")
    private Integer itemLevel;

    @ApiModelProperty(value = "对应查询code")
    private String queryCode;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否其他费用")
    private Boolean isOther;

    @ApiModelProperty(value = "排序")
    private Integer seq;


}
