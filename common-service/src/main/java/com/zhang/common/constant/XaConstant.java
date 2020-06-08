package com.zhang.common.constant;

import com.zhang.common.annotation.ConstantName;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/13 17:51
 * @Description: 系统常量类
 */
public class XaConstant {

    /**
     * 用户状态常量类
     */
    public static class Status {
        @ConstantName(type = "UserStatus", code = "lock", name = "锁定状态")
        public static final Integer lock = 0;
        @ConstantName(type = "UserStatus", code = "normal", name = "正常状态")
        public static final Integer normal = 1;
        @ConstantName(type = "UserStatus", code = "delete", name = "删除状态")
        public static final Integer delete = 9;
    }
}
