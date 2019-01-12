package com.it.common.base.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 基础配置类，让spring扫描com.it下的所有的类
 */
@Configuration
@ComponentScan("com.it")
public class AutoConfigurationBase {

}
