package com.zhang.components.business.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhang.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_drools_rule")
public class DroolsRule extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键标识
     */
    @TableField("id")
    private Long id;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 规则编号
     */
    private String ruleNo;

    /**
     * kbase的名字
     */
    private String kieBaseName;
    /**
     * 设置该kbase需要从那个目录下加载文件，这个是一个虚拟的目录，相对于 `src/main/resources`
     * 比如：kiePackageName=rules/rule01 那么当前规则文件写入路径为： kieFileSystem.write("src/main/resources/rules/rule01/1.drl")
     */
    private String kiePackageName;
    /**
     * 规则内容
     */
    private String ruleContent;
}
