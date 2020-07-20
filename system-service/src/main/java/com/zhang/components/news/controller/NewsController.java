package com.zhang.components.news.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhang.constant.result.SelfPageInfo;
import com.zhang.components.news.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("新闻信息接口")
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @ApiOperation("查询新闻信息")
    @GetMapping("getNewsInfo")
    public ResponseEntity<Object> getNewsInfo(@RequestBody SelfPageInfo pageInfo) {
        IPage<Map<String, Object>> page = newsService.getNewsInfo(pageInfo);
        return ResponseEntity.ok(page);
    }

}
