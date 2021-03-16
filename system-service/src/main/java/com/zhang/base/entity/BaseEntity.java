package com.zhang.base.entity;

import com.zhang.constant.XaConstant;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseEntity implements Serializable {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(name = "唯一标识", required = true)
    private Long Id;
    @ApiModelProperty(name = "创建时间")
    private String createTime;
    @ApiModelProperty(name = "创建人")
    private String createUser;
    @ApiModelProperty(name = "状态，0:锁定，1:正常，9:删除")
    private Integer status;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        if (StringUtils.isBlank(createTime)) {
            this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else {
            this.createTime = createTime;
        }
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        if (status == null) {
            this.status = XaConstant.Status.normal;
        } else {
            this.status = status;
        }
    }

}
