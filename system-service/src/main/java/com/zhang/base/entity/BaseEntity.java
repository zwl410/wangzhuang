package com.zhang.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.JdbcType;

import javax.xml.crypto.Data;
import java.io.Serializable;

@lombok.Data
public class BaseEntity implements Serializable {


    @TableField(value = "create_time", fill = FieldFill.INSERT, jdbcType = JdbcType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Data createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT, jdbcType = JdbcType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Data updateTime;
}
