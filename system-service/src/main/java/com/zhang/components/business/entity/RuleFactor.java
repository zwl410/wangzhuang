package com.zhang.components.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhang.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhang
 * @since 2022-07-14
 */
@Data
@ApiModel("TbRuleFactor对象")
@EqualsAndHashCode(callSuper = false)
@TableName("tb_rule_factor")
public class RuleFactor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "唯一标识", required = true)
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;
    @ApiModelProperty(name = "创建人")
    private String createUser;
    @ApiModelProperty(name = "状态，0:锁定，1:正常，9:删除")
    private Integer status;
    @ApiModelProperty(value = "规则因子编号")
    private String ruleFactorNo;
    @ApiModelProperty(value = "规则因子名称")
    private String ruleFactorName;
    @ApiModelProperty(value = "因子所属子系统")
    private String factorOwningSys;
    @ApiModelProperty(value = "因子编号")
    private String factorNo;
    @ApiModelProperty(value = "因子名称")
    private String factorName;
    @ApiModelProperty(value = "属性")
    private Float property;
}
