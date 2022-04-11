package com.carerobot.cockpit.model;

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
 * @since 2021-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ComboboxItem对象", description="")
public class ComboboxItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "检索标识")
    private String comboboxId;

    @ApiModelProperty(value = "名称")
    private String comboboxName;

    @ApiModelProperty(value = "存储值")
    private String comboboxValue;

    @ApiModelProperty(value = "显示文字")
    private String comboboxText;

    @ApiModelProperty(value = "顺序编码")
    private Integer comboboxOrder;


}
