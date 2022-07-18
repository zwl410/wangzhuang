package com.zhang.base.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 后端微服务项目启动时会执行此方法，可以进行一些初始化
 *
 * @author : why
 * @since : 2020-07-16
 */
@Slf4j
@Component
public class BackendCommandRunner implements CommandLineRunner {

    @Autowired
    private AsyncCommandRunner asyncCommandRunner;

    @Override
    public void run(String... args) throws Exception {
        asyncCommandRunner.runAsync();
    }

}
