package com.zhang.constant.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数
 */
@Data
public class SelfPageInfo implements Serializable {

    /** 当前页 **/
    private int pageNum;
    /** 每页显示条数 **/
    private int pageSize;
    /** 筛选条件 **/
    private String filterParams;
}
