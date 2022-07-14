package com.zhang.components.business.service;

import com.zhang.components.business.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {

    List<Dict> getDictByParams(Map<String,Object> params);
}
