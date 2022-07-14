package com.zhang.components.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhang.components.business.entity.News;
import com.zhang.components.business.provider.NewsProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface NewsMapper extends BaseMapper<News> {

    @SelectProvider(method = "getNewsInfo", type = NewsProvider.class)
    IPage<Map<String,Object>> getNewsInfo(IPage<News> page, Map<String, Object> filterParams);

}
