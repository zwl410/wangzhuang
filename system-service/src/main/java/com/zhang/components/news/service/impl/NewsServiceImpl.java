package com.zhang.components.news.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.constant.result.SelfPageInfo;
import com.zhang.util.JsonUtils;
import com.zhang.components.news.entity.News;
import com.zhang.components.news.mapper.NewsMapper;
import com.zhang.components.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper;

    @Override
    public IPage<Map<String,Object>> getNewsInfo(SelfPageInfo pageInfo) {
        IPage<News> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        Map<String,Object> filterParams = JsonUtils.getParametersStartingWith(pageInfo.getFilterParams(),"QP_");
        return newsMapper.getNewsInfo(page, filterParams);
    }
}
