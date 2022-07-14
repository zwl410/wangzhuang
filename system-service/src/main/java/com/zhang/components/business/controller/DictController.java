package com.zhang.components.business.controller;

import com.zhang.components.business.entity.Dict;
import com.zhang.components.business.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@Api("字典信息接口")
@RestController
@RequestMapping("/api/dict")
public class DictController {

    private final DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    @ApiOperation("通过名称模糊查询字典信息")
    @GetMapping("getDictInfoByName")
    public ResponseEntity<Object> getDictInfoByName(@NotNull @ApiParam(name = "名称",required = true) String name) {
        List<Dict> result = dictService.getDictByParams(new HashMap() {{
            put("name", name);
        }});
        return ResponseEntity.ok(result);
    }
}
