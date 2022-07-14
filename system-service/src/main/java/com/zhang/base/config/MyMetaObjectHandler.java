package com.zhang.base.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动对数据库实体添加日期属性
 *
 * @author : why
 * @since : 2020-06-19
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = now();
        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("updateTime", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = now();
        this.setFieldValByName("updateTime", now, metaObject);
    }

    protected Date now() {
        return DateTime.now().toDate();
    }
}
