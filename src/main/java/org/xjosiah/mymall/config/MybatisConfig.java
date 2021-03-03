package org.xjosiah.mymall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.xjosiah.mymall.mbg.mapper")
public class MybatisConfig {
}
