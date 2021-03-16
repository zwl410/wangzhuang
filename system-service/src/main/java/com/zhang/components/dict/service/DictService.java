package com.zhang.components.dict.service;

import com.zhang.components.dict.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {

    List<Dict> getDictByParams(Map<String,Object> params);
}
