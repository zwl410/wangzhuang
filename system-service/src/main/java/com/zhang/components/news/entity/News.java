package com.zhang.components.news.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("新闻信息实体类")
@TableName("tb_xa_news_info")
public class News implements Serializable {

    @ApiModelProperty(name = "唯一标识",required = true)
    private Long id;
    @ApiModelProperty(name = "创建时间")
    private String createTime;
    @ApiModelProperty(name = "创建人")
    private String createUser;
    @ApiModelProperty(name = "状态")
    private Integer status;
    @ApiModelProperty(name = "新闻标题")
    private String title;
    @ApiModelProperty(name = "新闻静态页面地址")
    private String newsUrl;
    @ApiModelProperty(name = "上次修改人")
    private Long lastUpdateUser;
    @ApiModelProperty(name = "上次修改时间")
    private String lastUpdateTime;
    @ApiModelProperty(name = "新闻类型")
    private String newsType;
    @ApiModelProperty(name = "发布人")
    private Long releaseUser;
}
