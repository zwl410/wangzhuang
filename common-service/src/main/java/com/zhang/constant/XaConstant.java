package com.zhang.constant;

import com.zhang.annotation.ConstantName;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/13 17:51
 * @Description: 系统常量类
 */
public class XaConstant {

    /**
     * 数据状态常量类
     */
    public static final class Status {
        @ConstantName(type = "UserStatus", code = "lock", name = "锁定状态")
        public static final int lock = 0;
        @ConstantName(type = "UserStatus", code = "normal", name = "正常状态")
        public static final int normal = 1;
        @ConstantName(type = "UserStatus", code = "delete", name = "删除状态")
        public static final int delete = 9;
    }

    /**
     * 系统常用code
     */
    public static final class Code{
        @ConstantName(type = "SysCode", code = "success", name = "成功")
        public static final int success = 1;
        @ConstantName(type = "SysCode", code = "error", name = "失败")
        public static final int error = 0;
        @ConstantName(type = "SysCode", code = "code_failure_token", name = "token失效")
        public static final int code_failure_token = 101;
        @ConstantName(type = "SysCode", code = "code_failure_fresh_token", name = "fresh_token失效")
        public static final int code_failure_fresh_token = 102;
        @ConstantName(type = "SysCode", code = "code_failure_fresh_token", name = "token解析失败")
        public static final int code_failure_parsing_token = 103;
        @ConstantName(type = "SysCode", code = "code_failure_fresh_token", name = "token签名失败")
        public static final int code_failure_sign_token = 104;
        @ConstantName(type = "SysCode", code = "code_system_failure", name = "系统操作失败")
        public static final int code_system_failure = 10001;
        @ConstantName(type = "SysCode", code = "code_business_failure", name = "业务操作失败")
        public static final int code_business_failure = 20001;
        @ConstantName(type = "SysCode", code = "code_validation_failure", name = "数据验证失败")
        public static final int code_validation_failure = 30001;
        @ConstantName(type = "SysCode", code = "code_database_failure", name = "数据库操作失败")
        public static final int code_database_failure = 40001;
        @ConstantName(type = "SysCode", code = "code_json_failure", name = "json解析失败")
        public static final int code_json_failure = 50001;
        @ConstantName(type = "SysCode", code = "code_objectNotFound_failure", name = "ObjectNotFound")
        public static final int code_objectNotFound_failure = 60001;
        @ConstantName(type = "SysCode", code = "code_request_parameter_failure", name = "参数请求失败")
        public static final int code_request_parameter_failure = 70001;
    }


    public static final class Message {
        @ConstantName(type = "UserStatus", code = "lock", name = "锁定状态")
        public static final String success = "操作成功！";
        @ConstantName(type = "UserStatus", code = "normal", name = "正常状态")
        public static final String error = "操作失败，请稍后再试！";
        @ConstantName(type = "UserStatus", code = "delete", name = "删除状态")
        public static final String error_request_parameter = "参数缺失异常,请检查参数名称大小写，有无空格等";
    }
}
