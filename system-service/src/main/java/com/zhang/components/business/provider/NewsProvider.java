package com.zhang.components.business.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class NewsProvider {

    public String getNewsInfo(Map<String, Object> filterParams) {
        return new SQL() {{
            SELECT("*");
            FROM("tb_xa_news_info");
        }}.toString();
    }
}
