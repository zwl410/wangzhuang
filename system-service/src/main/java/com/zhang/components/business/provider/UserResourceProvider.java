package com.zhang.components.business.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserResourceProvider {

    public String getMenu(Long userId) {
        return new SQL() {{
            SELECT("t1.id,t1.parent_id,t1.`name`,t1.url,t1.type,t1.icon,t1.permission,t1.sort,t1.parent_id");
            FROM("tb_cms_resource t1");
            LEFT_OUTER_JOIN("tb_cms_role_resource t2 ON t1.id = t2.resource_id");
            LEFT_OUTER_JOIN("tb_cms_role t3 ON t3.id = t2.role_id");
            LEFT_OUTER_JOIN("tb_cms_user_role t4 ON t3.id = t4.role_id");
            LEFT_OUTER_JOIN("tb_xa_cms_user t5 ON t4.user_id = t5.user_id");
            WHERE("t5.user_id = " + userId).GROUP_BY("t1.id").ORDER_BY("t1.type,t1.sort");
        }}.toString();
    }

}
