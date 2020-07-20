package com.zhang.components.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhang.constant.result.SelfPageInfo;

import java.util.Map;

public interface NewsService {

    /**
     * 查询新闻信息
     * @return
     */
    IPage<Map<String,Object>> getNewsInfo(SelfPageInfo  pageInfo);

}
