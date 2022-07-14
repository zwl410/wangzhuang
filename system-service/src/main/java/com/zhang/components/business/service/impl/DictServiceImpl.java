package com.zhang.components.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhang.components.business.entity.Dict;
import com.zhang.components.business.mapper.DictMapper;
import com.zhang.components.business.service.DictService;
import com.zhang.util.XaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DictServiceImpl implements DictService {

    private final DictMapper dictMapper;

    @Override
    public List<Dict> getDictByParams(Map<String, Object> params) {
        log.info("params ()->" + params.toString());
        QueryWrapper<Dict> wrapper = new QueryWrapper();
        if (XaUtil.isNotEmpty(params.get("name"))) {
            wrapper.like("name", params.get("name"));
        }
        if (XaUtil.isNotEmpty(params.get("code"))) {
            wrapper.like("code", params.get("code"));
        }
        return dictMapper.selectList(wrapper);
    }
}
