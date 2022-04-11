package com.carerobot.cockpit.model;

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
 * 
 * </p>
 *
 * @author wd
 * @since 2021-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUsers对象", description="")
public class SysUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Long Id;

    @TableField("Account")
    private String Account;

    @TableField("Name")
    private String Name;

    @TableField("PostId")
    private Integer PostId;

    @TableField("PostName")
    private String PostName;

    @TableField("MemberOrgId")
    private Integer MemberOrgId;

    @TableField("MemberOrgName")
    private String MemberOrgName;

    @TableField("Email")
    private String Email;

    @ApiModelProperty(value = "第三方id")
    @TableField("OauthId")
    private String OauthId;


}
