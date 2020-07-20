package com.zhang.components.dict.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("字典信息实体类")
@TableName("tb_xa_dict_info")
public class Dict implements Serializable {

    @ApiModelProperty(name = "唯一标识",required = true)
    private Long id;
    @ApiModelProperty(name = "创建时间")
    private String createTime;
    @ApiModelProperty(name = "创建人")
    private String createUser;
    @ApiModelProperty(name = "状态")
    private Integer status;

    @ApiModelProperty(name = "字典code")
    private String code;
    @ApiModelProperty(name = "字典名称")
    private String name;
    @ApiModelProperty(name = "字典排序")
    private Integer sort;
    @ApiModelProperty(name = "字典类型code")
    private String type;
}
