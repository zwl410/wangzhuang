package com.zhang.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

@lombok.Data
public class BaseEntity implements Serializable {


    @TableField(value = "create_time", fill = FieldFill.INSERT, jdbcType = JdbcType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT, jdbcType = JdbcType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
